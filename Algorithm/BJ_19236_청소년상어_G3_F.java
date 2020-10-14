package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_19236_청소년상어_G3_F {
	static int[][][] map;
	static int ans=0;
	static int[] arrIndex;
	static int[] dx =  {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		// 번호 : 1~16
		// 8방향
		// 이동할 수 없으면 반시계 45도
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[4][4][2];
		for(int i=0; i<4; i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken()); // 번호
				map[i][j][1] = Integer.parseInt(st.nextToken())-1; // 방향
			}
//			System.out.println(Arrays.toString(map[i][0]));
		}
		ans = map[0][0][0];
		map[0][0][0] = -1;
		start(map, ans,0, 0);
		System.out.println(ans);
	}
	
	public static void start(int[][][] premap, int score, int sharkx, int sharky) {
		int[][][] nowmap = new int[4][4][2];
		arrIndex = new int[17];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				nowmap[i][j][0] = premap[i][j][0]; // 번호
				nowmap[i][j][1] = premap[i][j][1]; // 방향
				if(premap[i][j][0] >= 0) {
					arrIndex[premap[i][j][0]] = i*4 + j;					
				}
			}
		}
//		System.out.println(sharkx + " " + sharky  + " " + score);
	
//		printarr(nowmap);
		
//		System.out.println(Arrays.toString(arrIndex));		
		// 물고기 이동
		move(arrIndex, nowmap);
//		System.out.println(Arrays.toString(arrIndex));		
//		printarr(nowmap);
		
		// 상어 이동
		int num = nowmap[sharkx][sharky][0];
		int way = nowmap[sharkx][sharky][1];
//		System.out.println("상어 이동  " + way );
		for(int i=1; i<=3; i++) {
			
			int nx = sharkx+dx[way]*i;
			int ny = sharky+dy[way]*i;
			
			if( nx < 0 || nx >=  4 || ny < 0 || ny >= 4 || nowmap[nx][ny][0] == 0) {
				continue;
			}
			
			int tempscore = nowmap[nx][ny][0];
//			System.out.println("스코어 : " + tempscore);
			int temp1 = nowmap[nx][ny][0];
			int temp2 = nowmap[nx][ny][1];
			
			nowmap[nx][ny][0] = -1;	
//			nowmap[x][ny][1] = way;	
//			nowmap[nx][ny][1] = nowmap[sharkx][sharky][1];	
			
			nowmap[sharkx][sharky][0] = 0;
			nowmap[sharkx][sharky][1] = 0;
//			int temp = nowmap[nx][ny][1];
			start(nowmap, score + tempscore, nx, ny);
			
			nowmap[sharkx][sharky][0] = -1;
			nowmap[sharkx][sharky][1] = temp2;
//			nowmap[sharkx][sharky][1] = nowmap[nx][ny][1];

			nowmap[nx][ny][0] = temp1;	
			nowmap[nx][ny][1] = temp2;	
			
			
//			for(int k=0; k<8; k++) {
////				int nx = sharkx+dx[(way+k)%8];
////				int ny = sharky+dy[(way+k)%8];
////				System.out.println("방향 : " + (way+k)%8);
//				
//				
//			}
		}
//		System.out.println("리턴 =====" + ans + "  " + score);
		ans = Math.max(ans,score);
		
	}
	public static void printarr(int[][][] map) {
		System.out.println(" 번호판 =====================");
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(map[i][j][0] +" ");
			}
			System.out.println();
		}
		System.out.println();
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(map[i][j][1] +" ");
			}
			System.out.println();
		}
	}
	
	public static void move(int[] arrIndex, int[][][] nowmap) {
		for(int p=1; p<17; p++) {
//			System.out.println(p + "  " + Arrays.toString(arrIndex));	
//			printarr(nowmap);
			if(arrIndex[p] == 0) {
				continue;
			}
			int x = arrIndex[p]/4;
			int y = arrIndex[p]%4;
			int num = nowmap[x][y][0];
			int way = nowmap[x][y][1];
			
			for(int k=0; k<8; k++) {
				int nx = x+dx[(way+k)%8];
				int ny = y+dy[(way+k)%8];
//				System.out.println("방향 : " + num + " " +  (way+k)%8 + " " + nx + "  " + ny + " " + x + "  " + y) ;
				if( nx < 0 || nx >=  4 || ny < 0 || ny >= 4 || num == -1) {
//					System.out.println("건너뜀");
					continue;
				}
				
				if(nowmap[nx][ny][0] >= 0) {
				// 물고기 일 때 or 빈칸
					int tempway  = (way+k)%8;
					// nx , ny , num
					
					nowmap[x][y][0] = nowmap[nx][ny][0];
					nowmap[x][y][1] = nowmap[nx][ny][1];					
					arrIndex[nowmap[x][y][0]] = x *4 +y;
					
					nowmap[nx][ny][0] = num;
					nowmap[nx][ny][1] = (way+k)%8;			
					arrIndex[nowmap[nx][ny][0]] = nx *4 +ny;
//					System.out.println("break");
//					System.out.println(Arrays.toString(arrIndex));	
					break;
				}
			}
		}
	}

}
