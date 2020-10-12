package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_2383_점심식사시간_모의_F {
	static int ans = 0, N, people = 0, stair1_x, stair1_y, stair2_x, stair2_y;
	static int[][] map;
	static boolean[] arr;
	static LinkedList<Data> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new LinkedList<Data>();
			ans = Integer.MAX_VALUE;

			boolean findstair = false;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
//						people++;
						list.add(new Data(i, j, 0, 0));
					} else if (map[i][j] > 1) {
						if (!findstair) {
							stair1_x = i;
							stair1_y = j;
							findstair = true;
						} else {
							stair2_x = i;
							stair2_y = j;
						}
					}

				}
			}
//			System.out.println(stair1_x);
//			System.out.println(stair1_y);
//			System.out.println(stair2_x);
//			System.out.println(stair2_y);
			people = list.size();
//			System.out.println(people);
			arr = new boolean[people];
			make(0);
			System.out.println("#" + t + " " + ans);
		}
	}

	static void time() {
		Queue<Data> stair1 = new LinkedList<>();
		Queue<Data> stair2 = new LinkedList<>();
		for (int i = 0; i < people; i++) {
			if (!arr[i]) {
				int dist = Math.abs(stair1_x - list.get(i).x) + Math.abs(stair1_y - list.get(i).y);
				stair1.add(new Data(stair1_x, stair1_y, dist, map[stair1_x][stair1_y]));
			} else {
				int dist = Math.abs(stair2_x - list.get(i).x) + Math.abs(stair2_y - list.get(i).y);
				stair2.add(new Data(stair2_x, stair2_y, dist, map[stair2_x][stair2_y]));
			}
		}
		int curtime = 1;
		int count = 0;

//		System.out.println("사이즈 : "+stair1.size() + " " + stair2.size() );
		while (true) {
//			System.out.println("사이즈 : "+stair1.size() + " " + stair2.size() + " " + curtime);

			int down1 = 0;
			int size1 = stair1.size();
//			if(size1 > 3) {
//				size1 = 3;
//			}
			for (int i = 0; i < size1; i++) {
				if (down1 == 3) {
					break;
				}
				if (!stair1.isEmpty()) {
					Data cur = stair1.poll();
					int x = cur.x;
					int y = cur.y;
					int dist = cur.dist;
					int stair = cur.stair;

//					System.out.println("1: " + cur.toString());
					if (dist != 0) {
						dist--;
					} else if (stair != 0 && down1 < 3) {
						stair--;
						if (stair != 0) {
							down1++;
						}
					}

					if (dist != 0 || stair != 0) {
						stair1.add(new Data(x, y, dist, stair));
					}
				}
			}

			int down2 = 0;
			int size2 = stair2.size();
			if (size2 > 3) {
				size2 = 3;
			}
			for (int i = 0; i < size2; i++) {
				if (down2 == 3) {
					break;
				}
				if (!stair2.isEmpty()) {
					Data cur = stair2.poll();
					int x = cur.x;
					int y = cur.y;
					int dist = cur.dist;
					int stair = cur.stair;
//					System.out.println("2: " + cur.toString());

					if (dist != 0) {
						dist--;
					} else if (stair != 0) {
						stair--;
						if (stair != 0) {
							down2++;
						}
					}

					if (dist != 0 || stair != 0) {
						stair2.add(new Data(x, y, dist, stair));
					}
				}
			}
			if (stair1.isEmpty() && stair2.isEmpty()) {
//				System.out.println(ans + " " + (curtime+1));
				ans = Math.min(ans, ++curtime);
				return;
			}
			curtime++;
		}
	}

	static void make(int index) {
		if (index == people) {
//			ans++;
//			System.out.println();
//			System.out.println(Arrays.toString(arr));
			time();
			return;
		}
		make(index + 1);
		arr[index] = true;
		make(index + 1);
		arr[index] = false;
	}

	static class Data {
		int x;
		int y;
		int dist;
		int stair;

		public Data(int x, int y, int dist, int stair) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.stair = stair;
		}

		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + ", dist=" + dist + ", stair=" + stair + "]";
		}

	}
}
