package algo_study_06;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_게리맨더링_17471_G5 {
	static int N, Min = Integer.MAX_VALUE;
	static int[][] map;
	static int[] people, area;
	static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		people = new int[N];
		area = new int[N];

		for (int i = 0; i < N; i++) {
			people[i] = sc.nextInt();
		}
		for (int j = 0; j < N; j++) {
			int m = sc.nextInt();
			for (int i = 0; i < m; i++) {
				int to = sc.nextInt() - 1;
				map[j][to] = 1;
				map[to][j] = 1;
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		dfs(0);
		if (Min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(Min);
		}
	}

	public static void dfs(int cnt) {
		if (cnt == N) {
			int area1 = 0, area2 = 0;
			for (int i = 0; i < N; i++) {
				if (area[i] == 1) {
					area1 += people[i];
				} else {
					area2 += people[i];
				}
			}
			visit = new boolean[N];
			int ans = 0;
			for (int i = 0; i < N; i++) {
				if (!visit[i]) {
					checkArea(i, area[i]);
					ans++;
				}
			}
//			System.out.println(ans);
			if (ans == 2) {
				Min = Math.min(Min, Math.abs(area1 - area2));
			}
			return;
		}

		area[cnt] = 1;
		dfs(cnt + 1);
		area[cnt] = 2;
		dfs(cnt + 1);
	}

	public static void checkArea(int index, int num) {
		visit[index] = true;
		for (int i = 0; i < N; i++) {
			if (map[index][i] == 1 && !visit[i] && area[i] == num) {
				checkArea(i, num);
			}
		}
	}
}
