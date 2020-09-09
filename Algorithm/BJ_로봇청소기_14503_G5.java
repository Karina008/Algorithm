package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_로봇청소기_14503_G5 {
	static int x, y, N, M, d;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, 0, 1, 0 };// 북 동 남 서
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken()); // 북 동 남 서

		map = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 0: 빈칸, 1:벽
			}
		}

		boolean check = false;
		int count = 0;
		int wall=0;
		while (true) {
			// 현재 위치 청소
			if(wall==4) {
				int nx = x + dx[(d + 2) % 4];
				int ny = y + dy[(d + 2) % 4];
				if (map[nx][ny] == 1) {
					break;
				}else {
					x= nx;
					y= ny;
					wall=0;
				}
			}
			if (map[x][y] == 0) {
				count++;
				map[x][y] = 2;
			}
			//
			int nx = x + dx[(d + 3) % 4];
			int ny = y + dy[(d + 3) % 4];
			if (map[nx][ny] == 0) {
				// a
				d = (d + 3) % 4; // 방향전환
				x = nx;
				y = ny;
				wall=0;
			} else {
				d = (d + 3) % 4; // 방향전환
				wall++;
			}

		}		
		 System.out.println(count);

	}
}