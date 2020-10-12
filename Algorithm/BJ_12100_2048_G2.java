package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_12100_2048_G2 {
	static int N, ans = 0;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		find(map, 0);
		System.out.println(ans);
	}

	static void find(int[][] premap, int count) {
		if (count == 5) {
			// 5번 이동 완료
			int maxNum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					maxNum = Math.max(maxNum, premap[i][j]);
				}
			}
			ans = Math.max(maxNum, ans);
			return;
		}
		int[][] nowmap = new int[N][N];

		// 이전에 받은 배열을 깊은 복사를 해준 후
		// 상 우 하 좌 방향으로 이동 후 함수 재호출 
		for(int k=0; k<4; k++) {
			copyarr(nowmap,premap);
			move(k,nowmap);
			find(nowmap, count+1);
		}
	}

	public static void copyarr(int[][] nowmap, int[][] premap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nowmap[i][j] = premap[i][j];
			}
		}
	}
	public static void move(int dir, int[][] map) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		switch (dir) {
		case 0:
			// 상
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if (map[i][j] != 0) {
						list.add(map[i][j]);
					}
				}
				int index = 0;
				for (int p = 0; p < list.size(); p++) {
					if (p == list.size() - 1) {
						map[index++][j] = list.get(list.size() - 1);
					} else {
						if (list.get(p).equals(list.get(p + 1))) {
							map[index++][j] = list.get(p) * 2;
							p++;
						} else {
							map[index++][j] = list.get(p);
						}
					}
				}
				
				//남은 자리 0으로 바꿔줌
				for (int p = index; p < N; p++) {
					map[p][j] = 0;
				}
				list.clear();
			}
			break;
		case 1:
			// 우
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0) {
						list.add(map[i][j]);
					}
				}
				int index = N - 1;
				for (int p = 0; p < list.size(); p++) {
					if (p == list.size() - 1) {
						map[i][index--] = list.get(list.size() - 1);
					} else {
						if (list.get(p).equals(list.get(p + 1))) {
							map[i][index--] = list.get(p) * 2;
							p++;
						} else {
							map[i][index--] = list.get(p);
						}
					}
				}

				//남은 자리 0으로 바꿔줌
				for (int p = index; p >= 0; p--) {
					map[i][p] = 0;
				}
				list.clear();
			}
			break;
		case 2:
			// 하
			for (int j = 0; j < N; j++) {
				for (int i = N-1; i >=0; i--) {
					if (map[i][j] != 0) {
						list.add(map[i][j]);
					}
				}
				int index = N-1;
				for (int p = 0; p < list.size(); p++) {
					if (p == list.size() - 1) {
						map[index--][j] = list.get(list.size() - 1);
					} else {
						if (list.get(p).equals(list.get(p + 1))) {
							map[index--][j] = list.get(p) * 2;
							p++;
						} else {
							map[index--][j] = list.get(p);
						}
					}
				}
				
				//남은 자리 0으로 바꿔줌
				for (int p = index; p >=0; p--) {
					map[p][j] = 0;
				}
				list.clear();
			}
			break;
		case 3:
			// 좌
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) {
						list.add(map[i][j]);
					}
				}
				int index = 0;
				for (int p = 0; p < list.size(); p++) {
					if (p == list.size() - 1) {
						map[i][index++] = list.get(list.size() - 1);
					} else {
						if (list.get(p).equals(list.get(p + 1))) {
							map[i][index++] = list.get(p) * 2;
							p++;
						} else {
							map[i][index++] = list.get(p);
						}
					}
				}

				//남은 자리 0으로 바꿔줌
				for (int p = index; p <N; p++) {
					map[i][p] = 0;
				}
				list.clear();
			}
			break;
		}
	}

}
