package algo_study_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_Maaaaaaaaaze_16985_G3 {
	static int[][][] map = new int[5][5][5]; // 도형5개 / 행5/ 열5
	static int[][][] temp_map = new int[5][5][5]; // 도형5개 / 행5/ 열5

	static boolean[] makenum = new boolean[5];
	static int[] arr = new int[5];
	static int dx[] = { 0, 0, 1, 0, -1, 0 };
	static int dy[] = { 0, 0, 0, -1, 0, 1 };
	static int dz[] = { 1, -1, 0, 0, 0, 0 };
	static boolean[][][] visit;
	static int ans = Integer.MAX_VALUE;
	static Queue<Data> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int t = 0; t < 5; t++) { // 도형 5개
			for (int i = 0; i < 5; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 5; j++) {
					map[t][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		make(0);
		if (ans == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(ans);
	}

	public static void rotate(int idx) {
		int[][] temp = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				temp[j][4 - i] = temp_map[idx][i][j];
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				temp_map[idx][i][j] = temp[i][j];
			}
		}
	}

	public static void make(int idx) {
		if (idx == 5) { // 순열 생성
			for (int i = 0; i < 5; i++) {  //  순열로 만들어진 층으로 temp_map생성
				for (int j = 0; j < 5; j++) {
					for (int z = 0; z < 5; z++) {
						temp_map[i][j][z] = map[arr[i]][j][z];
					}
				}
			}

			solution();
			return;
		} else {
			for (int i = 0; i < 5; i++) {
				if (!makenum[i]) {
					makenum[i] = true;
					arr[idx] = i;
					make(idx + 1);
					makenum[i] = false;
				}
			}
		}
	}

	public static void solution() {
		if (temp_map[0][0][0] == 1 && temp_map[4][4][4] == 1) {
			bfs();
		}
		// 각 층마다 회전시키면서 돌리며 출구와 입구가 1일 때 bfs 호출
		for (int a = 0; a < 4; a++) {
			rotate(0);
			for (int b = 0; b < 4; b++) {
				rotate(1);
				for (int c = 0; c < 4; c++) {
					rotate(2);
					for (int d = 0; d < 4; d++) {
						rotate(3);
						for (int e = 0; e < 4; e++) {
							rotate(4);
							if (temp_map[0][0][0] == 1 && temp_map[4][4][4] == 1) {
								bfs();
							}
						}
					}
				}
			}
		}
	}

	public static void bfs() {
		visit = new boolean[5][5][5];
		q.clear();
		q.add(new Data(0, 0, 0, 0));
		visit[0][0][0] = true;
		while (!q.isEmpty()) {
			Data cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int z = cur.z;
			int cnt = cur.cnt;
			if(cnt >= ans)
				return;
			for (int k = 0; k < 6; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				int nz = z + dz[k];
				if (0 <= nx && nx < 5 && 0 <= ny && ny < 5 && 0 <= nz && nz < 5) {
					if (!visit[nx][ny][nz] && temp_map[nx][ny][nz] == 1) {
						if (nx == 4 && ny == 4 && nz == 4) {
							ans = Math.min(ans, cnt + 1);
						}
						q.add(new Data(nx, ny, nz, cnt + 1));
						visit[nx][ny][nz] = true;
					}
				}
			}
		}
	}

	static class Data {
		int x;
		int y;
		int z;
		int cnt;

		public Data(int x, int y, int z, int cnt) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.cnt = cnt;
		}

	}
}
