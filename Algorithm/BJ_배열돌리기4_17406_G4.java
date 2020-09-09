package algo_study_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_배열돌리기4_17406_G4 {
	static int N, M, K, Min=Integer.MAX_VALUE;
	static int[][] map, map_copy, origin;
	static int r, c, s;
	static boolean[] visit;
	static int[] comb;
	static ArrayList<Data> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 회전연산 개수

		map = new int[N + 1][M + 1];
		map_copy = new int[N + 1][M + 1];
		origin = new int[N + 1][M + 1];
		visit = new boolean[K];
		comb = new int[K];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map_copy[i][j] = map[i][j] = origin[i][j]= Integer.parseInt(st.nextToken());
			}
		}

		copy();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());

			list.add(new Data(r, c, s));
		}
		dfs(0);
		System.out.println(Min);
	}

	public static void dfs(int index) {
		if(index== K) {
			for(int i=0; i<K; i++) {
				Data cur = list.get(comb[i]);
				int r = cur.r;
				int c = cur.c;
				int s = cur.s;
				
				rotate(r,c,s);
				copy();				
			}
			cal();
			copy_origin();
			return;
		}
		for(int i=0; i<K; i++) {
			if(visit[i]) 
				continue;
			comb[index] = i;
			visit[i] = true;
			dfs(index+1);
			visit[i] =false;
		}
		
	}
	public static void rotate(int r, int c, int s) {
		for (int i = 1; i <= s; i++) {
			// 윗
			for (int j = c + i; j > c - i; j--) {
				map_copy[r - i][j] = map[r - i][j - 1];
			}

			// 우
			for (int j = r + i; j > r - i; j--) {
				map_copy[j][c + i] = map[j - 1][c + i];
			}
			// 하
			for (int j = c - i; j < c + i; j++) {
				// System.out.println(j);
				map_copy[r + i][j] = map[r + i][j + 1];
			}
			// 좌
			for (int j = r - i; j < r + i; j++) {
				map_copy[j][c - i] = map[j + 1][c - i];
			}
		}
	}

	public static void cal() {
		for(int i=1; i<=N; i++) {
			int total=0;
			for(int j=1; j<=M; j++) {
				total+= map[i][j];
			}
			Min = Math.min(Min, total);
		}
	}
	public static void copy() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = map_copy[i][j];
			}
		}
	}
	public static void copy_origin() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = map_copy[i][j] = origin[i][j];
			}
		}
	}

	public static class Data {
		int r;
		int c;
		int s;

		public Data(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}

	}
}
