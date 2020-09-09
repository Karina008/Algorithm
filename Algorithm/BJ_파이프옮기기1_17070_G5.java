package algo_study_06;

import java.util.Scanner;

public class BJ_파이프옮기기1_17070_G5 {
	static int N, ans = 0;
	static int[][] map;
	static int[][] visit;
	static int[][][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(1,2,0);
		System.out.println(ans);
	}
	public static boolean isIn(int x, int y) {
		return 0<x && x<=N && 0<y && y<=N;
	}
	public static void dfs(int x, int y, int d) {
		if(x==N && y==N) {
			ans++;
			return;
		}
		//d: 0-우 , 1-대각, 2-하
		if(d!=2 && isIn(x,y+1) && map[x][y+1] == 0) {
			dfs(x,y+1,0);
		}
		if(d!=0 && isIn(x+1,y) && map[x+1][y] ==0) {
			dfs(x+1,y,2);
		}
		if(isIn(x+1,y+1) && map[x][y+1] == 0 && map[x+1][y] ==0 && map[x+1][y+1] ==0) {
			dfs(x+1,y+1,1);
		}
	}
}
