package algo_study_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_백만장자프로젝트_1859_D2 {
	static int n,max=0, idx=0;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			max=0;
			idx=0;
			arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());			
			for(int i=0; i<n; i++) {
				arr[i]= Integer.parseInt(st.nextToken());
			}
			//System.out.println(Arrays.toString(arr));
			long ans=0;
			idx= -1;
			for(int i=0; i<n; i++) {
				if(idx>=i) {
					
				}else {
					max=0;
					getMax(i);
				}
				ans += max- arr[i];
			}
			System.out.println("#" + t+ " " + ans);
			
		}
	}
	
	public static void getMax(int j) {
		for(int i=j; i<n; i++) {
			if(arr[i] > max) {
				max = arr[i];
				idx = i;
			}			
		}
	}
}
