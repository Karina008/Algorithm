package algo_study_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_홀수만더하기_D1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			
			int ans =0;
			for(int i=0; i<10; i++) {
				int su = Integer.parseInt(st.nextToken());
				if(su%2==1) {
					ans+= su;
				}
//				System.out.println(st.nextToken());
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

}
