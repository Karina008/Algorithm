package algo_study_05;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_가르침_1062_G4 {
	static int N, K, Max = Integer.MIN_VALUE;
	static int[][] arr;
	static boolean[] visit;
	static int[] index;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 남극 언어의 단어 <50
		K = sc.nextInt(); // 가르칠 글자 수 <= 26
		
		arr = new int[N][26];
		visit = new boolean[26];
		index = new int[26];
		
		if(K<5) {
			System.out.println(0);
		}else {
			
			for(int n=0; n<N; n++) {
				String str = sc.next();
				int idx=0;
				for(int i=4; i<str.length()-4; i++) {					
					if(str.charAt(i) =='a'  )
						continue;
					arr[n][idx++] = str.charAt(i)-'a';
				}
			}
			
			visit['a' - 'a'] = true;
			visit['c' - 'a'] = true;
			visit['i' - 'a'] = true;
			visit['n' - 'a'] = true;
			visit['t' - 'a'] = true;
			K-=5;
			dfs(0,0);
			System.out.println(Max);
		}
	}

	public static void dfs(int cnt, int start) {
		if(cnt == K) {
			int count=0;
			for(int i=0; i<N; i++) { // N개 단어 비교
				int idx=0,idx2=0;
				boolean chk= true;
				
				int len=0;
				while(true) {
					if(arr[i][len++] ==0 )
						break;
				}
				len--;
				for(int j=0; j<len; j++) {
					if(!visit[arr[i][j]]) {
						chk = false;
						break;
					}
				}
				
				if(chk) {
					count++;
				}
			}
			Max = Math.max(Max, count);
			
			return;
		}
		for(int i=start; i<26; i++) {
			if(visit[i])
				continue;
			visit[i] = true;
			index[i] = 1;
			dfs(cnt+1, i+1);
			visit[i] = false;
		}
	}
}
