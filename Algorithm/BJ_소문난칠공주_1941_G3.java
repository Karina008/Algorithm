package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_소문난칠공주_1941_G3 {
	static char[][] map = new char[5][5];
	static boolean[] visit = new boolean[25];
	static int[] arr = new int[8];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static int ans=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++) {
			String str = br.readLine();
			for(int j=0; j<5; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		// S: 이다솜 v , 4명이상
		// Y: 임도연
		
		for(int i=0; i<25; i++) {
			dfs(i,1,0);
		}
		System.out.println(ans);
	}
	
	public static void dfs(int n, int cnt, int s) {
		
		if(map[n/5][n%5] == 'S' ) {
			s++;
		}
		visit[n] = true;

		if(cnt==7) {			
			if(s>=4) {
				if(make(n/5, n%5)) {
					
					ans++;
				}
			}
		}else {
			for(int i=n+1; i<25; i++) {
				if(!visit[i]) {
					dfs(i,cnt+1, s);
				}
			}
		}
		visit[n] = false;
	}
	
	public static boolean make(int x, int y) {
		boolean[][] check = new boolean[5][5];
		Queue<int[]> q = new LinkedList<>();
		check[x][y] = true;
		int count=1;
		q.offer(new int[] {x,y});
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int k=0; k<4; k++) {
				int nx = temp[0] + dx[k];
				int ny = temp[1] + dy[k];
				
				if(0<= nx && nx< 5 && 0<= ny && ny <5 ) {
					if(!check[nx][ny] && visit[nx*5+ny]) {
						count++;
						check[nx][ny] = true;
						q.offer(new int[] {nx,ny});
					}
				}
			}
		}
		if(count == 7) {
			return true;
		}else 
			return false;
	}
}

