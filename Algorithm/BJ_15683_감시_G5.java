package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15683_감시_G5 {
	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] map;
	static ArrayList<CCTV> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					list.add(new CCTV(i, j, map[i][j]));
				}
			}
		}
		dfs(0, map);
		System.out.println(ans);
	}

	// 상 우 하 좌
	public static void dfs(int cctvindex, int[][] premap) {
		int[][] visited = new int[N][M];
		// 모든 cctv 조사함
		if (cctvindex == list.size()) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (premap[i][j] == 0) {
						count++;
					}
				}
			}
			ans = Math.min(ans, count);
		} else {
			// 각 cctv의 index를 가져와 맵을 채움
			CCTV cur = list.get(cctvindex);
			int x = cur.x;
			int y = cur.y;
			int idx = cur.v;

			switch (idx) {
			case 1:
				for (int k = 0; k < 4; k++) {
					copyarr(visited, premap);
					fillMap(x, y, k, visited);
					dfs(cctvindex + 1, visited);
				}
				break;
			case 2:
				for (int k = 0; k < 2; k++) {
					copyarr(visited, premap);
					fillMap(x, y, k, visited);
					fillMap(x, y, k + 2, visited);
					dfs(cctvindex + 1, visited);
				}
				break;
			case 3:
				for (int k = 0; k < 4; k++) {
					copyarr(visited, premap);
					fillMap(x, y, k, visited);
					fillMap(x, y, (k + 1) % 4, visited);
					dfs(cctvindex + 1, visited);
				}
				break;
			case 4:
				for (int k = 0; k < 4; k++) {
					copyarr(visited, premap);
					fillMap(x, y, k, visited);
					fillMap(x, y, (k + 1) % 4, visited);
					fillMap(x, y, (k + 2) % 4, visited);
					dfs(cctvindex + 1, visited);
				}
				break;
			case 5:
				copyarr(visited, premap);
				fillMap(x, y, 0, visited);
				fillMap(x, y, 1, visited);
				fillMap(x, y, 2, visited);
				fillMap(x, y, 3, visited);
				dfs(cctvindex + 1, visited);

				break;
			}
		}
	}

	// 해당 방향으로 값을 채워줌
	public static void fillMap(int x, int y, int index, int[][] visited) {
		switch (index) {
		case 0: // 상
			for (int k = x; k >= 0; k--) {
				if (map[k][y] == 6) {
					break;
				}
				visited[k][y] = 7;
			}
			break;
		case 1: // 우
			for (int k = y; k < M; k++) {
				if (map[x][k] == 6) {
					break;
				}
				visited[x][k] = 7;
			}
			break;
		case 2: // 하
			for (int k = x; k < N; k++) {
				if (map[k][y] == 6) {
					break;
				}
				visited[k][y] = 7;
			}
			break;
		case 3: // 좌
			for (int k = y; k >= 0; k--) {
				if (map[x][k] == 6) {
					break;
				}
				visited[x][k] = 7;
			}
			break;
		}

	}

	// 이전 배열의 값을 복사해줌
	public static void copyarr(int[][] visited, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = map[i][j];
			}
		}
	}

	static class CCTV {
		int x;
		int y;
		int v;

		public CCTV(int x, int y, int v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}

	}
}
