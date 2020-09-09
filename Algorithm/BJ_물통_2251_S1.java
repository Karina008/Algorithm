package algo_study_05;

import java.util.Scanner;

public class BJ_물통_2251_S1 {
	static int A,B,C;
	static boolean[][]  visit = new boolean[201][201];
	static boolean[] ans = new boolean[201];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A= sc.nextInt();
		B= sc.nextInt();
		C= sc.nextInt();

		dfs(0,0, C);
		
		for(int i=0; i<201; i++) {
			if(ans[i])
				System.out.print(i+" ");
		}
	}

	public static void dfs(int na, int nb, int nc) {
		if(visit[na][nb]) 
			return;
		
		if(na == 0) {
			ans[nc] =true;
		}
		
		visit[na][nb] = true;
		
		// a->b
		if(na + nb > B) {
			dfs((na+nb)-B, B, nc);			
		}else {
			dfs(0, na+nb, nc);
		}
		
		// b->a
		if(na+nb > A) {
			dfs(A, (na+nb)-A, nc);
		}else {
			dfs(na+nb, 0, nc);
		}
		
		// c->a
		if(na+nc > A) {
			dfs(A, nb, (na+nc)-A);
		}else {
			dfs((na+nc), nb, 0);
		}
		
		// c->b
		if(nb+nc > B) {
			dfs(na, B, (nb+nc)-B);
		}else {
			dfs(na, nb+nc, 0);
		}
		
		dfs(0, nb, na+nc);
		dfs(na, 0, nb+nc);
	}
}
