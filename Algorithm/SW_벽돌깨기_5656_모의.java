package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_벽돌깨기_5656_모의 {
	static int N, W, H;
	static int[][] map, tempmap;
	static int[] arr, check;
	static boolean[][] visit;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int ans = Integer.MAX_VALUE, count_num = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			arr = new int[N];
			ans = Integer.MAX_VALUE;
			

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			make(0);

			System.out.println("#" + t + " " + ans);

		}
	}

	public static void make(int idx) {
		if (idx == N) {
			sol();
		} else {
			for (int i = 0; i < W; i++) {
				arr[idx] = i;
				make(idx + 1);
			}
		}
	}

	public static void sol() {
		tempmap = new int[H][W];
		count_num = 0;
		for (int i = 0; i < H; i++) { // 맵복사
			for (int j = 0; j < W; j++) {
				tempmap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < N; i++) { // N번 떨어뜨림
			visit = new boolean[H][W];
			check = new int[W];
			dropball(arr[i]);
			down();
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (tempmap[i][j] > 0) {
					count_num++;
				}
			}
		}
		ans = Math.min(ans, count_num);
	}

	public static void dropball(int dropindex) {
		int p = 0;
		while (p < H) {
			if (tempmap[p][dropindex] > 0) {
				if (tempmap[p][dropindex] == 1) {
					tempmap[p][dropindex] = 0;
					check[dropindex] = 1;
				} else {
					bfs(p, dropindex);
				}
				break;
			}
			p++;
		}
	}

	public static void bfs(int x, int y) {
		Queue<Data> q = new LinkedList<>();
		visit[x][y] = true;
		q.add(new Data(x, y, tempmap[x][y]));
		tempmap[x][y] = 0;
		check[y] = 1;
		while (!q.isEmpty()) {
			Data cur = q.poll();
			int curx = cur.x;
			int cury = cur.y;
			int num = cur.num;

			for (int k = 0; k < 4; k++) { // 4방향
				for (int p = 1; p < num; p++) { // 한 방향으로 num 만큼
					int nx = curx + dx[k] * p;
					int ny = cury + dy[k] * p;
					if (0 <= nx && nx < H && 0 <= ny && ny < W && tempmap[nx][ny] != 0 && !visit[nx][ny]) {
						q.add(new Data(nx, ny, tempmap[nx][ny]));
						visit[nx][ny] = true;
						tempmap[nx][ny] = 0;
						check[ny] = 1;
					}
				}
			}
		}
	}

	public static void down() {
		for (int i = 0; i < W; i++) {
			if (check[i] == 1) {
				for (int j = H - 1; j > 0; j--) {
					if (tempmap[j][i] == 0 ) {
						int idx=0;
						while(tempmap[j - 1+idx][i] == 0 && j - 1+idx>0) {
							idx--;
						}
						tempmap[j][i] = tempmap[j - 1+idx][i];
						tempmap[j - 1+idx][i] = 0;
					}
				}
			}
		}
	}
	
	public static class Data {
		int x;
		int y;
		int num;
		public Data(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
}
