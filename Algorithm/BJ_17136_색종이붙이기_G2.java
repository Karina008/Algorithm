package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17136_색종이붙이기_G2 {
	static int N=10, ans=Integer.MAX_VALUE;
	static int[][] map = new int[10][10];
	static int[] page = new int[6];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=5; i++) {
			page[i] = 5;
		}
		search(0,0, 0);

		if(ans == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(ans);			
		}
		
	}
	
	public static void search(int x, int y, int count) {
		// 가지치기
		if(ans < count)
			return;
		
		// 좌표를 돌며 검사함
		for(int i=x; i<10; i++) {
			for(int j=0; j<10; j++) {
				// 덮어야할 지점 발견
				if(map[i][j] == 1) {
					// 큰종이부터 작은 종이로 재귀호출
					for(int k=5; k>0;  k--) {
						if(i+k<=10 && j+k <=10 && page[k] > 0) {
							// 종이 크기만큼 0으로 채워준 후 다시 1로 채워준다
							if(check(i,j,k, 0)) {
								fill(i,j,k, 0);
								page[k]--;
								search(i,j, count+1);
								fill(i,j,k, 1);
								page[k]++;
							}
						}
					}
					// 1인 지점에서 5개의 종이를 호출한 후 
					// 현재 이 값은 필요없어지므로 되돌려 주어야
					// 시간초과가 나지 않는다
					return;
				}
			}
		}

		// 전부 덮였는지 체크 후 되돌려줌
		if(check(0,0,10,1)) {
			ans = Math.min(ans, count);
			return;
		}		
	}
	
	public static boolean check(int x, int y, int num, int target) {
		for(int i=x; i<x+num; i++) {
			for(int j=y; j<y+num; j++) {
				if(map[i][j] == target) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void fill(int x, int y, int num, int target) {
		for(int i=x; i<x+num; i++) {
			for(int j=y; j<y+num; j++) {
				map[i][j] = target;
			}
		}		
	}
}
