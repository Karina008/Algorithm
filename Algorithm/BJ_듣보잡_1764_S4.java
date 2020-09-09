package algo_study_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class BJ_듣보잡_1764_S4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] arr1 = new String[N];
		//String[] arr2 = new String[M];
		String[] arr3 = new String[N+M];
		boolean[] chk = new boolean[N];
		int count =0;
		for(int i=0; i<N; i++) {
			arr1[i] = br.readLine();
		}
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				
				if(!chk[j] && arr1[j].equals(str)) {
					arr3[count] = str;
					count++;
					chk[j]=true;
				}
			}
			//arr2[i] = br.readLine();
		}
		
		System.out.println(count);
		for(int i=0; i<count; i++) {
			System.out.println(arr3[i]);
		}
		
	}

}
