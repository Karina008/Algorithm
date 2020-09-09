package algo_study_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_등산로조성_1949_모의 {
	static int N,K,Top, ans;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static boolean[][] visit;
	static ArrayList<Data> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Top =0;
			ans=0;
			
			map =  new int[N][N];
			list = new ArrayList<>();
			
			
			for(int i=0; i<N;  i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(Top < map[i][j]) {
						list.clear();
						list.add(new Data(i,j));
						Top = map[i][j];
					}else if(Top ==  map[i][j]) {
						list.add(new Data(i,j));
					}
				}
			}
			
			for(int i=0; i<list.size();i++) {
				visit = new boolean[N][N];
				dfs(list.get(i).x, list.get(i).y, 1, false);
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	public static void dfs(int x, int y, int len, boolean check) {
		visit[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(0<= nx && nx<N && 0<= ny && ny <N && !visit[nx][ny]) {
				if(map[nx][ny]  < map[x][y]) {
					dfs(nx,ny, len+1, check);
				}else {
					if(!check) {
						for(int j=1; j<=K; j++) {
							if(map[nx][ny]-j <  map[x][y]) {
								map[nx][ny] -= j;
								dfs(nx,ny,len+1, true);					
								map[nx][ny] += j;
							}
						}
					}
				}
			}
			
		}
		
		visit[x][y] = false;
		ans = Math.max(ans, len);
	}
	
	static class Data {
		int x;
		int y;		
		
		public Data(int x, int y) {
			this.x = x;
			this.y = y;			
		}
		
		
	}
}
