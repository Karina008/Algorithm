package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_홈방범서비스_2117_모의 {
	static int N, M, cost, home, K;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static Queue<Data> q = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visit = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans=0;
			for (K = 1; K <= N+1; K++) {
				cost = K*K + (K-1)*(K-1);
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						visit = new boolean[N][N];						
						home=0;
						visit[i][j] =true;
						if(map[i][j]==1)
							home++;
						q.add(new Data(i,j,0));
						bfs();
						if(home *M >= cost) {
							ans = Math.max(ans, home);
						}						
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			Data cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int count = cur.count;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (0 <= nx && nx < N && 0 <= ny && ny < N && !visit[nx][ny] && count < K-1) {
					visit[nx][ny] = true;
					q.add(new Data(nx, ny, count + 1));
					if(map[nx][ny] == 1) {
						home++;
					}
				}
			}
		}
	}

	static class Data {
		int x;
		int y;
		int count;
		
		public Data(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
	}
}

