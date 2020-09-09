package algo_study_05;

import java.util.Scanner;

public class BJ_좋은수열_2661_G4 {
	static int N;
	static boolean chk=false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dfs(0,"");
	}
	
	public static void dfs(int cnt, String str) {
		if(chk)
			return;
		if(cnt == N) {
			chk=true;
			System.out.println(str);
		}else {
			for(int i=1; i<=3; i++) {
				if(check(str+i)) {
					dfs(cnt+1, str+i);
				}
			}
		}
	}
	
	public static boolean check(String str) {
		int len = str.length();
		int start=len-1;
		int end = len;
		
		for(int i=1; i<= len/2; i++) {
			if(str.substring(start-i, end-i).equals(str.substring(start,end) )) {
				return false;
			}
			start = start-1;
		}
		
		return true;		
	}
}
