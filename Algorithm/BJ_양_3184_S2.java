package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_양_3184_S2 {
	static int R,C, yang, wolf, ans1=0, ans2=0;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static char[][] map;
	static boolean noArea;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visit = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visit[i][j] && map[i][j] != '#') {
					wolf =0;
					yang =0;
					noArea = false;
					if(map[i][j] == 'o') {
						yang++;
					}else if(map[i][j] == 'v') {
						wolf++;
					}
					dfs(i,j);
					if(!noArea) {						
						if(yang > wolf) {
							ans1 += yang;
						}else {
							ans2 += wolf;
						}
					}
				}
			}
		}
		System.out.println(ans1 + " " + ans2);
	}

	static void dfs(int x, int y) {
		visit[x][y] = true;
		
		for(int k=0; k<4; k++) {
			int nx = x+ dx[k];
			int ny = y+ dy[k];
			if(0<= nx && nx < R && 0<= ny && ny <C ) {
				if(map[nx][ny] != '#' && !visit[nx][ny]) {					
					if(map[nx][ny] == 'v') {
						wolf++;
					}else if(map[nx][ny] == 'o') {
						yang++;
					}
					dfs(nx,ny);
				}
			}
		}
	}
}
