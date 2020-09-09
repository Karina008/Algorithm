package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_안전영역_2468_S1 {
	static int N, Max = Integer.MIN_VALUE, high = 0, Max_high=Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > Max_high) {
					Max_high = map[i][j];
				}
			}
		}

//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		for (; high <= Max_high; high++) {
			int count = 0;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > high &&!visit[i][j] ) {
						dfs(i, j);
						count++;
					}
				}
			}
			if (Max < count) {
				Max = count;
			}
		}
		System.out.println(Max);
	}

	public static void dfs(int x, int y) {
		visit[x][y] = true;

		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (!visit[nx][ny] && map[nx][ny] > high)
					dfs(nx, ny);
			}
		}
	}

}
