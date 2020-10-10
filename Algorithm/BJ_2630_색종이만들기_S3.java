package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2630_색종이만들기_S3 {
	static int N, white=0, blue=0;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		cut(0, 0, N, N,N);
		System.out.println(white);
		System.out.println(blue);
	}

	public static void cut(int startX,int startY,int endX,int endY, int N) {
//		System.out.println(startX + " " +  startY + " " +  endX + " " +  endY + " " +  N);
		if(check(startX,startY,endX,endY)) {
		// 색 일치
			if(map[startX][startY] == 0) {
				white++;
			}else {
				blue++;
			}
			return;			
		}else {
		// 잘라줌
			// 좌상
			cut(startX, startY, startX+N/2, startY+N/2, N/2);
			// 우상
			cut(startX+N/2, startY, endX, startY+N/2, N/2);
			// 좌하
			cut(startX, startY+N/2, startX+N/2, endY, N/2);
			// 우하
			cut(startX+N/2, startY+N/2, endX, endY, N/2);
			
		}
	}
	
	public static boolean check(int startX,int startY,int endX,int endY)  {
		int color = map[startX][startY];
		for(int i=startX; i<endX; i++) {
			for(int j=startY; j<endY; j++) {
				if(color != map[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
