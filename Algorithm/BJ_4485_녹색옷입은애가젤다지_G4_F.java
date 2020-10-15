package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_4485_녹색옷입은애가젤다지_G4_F {
	static int ans, N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		while (true) {

//			ans = Integer.MAX_VALUE;
			ans = 0;
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans += map[i][j];
				}
			}
			
			visited[0][0] = true;
			dfs(0, 0, map[0][0]);
			System.out.println("Problem " + t++ + ": " + ans);
		}
	}

	public static void dfs(int x, int y, int score) {
		
//		System.out.println(x + " " + y + " " + score);
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];

//			System.out.println(nx + " " + ny);
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
//				System.out.println("패스");
				continue;
			}

			if (nx == N - 1 && ny == N - 1) {
				// 목적지 도착
				ans = Math.min(ans, score + map[nx][ny]);
//				System.out.println("목적지 도착 : " + (score + map[nx][ny]));
				return;
			} else {
				if (score + map[nx][ny] > ans) {
					continue;
				}
				visited[x][y] = true;
				dfs(nx, ny, score + map[nx][ny]);
				visited[x][y] = false;

			}
		}
	}
}
