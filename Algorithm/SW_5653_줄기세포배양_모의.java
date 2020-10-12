package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5653_줄기세포배양_모의 {
	static int ans, N, M, K, num = 250;
	static int[][] map, firstmap;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static LinkedList<Data> list;
	static Queue<Data> list2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			ans=0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로크기
			M = Integer.parseInt(st.nextToken()); // 가로크기
			K = Integer.parseInt(st.nextToken()); // 배양시간
			
			map = new int[501][501]; // 변하는 세포의 값 저장
			firstmap = new int[501][501];  // 처음 세포의 값 저장
			list = new LinkedList<>();
			list2 = new LinkedList<>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[num+i][num+j] = firstmap[num+i][num+j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			// 0인 값을 -1로 바꿔주어 활성상태 세포와 구분해줌
			for(int i=0; i<501; i++) {
				for(int j=0; j<501; j++) {
					if(map[i][j] == 0) {
						map[i][j] = -1;
					}
				}
			}
			
			for(int j=0; j<K; j++) {
				nextTime();
			}
			
			// 비활성 상태 + 활성 상태 세포 개수 셈
			for(int i=0; i<501; i++) {
				for(int j=0; j<501; j++) {
					if(map[i][j] >=0 | map[i][j] == -3) {
						ans++;
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	public static void nextTime() {
		for(int i=0; i<501; i++) {
			for(int j=0; j<501; j++) {
				// 살아있는 세포 있음
				if(map[i][j] >0)  {
					map[i][j]--;
				}else if(map[i][j] == 0) {
				// 세포 퍼트림
					// 세포 활성 상태후 수명 체크하기 위한 Queue
					list2.add(new Data(i,j,firstmap[i][j]));
					map[i][j] = -3;
					spread(i,j);
					
				}
			}
		}
		
		// 세포 퍼질 때 겹쳐서 퍼지는 경우 높은 값으로 변경해줌
		for(int k=0; k<list.size(); k++) {
			int x = list.get(k).x;
			int y = list.get(k).y;
			int value = list.get(k).value;
			
			if(map[x][y] >0 ) {
				if(map[x][y] < value) {
					map[x][y] = firstmap[x][y]= value;
				}
			}else {
				map[x][y] = firstmap[x][y]= value;
			}
		}
		list.clear();
		
		// 활성 상태의 수명 체크해서 죽은 상태이면 -2로 바꾸어줌
		int size = list2.size();
		for(int k=0; k<size; k++) {
			Data cur = list2.poll();
			int x = cur.x;
			int y = cur.y;
			int value = cur.value;
			
			if(--value == 0) {
				map[x][y] = -2;							
			}else {
				list2.add(new Data(x,y,value));
			}			
		}
		
	}
	
	public static void spread(int x, int y) {
		for(int k=0; k<4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if(0<= nx && nx < 501 && 0<=ny && ny <501  ) {
				if(map[nx][ny] == -1) {
				// 세포 퍼짐
					list.add(new Data(nx,ny, firstmap[x][y]));
				}
			}
		}
	}
	
	static class Data {
		int x;
		int y;
		int value;
		public Data(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
		
		
	}
}
