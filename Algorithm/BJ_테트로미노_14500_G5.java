package algo_study_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_테트로미노_14500_G5 {
	static int N, M, Max = Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로 4<= <=500
		M = Integer.parseInt(st.nextToken()); // 가로 4<= <=500

		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visit[i][j] = false;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				oh(i, j);
			}
		}
		System.out.println(Max);
	}

	public static void dfs(int x, int y, int cnt, int total) {
		if (cnt == 4) {
			visit[x][y] = true;
			Max = Math.max(Max, total);
			visit[x][y] = false;
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];

			if (0 <= nx && nx < N && 0 <= ny && ny < M) {
				if (!visit[nx][ny]) {
					visit[nx][ny] = true;
					dfs(nx, ny, cnt + 1, total + map[nx][ny]);
					visit[nx][ny] = false;
				}
			}
		}
	}

	public static void oh(int x, int y) {
		int total=map[x][y];
		for(int k=0; k<4; k++) {
			int nx = x+dx[k];
			int ny = y+dy[k];
			if(0<= nx && nx<N && 0<= ny && ny <M) { 
				total+=map[nx][ny];
			}
		}
		int count=0;
		int temp=total;
		for(int k=0; k<4; k++) {
			int nx = x+dx[k];
			int ny = y+dy[k];
			if(0<= nx && nx<N && 0<= ny && ny <M) { 
				temp = total- map[nx][ny];
			}else {
				 temp=total;
			}
			if(Max<temp) {
				Max=temp;
			}
		}
	}

}
