package algo_study_05;

import java.util.Scanner;

public class BJ_미세먼지야안녕_17144_G5 {
	static int R, C, T;
	static int[][] map, map_copy;
	static int[][] air;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt(); // 행
		C = sc.nextInt(); // 열
		T = sc.nextInt(); // 초

		map = new int[R][C];
		air = new int[2][2];
		int index = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1) {
					air[index][0] = i; // air[0]
					air[index][1] = j; // air[1]
					index++;
				}
			}
		}

		for (int t = 0; t < T; t++) {
			// 미세먼지 확산
			map_copy = new int[R][C];

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					// 해당 위치에 미세먼지가 있다면
					if (map[i][j] > 4) {
						int moveCount = 0;
						for (int k = 0; k < 4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];

							if (air[0][0] == nx && air[0][1] == ny)
								continue;
							if (air[1][0] == nx && air[1][1] == ny)
								continue;

							if (0 <= nx && nx < R && 0 <= ny && ny < C) {
								map_copy[nx][ny] += map[i][j] / 5;
								moveCount++;
							}
						}
						int temp1 = map[i][j] / 5;
						int temp = moveCount * temp1;
						map_copy[i][j] += map[i][j] - temp;
					} else {
						map_copy[i][j] += map[i][j];
					}
				}
			}
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] = map_copy[i][j];
				}
			}
			
			// 공기청정기 가동
			int air_up = air[0][0];
			int air_down = air[1][0];
			
			// 위쪽 - 좌
			if (air_up > 1) {
				for (int i = air_up-1; i > 0; i--) {
					map[i][0] = map[i - 1][0];
				}
			}

			for (int i = 0; i < C - 1; i++) {
				map[0][i] = map[0][i + 1];
			}
			
			for (int i = 0; i < air_up; i++) {
				map[i][C - 1] = map[i + 1][C - 1];
			}
			
			for (int i = C - 1; i > 1; i--) {
				map[air_up][i] = map[air_up][i - 1];
			}
			map[air_up][1] = 0;
			
			// 하단 - 좌
			for (int i = air_down + 1; i < R-1; i++) {
				map[i][0] = map[i + 1][0];
			}
			// 하단 - 아래
			if (air_down < R - 1) {
				for (int i = 0; i <C-1; i++) {
					map[R-1][i] = map[R-1][i+1];
				}
			}
			
			for (int i = R - 1; i > air_down; i--) {
				map[i][C - 1] = map[i - 1][C - 1];
			}
			for (int i = C-1; i >1; i--) {
				map[air_down][i] = map[air_down][i - 1];
			}
			map[air_down][1] =0;
			
			
		}
		int ans=2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				ans+=map[i][j];
			}
		}
		
		System.out.println(ans);
		
	}

}
