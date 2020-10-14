package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19238_스타트택시_G4 {
	static int N, M, fuel, ans;
	static int[][] map, destination;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[][] visited;
	static boolean[] finish;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도 크기
		M = Integer.parseInt(st.nextToken()); // 출발지의 행 열번호, 목적지의 행 열번호
		fuel = Integer.parseInt(st.nextToken()); // 연료

		map = new int[N][N];
		destination = new int[M][2];
		finish = new boolean[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1; // 시작X
		int startY = Integer.parseInt(st.nextToken()) - 1; // 시작Y

		int peoplenum = 2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = peoplenum;
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			destination[peoplenum - 2][0] = x;
			destination[peoplenum - 2][1] = y;
			peoplenum++;
		}
		ans = -1;
		bfs(startX, startY, fuel);
		System.out.println(ans);
	}

	public static void bfs(int startX, int startY, int f) {
		Queue<Data> q = new LinkedList<>();
		LinkedList<Data> list = new LinkedList<>();

		visited = new boolean[N][N];

		int count = 0;
		q.add(new Data(startX, startY, f, 0, map[startX][startY]));

		while (!q.isEmpty()) {
			int size = q.size();
			boolean newstart = false;
			for (int p = 0; p < size; p++) {
				Data cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				int fuel = cur.fuel;
				int move = cur.move;
				int customer = cur.customer;

				for (int k = 0; k < 4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1 || visited[nx][ny] || fuel <= 0) {
						continue;
					}

					if (customer > 1) {
						// 승객 태운 상태
						if (destination[customer - 2][0] == nx && destination[customer - 2][1] == ny) {
							// 목적지 도착
							visited = new boolean[N][N];
							visited[nx][ny] = true;
							q.clear();
							
							// 도착 지점에 승객이 있을 때
							if (map[nx][ny] > 1 && !finish[map[nx][ny] - 2]) {
								q.add(new Data(nx, ny, fuel + (move + 1) * 2 - 1, 0, map[nx][ny]));
							} else {
								q.add(new Data(nx, ny, fuel + (move + 1) * 2 - 1, 0, 0));
							}
							newstart = true;
							finish[customer - 2] = true;
							count++;
							if (count == M) {
								ans = fuel + (move + 1) * 2 - 1;
								return;
							}
							break;
						} else {
							// 벽 아닐 때
							q.add(new Data(nx, ny, fuel - 1, move + 1, customer));
							visited[nx][ny] = true;
						}

					} else {
						// 승객 안태움
						if (map[nx][ny] > 1 && !finish[map[nx][ny] - 2]) {
							// 승객 발견
							list.add(new Data(nx, ny, fuel - 1, 0, map[nx][ny]));
							visited[nx][ny] = true;
						} else {
							// 빈 공간
							q.add(new Data(nx, ny, fuel - 1, 0, 0));
							visited[nx][ny] = true;
						}
					}
				}
				if (newstart) {
					break;
				}
			}
			int index = 0;
			// 발견 승객 중 우선순위를 큐에 넣어줌
			for (int i = 1; i < list.size(); i++) {
				if (list.get(index).x > list.get(i).x) {
					index = i;
				} else if (list.get(index).x == list.get(i).x) {
					if (list.get(index).y > list.get(i).y) {
						index = i;
					}
				}
			}
			if (list.size() > 0) {
				int x = list.get(index).x;
				int y = list.get(index).y;
				int fuel2 = list.get(index).fuel;
				int customer = list.get(index).customer;
				visited = new boolean[N][N];
				visited[x][y] = true;
				q.clear();
				q.add(new Data(x, y, fuel2, 0, customer));
				list.clear();
			}
		}
	}

	static class Data {
		int x;
		int y;
		int fuel;
		int move;
		int customer;

		public Data(int x, int y, int fuel, int move, int customer) {
			super();
			this.x = x;
			this.y = y;
			this.fuel = fuel;
			this.move = move;
			this.customer = customer;
		}
	}
}
