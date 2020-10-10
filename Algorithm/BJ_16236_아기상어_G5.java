package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16236_아기상어_G5 {

	static int N,ans=0;
	static int[][] map;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	// 상 좌 우 하
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int sharkX=0, sharkY = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
				// 상어 위치 저장
					sharkX = i;
					sharkY = j;
				}
			}
		}
		search(sharkX, sharkY);
		System.out.println(ans);
	}
	
	public static void search(int startx, int starty) {
		Queue<Data> q = new LinkedList<>();
		LinkedList<Data> fish = new LinkedList<>();
		q.add(new Data(startx, starty, 2));
		boolean[][] visited = new boolean[N][N];
		visited[startx][starty] = true;
		map[startx][starty] =0;
		int eatcount=0, move=0;
		while(!q.isEmpty()) { 
			boolean switchmove = false;
			move++;
			// 한 번에 움직이는 위치 
			int size = q.size();
			for(int i=0; i<size; i++) {
				Data cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				int sharksize = cur.sharksize;
				for(int k=0; k<4; k++) {
					int nx = x+dx[k];
					int ny = y+dy[k];
					if(0<= nx && nx < N && 0<= ny && ny <N && !visited[nx][ny]) {						
						if(map[nx][ny] == 0 || map[nx][ny] == sharksize) {
						// 빈공간 or 상어의 크기와 같은 물고기
							visited[nx][ny] = true;
							q.add(new Data(nx,ny,sharksize));
						}else if(sharksize > map[nx][ny] ){
						// 상어의 크기가 더 클 때 fish 에 일단 넣어준 후 우선순위 파악함
							visited[nx][ny] = true;
							fish.add(new Data(nx,ny,sharksize));
						}
					}
				}
			}
			
			// 먼저 먹을 물고기를 찾음
			if(fish.size() !=  0) {
				Data cur = fish.get(0);
				for(int i=1; i<fish.size(); i++) {
					if(cur.x > fish.get(i).x) {
						cur = fish.get(i);
					}else if(cur.x == fish.get(i).x && cur.y > fish.get(i).y) {
						cur = fish.get(i);
					}
				}
				
				int x = cur.x;
				int y = cur.y;
				int sharksize = cur.sharksize;
				eatcount++;
				map[x][y] =0;
				// 상어 크기 증가
				if(eatcount == sharksize) {								
					eatcount=0;
					sharksize++;
				}
				ans += move;
				move=0;
				q.clear();
				q.add(new Data(x,y,sharksize));
				visited = new boolean[N][N];
				fish.clear();
			}
		}
	}

	static class Data {
		int x;
		int y;
		int sharksize;
		
		public Data(int x, int y, int sharksize) {
			this.x = x;
			this.y = y;
			this.sharksize = sharksize;
		}	
	}
}
