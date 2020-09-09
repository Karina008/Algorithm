package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class SW_보물상자비밀번호_5658_모의 {
	static int N, K;
	static String[] arr;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 숫자의 개수
			K = Integer.parseInt(st.nextToken()); // 크기 순서
			
			String str = br.readLine();
			arr = new String[N];
			list = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				arr[i] = Character.toString(str.charAt(i));
			}
			
			for(int i=0; i<N/4; i++) {
				calc();
				rotate();
			}
			Collections.sort(list);
			
			System.out.println("#" + t + " " + list.get(list.size()-K));
		}
	}
	public static void rotate() {
		String temp = arr[N-1];
		for(int i=N-1; i>0; i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = temp;

	}
	public static void calc() {
		int  sum =0, total=0;
		String str =""; 
		for(int i=0; i<4; i++) {
			for(int j=0; j<N/4; j++) {
				int idx = i*(N/4)+j;
				str += arr[idx];
			}
			if(!list.contains(Integer.parseInt(str,16))) {
				
				list.add(Integer.parseInt(str,16));
			}
			str ="";
		}
	}

}
