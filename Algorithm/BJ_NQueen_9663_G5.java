package algo_study_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_NQueen_9663_G5 {
	static int[] map;
	static int N, ans=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=N; i++) {
			//처음에 둘 체스를 정해두고 시작한다.
			map = new int[N+1];
			// 첫 체스를 map[1]에 넣어두고 dfs 호출해서 나머지 체스돌을 찾음.
			map[1] = i;
			dfs(1);
		}
		System.out.println(ans);
	}

	public static void dfs(int idx) {
		if(idx == N) { // N번째 체스까지 놓아서 완성
			ans++;
		}else {
			// for문을 돌며 다음번에 둘 체스가 둘 수 있는지 체크함
			for(int i=1; i<=N; i++) {
				map[idx+1] = i; // 다음번에 둘 체크 위치
				if(check(idx+1)) { // 둘 수 있는지 체크
					dfs(idx+1); // 다음 체스 두러감
				}
			}
		}
	}
	
	public static boolean check(int idx) {
		for(int i=1; i<idx; i++) { 
			if(map[idx] == map[i]) { // 같은 행일 때
				return false;
			}
			// x , y 각각 뺀 절대값이 같으면 대각선에 있는것.
			if(Math.abs(i- idx) == Math.abs(map[i]- map[idx])) {
				return false;
				
			}
		}
		return true;
	}
}
