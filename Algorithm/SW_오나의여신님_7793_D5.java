package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_오나의여신님_7793_D5 {
	static int N, M, end_x, end_y, ans;
	static char[][] map;
	static boolean[][] visit;
	static ArrayList<Data> list;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static Queue<Data> q; // S 이동하는 큐
	static Queue<Data> q2; // 바이러스 이동하는 큐

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			visit = new boolean[N][M];
			q = new LinkedList<>();
			q2 = new LinkedList<>();
			ans = Integer.MAX_VALUE;
			list = new ArrayList<Data>();

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == 'S') {
						q.add(new Data(i, j, 1));
					} else if (map[i][j] == '*') {
						q2.add(new Data(i, j, 0));
					}
				}
			}

			bfs();
			if (ans == Integer.MAX_VALUE) {
				System.out.println("#" + t + " GAME OVER");
			} else {
				System.out.println("#" + t + " " + ans);
			}
		}
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			int q_size = q.size();
			for (int w = 0; w < q_size; w++) {
				// S 이동 시작
				Data cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				int cnt = cur.cnt;

				for (int k = 0; k < 4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					if (0 <= nx && nx < N && 0 <= ny && ny < M && !visit[nx][ny]) {
						if (map[nx][ny] == '.') {
							// 이동
							map[x][y] = '.';
							map[nx][ny] = 'S';
							visit[nx][ny] = true;
							list.add(new Data(nx, ny, cnt + 1));
						} else if (map[nx][ny] == 'D') {
							// 종료
							ans = cnt;
							return;
						}
					}
				}
			}

				// 바이러스 퍼짐 시작
				int size = q2.size();
				for (int z = 0; z < size; z++) {
					Data cur2 = q2.poll();
					int x2 = cur2.x;
					int y2 = cur2.y;
					for (int k = 0; k < 4; k++) {
						int nx2 = x2 + dx[k];
						int ny2 = y2 + dy[k];
						if (0 <= nx2 && nx2 < N && 0 <= ny2 && ny2 < M) {
							if (map[nx2][ny2] == '.') {
								// 이동
								map[nx2][ny2] = '*';
								q2.add(new Data(nx2, ny2, 0));
							} else if (map[nx2][ny2] == 'S') {
								// 게임오버 해당하는 인덱스의 S 리스트에서 삭제
								int size_list = list.size();
								for (int p = 0; p < size_list; p++) {
									if (list.get(p).x == nx2 && list.get(p).y == ny2) {
										list.remove(p);
										break;
									}
								}
								map[nx2][ny2] = '*';
								q2.add(new Data(nx2, ny2, 0));
							}
						}
					}
				}

				// S의 리스트를 S 큐에 넣어줌
				int size_list = list.size();
				for (int p = 0; p < size_list; p++) {
					q.add(new Data(list.get(p).x, list.get(p).y, list.get(p).cnt));
				}
				list.clear();
			}
		
	}

}

class Data {
	int x;
	int y;
	int cnt;

	public Data(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

}
