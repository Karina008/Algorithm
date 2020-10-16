package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15684_사다리조작_G5 {
	static int N, M, H, ans = -1;
	static int[][] map;
	static boolean find;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로선의 개수
		M = Integer.parseInt(st.nextToken()); // 가로선의 개수
		H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 위치의 개수

		map = new int[35][15];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			map[a][b] = b + 1;
			map[a][b + 1] = b;
		}
		
		// 전부 일치하는 지 체크하는 변수
		find = false;
		// K: 사다리 놓는 개수
		// 사다리를 하나씩 늘려가며 가능한지 체크
		for (int k = 0; k <= 3; k++) {
			dfs(1, 1, 0, k);
			if (find) {
				// 전부 완성
				ans = k;
				break;
			}
		}
		System.out.println(ans);
	}

	public static void dfs(int x, int y, int count, int size) {
		// 사다리를 목표치까지 생성하였을 때 도착하는지 체크
		if (count == size) {
			boolean finish = true;
			for (int i = 1; i <=N; i++) {
				if (!check(1, i, i)) {
					finish = false;
				}
			}
			if (finish) {
				// 전부 완성
				find = true;
			}
			return;
		}

		// H: 열을 하나씩 증가시켜가며 사다리 추가
		for (int i = x; i <= H ; i++) {
			// N: 열을 하나씩 증가시켜가며 사다리 추가			
			for (int j = 1; j < N; j++) {
				// 현재 위치와 다음위치가 둘다 비어있어야함
				// 오른쪽으로만 두기에 왼쪽은 검사하지 않아도 됨
				if (map[i][j] != 0 || map[i][j + 1] != 0 ) {
					continue;
				}
				// 사다리를 놓아준 후 
				// dfs호출 후 되돌려 주어야 함
				map[i][j] = j + 1;
				map[i][j + 1] = j;
				dfs(i, y, count + 1, size);
				map[i][j] = map[i][j + 1] = 0;
			}
		}
		return;
	}

	public static boolean check(int x, int y, int index) {
		// 0: 사다리 없음 / 그외 : 사다리 있음		
		// 아래로 내려가며 사다리를 탄다
		while (x <= H + 1) {
			if (map[x][y] != 0) {
				y = map[x][y];
			} 
			x++;
		}

		// 시작점과 도착점이 일치하는지 체크
		if (y == index) {
			return true;
		} else {
			return false;
		}
	}
}
