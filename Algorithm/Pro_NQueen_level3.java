package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pro_NQueen_level3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int ans = solution(n);
	}

	public static int solution(int n) {
		int answer = 0;

		for(int i=1; i<=n; i++) {
			int[] arr = new int[n+1];
			arr[1] = i;
			dfs(arr, 1, n);
		}
		System.out.println(count);
		return answer;
	}
	static int count=0;
	public static void dfs(int[] arr, int row, int n) {
		if(row == n) {
			count++;
		}else {
			for(int i=1; i<=n; i++) {
				arr[row+1] = i;
				if(isPossible(arr, row+1)) {
					dfs(arr, row+1, n);
				}
			}
		}
	}
	
	public static boolean isPossible(int[] arr, int row) {
		for(int i=1; i<row; i++) {
			if(arr[i] == arr[row])
				return false;
			if(Math.abs(i-row) == Math.abs(arr[i]-arr[row]))
				return false;
		}
		return true;
	}
	

}
