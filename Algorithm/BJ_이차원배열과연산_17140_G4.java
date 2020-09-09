package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_이차원배열과연산_17140_G4 {
	static int R, C, K;
	static int row = 3, col = 3;
	static int[] num = new int[101];
	static int[][] map = new int[101][101];
	static LinkedList<Data> list = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		while (true) {
			if(row > 100)
				row =100;
			if(col > 100)
				col =100;
			if(ans > 100) {
				ans=-1;
				break;
			}
			if(map[R-1][C-1] == K) {
				break;
			}
			ans++;
			if (row >= col) {
				// 행정렬 실행
				rowcal();
			} else {
				// 열정렬 실행
				colcal();
			}
		}
		System.out.println(ans);
	}

	public static void rowcal() {
		int tempcol = 0;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				num[map[i][j]]++;
				map[i][j] = 0;
			}
			// 행정렬 수행
			for (int j = 1; j < 101; j++) {
				if (num[j] != 0)
					list.add(new Data(j, num[j]));
			}
			Collections.sort(list);

			int idx = 0;
			for (Data cur : list) {
				map[i][idx++] = cur.number;
				map[i][idx++] = cur.count;
			}

			if (tempcol <= idx)
				tempcol = idx;

			list.clear();
			Arrays.fill(num, 0);
		}
		col = tempcol;
	}

	public static void colcal() {
		int temprow = 0;
		
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				num[map[j][i]]++;
				map[j][i] = 0;
			}
			// 행정렬 수행
			for (int j = 1; j < 101; j++) {
				if (num[j] != 0)
					list.add(new Data(j, num[j]));
			}
			Collections.sort(list);

			int idx = 0;
			for (Data cur : list) {
				map[idx++][i] = cur.number;
				map[idx++][i] = cur.count;
			}

			if (temprow <= idx)
				temprow = idx;

			list.clear();
			Arrays.fill(num, 0);
		}
		row = temprow;
	}

	static class Data implements Comparable<Data> {
		int number;
		int count;

		public Data(int number, int count) {
			this.number = number;
			this.count = count;
		}

		@Override
		public int compareTo(Data o) {
			if (this.count < o.count) {
				return -1;
			} else if (this.count > o.count)
				return 1;
			else {
				if (this.number > o.number) {
					return 1;
				} else if (this.number < o.number)
					return -1;
			}
			return 0;
		}

	}
}
