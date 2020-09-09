package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_암기왕_2776_S3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int N, M;
		int[] arr1, arr2;
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			arr1 = new int[N];
			int idx=0;
			for(int i=0; i<N; i++) {
				arr1[idx++] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(br.readLine());
			arr2 = new int[M];
			st = new StringTokenizer(br.readLine());
			idx=0;
			for(int i=0; i<M; i++) {
				arr2[idx++] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr1);
			int index=0;
			for(int i=0; i<M; i++) {
				boolean chk = binarySearch(arr1, arr2[i]);
				if(chk) {
					System.out.println("1");
				}else
					System.out.println("0");
			}	
		}
	}
	public static boolean binarySearch(int[] arr, int num) {
		int first =0;
		int last = arr.length-1;
		int mid =0;
		
		while(first <= last) {
			mid = (first + last)/2;
			if(num == arr[mid]) {
				return true;
			}else {
				if(num < arr[mid]) {
					last = mid-1;				
				}else 
					first = mid+1;
			}
			
		}
		return false;
	}

}
