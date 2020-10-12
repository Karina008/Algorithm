package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SW_5650_핀볼게임_모의_F {
	static int ans, N, startX, startY;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dy = { 0, 1, 0, -1 };
	static LinkedList<Data> hollList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;

			map = new int[N][N];
			hollList = new LinkedList<Data>();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6 && map[i][j] <= 10) {
						hollList.add(new Data(i, j, map[i][j]));
					}
				}
			}

			for (int i = 0; i < N; i++) {
				startX = i;
				for (int j = 0; j < N; j++) {
					startY = j;
					if (map[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
//							if((k==0 && i==0) || (k==1 && j==N-1) || (k==2 && i==N-1) || (k==3 && j==0))  {
//								continue;
//							}
							System.out.println("시작 : " + i + " " + j + " " + k);
							simul(i, j, k);
						}
					}
				}
			}

			System.out.println("#" + t + " " + ans);
		}
	}

	public static void simul(int x, int y, int way) {
		int count = 0;
		while (true) {
			int nx = x + dx[way];
			int ny = y + dy[way];
			System.out.println(nx + " " + ny + " " + way + " " + count);
//			if(count>10) {
//				try {
//					Thread.sleep(count*100000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//				
//			}
			
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (map[nx][ny] == -1 || (startX == nx && startY == ny)) {
					// 블랙홀에 빠지거나 출발위치로 돌아오면 끝
					ans = Math.max(ans, count);
					System.out.println("끝 : " + ans + " " + count);
					return;
				} else if (1 <= map[nx][ny] && map[nx][ny] <= 5) {
					// 블록
					way = block(way, map[nx][ny]);
					count++;
					x = nx;
					y = ny;
				} else if (6 <= map[nx][ny] && map[nx][ny] <= 10) {
					// 웜홀
					int hollnum = map[nx][ny];
					for (int i = 0; i < hollList.size(); i++) {
						if (hollList.get(i).num == hollnum) {
							if (!(hollList.get(i).x == nx && hollList.get(i).y == ny)) {
								x = hollList.get(i).x;
								y = hollList.get(i).y;
								break;
							}
						}
					}
				} else {
					// 빈공간
					x = nx;
					y = ny;
				}
			} else {
				// 벽에 부딪힘
				way = (way + 2) % 4;
				if(map[x][y] >=1 && map[x][y] <=5) {
					way = block(way, map[x][y]);
				}else if (6 <= map[x][y] && map[x][y] <= 10) {
//					System.out.println("벽앞에 홀" + x + " " + y);
					// 웜홀
					
					int hollnum = map[x][y];
					for (int i = 0; i < hollList.size(); i++) {
						if (hollList.get(i).num == hollnum) {
							if (hollList.get(i).x != x || hollList.get(i).y != y) {
								x = hollList.get(i).x;
								y = hollList.get(i).y;
//								System.out.println("벽앞에 업데이트" + x + " " + y);
								break;
							}
						}
					}
				}
				count++;
				if (map[x][y] == -1 || (startX == x && startY == y)) {
					// 블랙홀에 빠지거나 출발위치로 돌아오면 끝
					ans = Math.max(ans, count);
					System.out.println("끝 : " + ans + " " + count);
					return;
				}
			}
		}
	}

	public static int block(int way, int blocknum) {
		switch (blocknum) {
		case 1:
			if (way == 0) {
				way = 2;
			} else if (way == 1) {
				way = 3;
			} else if (way == 2) {
				way = 1;
			} else {
				way = 0;
			}
			break;
		case 2:
			if (way == 0) {
				way = 1;
			} else if (way == 1) {
				way = 3;
			} else if (way == 2) {
				way = 0;
			} else {
				way = 2;
			}
			break;
		case 3:
			if (way == 0) {
				way = 3;
			} else if (way == 1) {
				way = 2;
			} else if (way == 2) {
				way = 0;
			} else {
				way = 1;
			}
			break;
		case 4:
			if (way == 0) {
				way = 2;
			} else if (way == 1) {
				way = 0;
			} else if (way == 2) {
				way = 3;
			} else {
				way = 1;
			}
			break;
		case 5:
			if (way == 0) {
				way = 2;
			} else if (way == 1) {
				way = 3;
			} else if (way == 2) {
				way = 0;
			} else {
				way = 1;
			}
			break;
		}
		return way;
	}

	static class Data {
		int x;
		int y;
		int num;

		public Data(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

	}
}
