package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19952_인성문제있어_G4 {
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int W,H,endX, endY;
	static boolean find;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st =  new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 세로
			W = Integer.parseInt(st.nextToken()); // 가로
			int O = Integer.parseInt(st.nextToken()); // 장애물 개수
			int F = Integer.parseInt(st.nextToken()); // 초기힘
			int startX = Integer.parseInt(st.nextToken()); // 시작점
			int startY = Integer.parseInt(st.nextToken()); // 초기힘
			endX = Integer.parseInt(st.nextToken()); // 초기힘
			endY = Integer.parseInt(st.nextToken()); // 초기힘
			
			map = new int[H+1][W+1];
			visit = new boolean[H+1][W+1];
			find = false;
			for(int i=0; i<O; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				map[x][y] = v;
			}
			
//			for(int i=0; i<= H; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

			bfs(startX, startY, F);
			if(find) {
				System.out.println("잘했어!!");
			}else {
				System.out.println("인성 문제있어??");
			}
		}
	}
	public static void bfs(int startX, int startY, int F) {
		Queue<Data> q = new LinkedList<>();
		visit[startX][startY] = true;
		q.add(new Data(startX, startY, F));
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int f = cur.F;
			
			if((x == endX && y == endY)) {
				find=true;
				return;
			}
			// 이동 불가
			if(f<=0) {
				return;
			}
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				if(0<nx && nx <=H && 0<ny && ny <=W && !visit[nx][ny]) {
					// 높은 곳으로 갈 때
					if(map[nx][ny]> map[x][y]) {
						// 남은 힘으로 이동 가능
						if(map[nx][ny] - map[x][y] <= f) {
							visit[nx][ny] = true;
							q.add(new Data(nx, ny, f-1));
						}					
					// 같거나 낮은곳으로 이동
					}else {
						visit[nx][ny] = true;
						q.add(new Data(nx, ny, f-1));
					}
				}
			}
		}
	}

	static class Data {
		int x;
		int y;
		int F;
		public Data(int x, int y, int f) {
			this.x = x;
			this.y = y;
			F = f;
		}
		
		
	}
}
