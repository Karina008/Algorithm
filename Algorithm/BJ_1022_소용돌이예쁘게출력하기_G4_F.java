package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1022_소용돌이예쁘게출력하기_G4 {
	static int[][] map;
	static int num;
	static int r1, c1, r2, c2, row, col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());

		int temp1 = 0, temp2, N;
		temp1 = Math.max(Math.abs(r1), Math.abs(c1));
		temp2 = Math.max(Math.abs(r2), Math.abs(c2));
		N = Math.max(Math.abs(temp1), Math.abs(temp2));
		row = Math.abs(r1 - r2) + 1;
		col = Math.abs(c1 - c2) + 1;
//		System.out.println(row);
//		System.out.println(col);
		N = N * 2 + 1;
		int mid = N / 2 + 1;
//		System.out.println(N);
//		System.out.println(mid);
		map = new int[row+1][col+1];

		num = 1;
		if (isin(mid, mid))
			map[mid][mid] = num;
		num++;
		if (isin(mid, mid + 1))
			map[mid][mid + 1] = num;
		num++;
		if (isin(mid - 1, mid + 1))
			map[mid - 1][mid + 1] = num;
		num++;
		if (isin(mid - 1, mid))
			map[mid - 1][mid] = num;
		num++;
		if (isin(mid - 1, mid - 1))
			map[mid - 1][mid - 1] = num;
		num++;
		if (isin(mid, mid - 1))
			map[mid][mid - 1] = num;
		num++;
		if (isin(mid + 1, mid - 1))
			map[mid + 1][mid - 1] = num;
		num++;

		makemap(mid, N);
//		printarr(N);

		int len = 0;
//		System.out.println(num);
		while (num > 0) {
			num /= 10;
			len++;
		}
//		System.out.println(len);
		for (int i = 1; i <= Math.abs(r2-r1)+1; i++) {
			for (int j = 1; j <= Math.abs(c2-c1)+1; j++) {
				System.out.format("%" + len + "d ", map[i][j]);
			}
			System.out.println();
		}
//		for (int i = mid + r1; i <= mid + r2; i++) {
//			for (int j = mid + c1; j <= mid + c2; j++) {
//				System.out.format("%" + len + "d ", map[i][j]);
//			}
//			System.out.println();
//		}

	}

	static boolean isin(int i, int j) {
		if (0 <= i && i <= row && 0 <= j && j <= col) {
			return true;
		}
		return false;
	}

	static void makemap(int mid, int N) {
		int index1 = 1;
		int index2 = 0;
		while (num < N * N) {
//		while(num <= 49) {
			for (int j = mid - index2; j <= mid + index1 + 1; j++) {
				if (isin(mid + index1, j)) {
					map[mid + index1][j] = num;
				}
				if (++num == N * N + 1) {
					return;
				}
			}
			index1++;
			for (int i = mid + index2; i > mid - index1 - 1; i--) {
				if (isin(i, mid + index1)) {
					map[i][mid + index1] = num;
				}
				if (++num == N * N + 1) {
					return;
				}
			}

			index2++;
			for (int j = mid + index2; j > mid - index1 - 1; j--) {
				if (isin(mid - index1, j)) {
					map[mid - index1][j] = num;
				}
				if (++num == N * N + 1) {
					return;
				}
			}
//			index1++;
			for (int i = mid - index2; i <= mid + index1; i++) {
				if (isin(i, mid - index1)) {
					map[i][mid - index1] = num;
				}
				if (++num == N * N + 1) {
					return;
				}
			}
		}
	}

	static void printarr(int N) {
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
