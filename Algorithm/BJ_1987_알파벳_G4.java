package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1987_알파벳_G4 {
	static int R, C,ans=0;
	static char[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean[] alpha = new boolean[26];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		alpha[map[0][0]-'A'] = true;
		dfs(0,0,1);
		System.out.println(ans);
	}
	
	static void dfs(int x, int y, int move) {
		// char 형인데 대문자 알파벳이라 A를 빼주면 정수가 된다.
		alpha[map[x][y]-'A'] = true;
		// 알파벳 전부 탐색 완료 시 리턴 ( 가지치기)
		if(ans == 26) {
			return;
		}
		for(int k=0; k<4; k++) {
			int nx = x+dx[k];
			int ny = y+dy[k];
			if(nx<0 || nx >= R || ny <0 || ny>=C || alpha[map[nx][ny]-'A']) {
				continue;
			}			
			dfs(nx,ny,move+1);
		}
		
		// 되돌아 가기전 해당 지점 방문을 미방문으로 돌려줌
		alpha[map[x][y]-'A'] = false;		
		ans = Math.max(ans, move);		
	}
}
