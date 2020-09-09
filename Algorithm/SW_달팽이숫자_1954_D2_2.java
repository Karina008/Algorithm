package algo_study_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW_달팽이숫자_1954_D2_2 {

	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			int array[][] = new int[N][N];
			array[0][0] = 1;
			if (N == 1) {
				System.out.println("#" + testcase);
				System.out.println(1);
				continue;
			}

			int nx = 0;
			int ny = 0;

			int count = 1;
			int number = N - 1;
			int temp = 0;

			L: while (true) {
				for (int i = 0; i < 4; i++) {
					temp++;
					for (int j = 0; j < number; j++) {
						nx += dx[i];
						ny += dy[i];
						array[nx][ny] = ++count;
						if (count == N * N)
							break L;
					}
					if (temp == 3)
						number--;
					else if (temp > 3 && temp % 2 == 1)
						number--;
				}
			}

			System.out.println("#" + testcase);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(array[i][j] + " ");
				}
				System.out.println();
			}

		}

	}
}