package algo_study_05;

import java.util.Arrays;
import java.util.Scanner;

public class SW_4013_특이한자석 {

	static int[][] map = new int[4][8];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int K = sc.nextInt(); // 회전 횟수
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 8; j++) {
					map[i][j] = sc.nextInt(); // N: 0, S: 1
				}
			}

			for (int k = 0; k < K; k++) {
				int target = sc.nextInt();
				int d = sc.nextInt(); // 1: 시계방향 , -1: 반시계방향

				sol(target-1, d);
			}
			int ans = 0;
//			for (int i = 0; i < 4; i++) {
//				//System.out.print(map[i][0]+ " ");
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			
//			System.out.println();
			for (int i = 0; i < 4; i++) {
				ans += map[i][0] * Math.pow(2, i);
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	public static void sol(int target, int d) {
		left(target - 1, -d);
		right(target + 1, -d);
		rotate(target, d);

	}

	public static void left(int target, int d) {
		if (target < 0)
			return;
		if (map[target][2] != map[target + 1][6]) {
			left(target - 1, -d);
			rotate(target, d);
		}
	}

	public static void right(int target, int d) {
		if (target > 3)
			return;
		if (map[target - 1][2] != map[target][6]) {
			right(target + 1, -d);
			rotate(target, d);
		}
	}

	public static void rotate(int target, int d) {
		if (d == 1) { // 시계 방향
			int temp = map[target][7];

			for (int i = 7; i > 0; i--)
				map[target][i] = map[target][i - 1];
			map[target][0] = temp;

		} else if (d == -1) {// 반시계 방향
			int temp = map[target][0];

			for (int i = 0; i < 7; i++)
				map[target][i] = map[target][i + 1];
			map[target][7] = temp;

		}
	}
}
