package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_준환이의양팔저울_3234_D4 {
	static int N;
	static boolean[] visit;
	static int[] com, arr,way;
	static int ans=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			visit = new boolean[N];
			com = new int[N];
			way = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ans=0;
			comb(0);
			System.out.println("#" + t+ " " + ans);
		}
	}

	public static void comb(int cnt) {
		if(cnt == N) {
			check(0,0,0);
			return;
		}
		for(int i=0; i<N; i++) {
			if(visit[i])
				continue;
			visit[i] = true;
			com[cnt] = i;
			comb(cnt+1);
			visit[i] = false;
		}
	}
	
	public static void check(int cnt, int left, int right) {
		if(cnt == N) {
			ans++;
			return;
		}
		
		// 왼쪽		
		check(cnt+1,left+arr[com[cnt]], right);
		// 오른쪽
		if(right+arr[com[cnt]] <= left) {
			check(cnt+1,left, right+arr[com[cnt]]);			
		}
	}
}
