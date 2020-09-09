package algo_study_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_사과나무_19539_S1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int total = 0, total_mod=0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
			total_mod += arr[i]/2; // 높이 2씩 성장시킬수 있는 총 높이
		}
		if(total %3 == 0 && total_mod>= total/3) // 2씩 성장시키는것이 1성장 이상이여야함
			System.out.println("YES");
		else
			System.out.println("NO");
	}

}
