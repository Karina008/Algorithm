package algo_study_05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_결혼식_5567_S1 {
	static int n, m, ans = 0;
	static int[][] map;
	static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		map = new int[n + 1][n + 1];
		visit = new boolean[n + 1];

		for (int i = 0; i < m; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			map[start][end] = 1;
			map[end][start] = 1;
		}
		bfs();
		System.out.println(ans);
	}

	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visit[1] = true;
		for (int t = 0; t < 2; t++) {
			int size = q.size();
			for(int z=0; z<size; z++) {
				int cur = q.poll();

				for (int i = 1; i <= n; i++) {
					if (!visit[i] && map[cur][i] == 1) {
						q.add(i);
						visit[i] = true;
						ans++;
					}
				}
			}
		}

	}
}
