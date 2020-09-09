package algo_study_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_수리공항승_1449_S3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		boolean[] arr = new boolean[1001];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[Integer.parseInt(st.nextToken())]= true;;
		}
		int ans =0;
		for(int i=0;i<1001; i++) {
			if(arr[i]) {
				ans++;
				for(int j=0; j<L && i+j<1001; j++) {
					arr[i+j] = false;
				}
			}
		}
		System.out.println(ans);
	}

}
