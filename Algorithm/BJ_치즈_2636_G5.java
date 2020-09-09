package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_치즈_2636_G5 {
	static int N,M,count=0;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx= {1, -1, 0, 0};
	static int[] dy= {0, 0, 1, -1};
	static boolean chk=false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		while(true) {
			visit = new boolean[N][M];
			chk=false;
			dfs(0,0);
			if(!chk)
				break;
			count++;
		}
		
		System.out.println(count);
		int ans=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == count+1) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	public static void dfs(int x, int y) {	
		
		for(int k=0; k<4; k++) {
			int nx = x+dx[k];
			int ny = y+dy[k];
			if(0<= nx && nx <N && 0<= ny && ny <M) {
				if(!visit[nx][ny] && map[nx][ny]!=1) {
					visit[nx][ny] = true;
					dfs(nx,ny);
				}else if(!visit[nx][ny] && map[nx][ny] == 1) {
					visit[nx][ny]= true;
					map[nx][ny]=count+2;
					chk=true;
				}
			}
		}
	}
}
