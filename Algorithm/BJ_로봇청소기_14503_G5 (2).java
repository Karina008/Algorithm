package algo_study_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_로봇청소기_14503_G5 {
	static int N, M, cur_x, cur_y, d, map[][], ans = 0;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		cur_x = Integer.parseInt(st.nextToken());
		cur_y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sol();
	}

	public static void sol() {
		//   0
		// 3 2 1
		int wall = 0;
		boolean count_wall = false;
		while (true) {
			
			if (wall == 4) {
				int back = d - 2;
				if (back < 0)
					back += 4;
				// 만약 4방향이 청소O, 벽이면 1칸 후진 -> 처음부터 다시 실행
				if (map[cur_x + dx[back]][cur_y + dy[back]] != 1) {
					cur_x += dx[back];
					cur_y += dy[back];
					count_wall = false;
					wall = 0;
				} else {
					// 만약 4방향이 청소O, 벽이고 후진 불가 -> 종료
					System.out.println(ans);
					return;
				}
			}
			
			// 현재 위치 청소
			else if (!count_wall) {
				if (map[cur_x][cur_y] == 0) {
					map[cur_x][cur_y] = 2;
					ans++;
				}
			}
			int left = d - 1;
			if (left < 0)
				left = 3;
			// 왼쪽 청소 안했음
			if (map[cur_x + dx[left]][cur_y + dy[left]] == 0) {
				// 그 방향 회전 후 -> 1칸 전진 처음부터 다시 실행
				d = left;
				cur_x += dx[d];
				cur_y += dy[d];
				count_wall = false;
				wall = 0;
			} else if(map[cur_x + dx[left]][cur_y + dy[left]] != 0) {
				// 왼쪽 청소 했음
				// 그 방향 회전 후 -> 왼쪽 청소했는지 체크하는곳으로 돌아감
				wall++; // 벽 개수 셈
				count_wall = true;
				d = left;
			}
		}
	}
}
