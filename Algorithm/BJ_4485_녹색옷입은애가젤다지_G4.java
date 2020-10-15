package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_4485_녹색옷입은애가젤다지_G4 {
	static int ans, N;
	static int[][] map, dijk;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		while (true) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			// 중지
			if (N == 0) {
				break;
			}
			map = new int[N][N]; // 좌표의 값 저장
			dijk = new int[N][N]; // 최소비용 저장

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dijk[i][j] = Integer.MAX_VALUE; // 최대값으로 초기화 해줌
				}
			}
			
			bfs(0,0);
			System.out.println("Problem " + t++ + ": " + dijk[N-1][N-1]);
		}
	}

	public static void bfs(int startx, int starty) {
		PriorityQueue<Data> pq = new PriorityQueue<>();
		dijk[startx][starty] = map[startx][starty];
		pq.add(new Data(startx,starty,map[startx][starty]));
		
		while(!pq.isEmpty()) {
			Data cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				// 기존의 가중치보다 작을 경우
				if(dijk[nx][ny] > dijk[x][y] + map[nx][ny]) {
					// 값 저장
					dijk[nx][ny] = dijk[x][y] + map[nx][ny];
					pq.add(new Data(nx,ny,dijk[nx][ny]));
				}
			}
		}
	}
	
	static class Data implements Comparable<Data> {
		int x;
		int y;
		int score;
		public Data(int x, int y, int score) {
			this.x = x;
			this.y = y;
			this.score = score;
		}
		@Override
		public int compareTo(Data o) {
			// 오름차순 정렬
			return this.score - o.score;
		}
		
		
	}
}
