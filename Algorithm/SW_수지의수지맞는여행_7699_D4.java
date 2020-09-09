package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_수지의수지맞는여행_7699_D4 {
	static int R,C, max=0;
	static char[][] map;
	static boolean[] visit2;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			visit2 = new boolean[26];
			max =0;
			
			for(int i=0; i<R; i++) {
				String str = br.readLine();
				for(int j=0; j<C; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			dfs(0,0,1);
			System.out.println("#" + t + " " + max);
		}
	}

	
	public static void dfs(int x, int y,int cnt) {
		visit2[map[x][y]-'A'] = true;
		max = Math.max(max, cnt);
		
		for(int k=0; k<4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(0<= nx && nx <R && 0<= ny && ny<C   && !visit2[map[nx][ny]-'A']) {
				dfs(nx,ny, cnt+1);
			}
			
		}
		visit2[map[x][y]-'A'] = false;
	}
}
