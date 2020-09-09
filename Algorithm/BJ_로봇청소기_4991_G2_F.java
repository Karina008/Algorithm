package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_로봇청소기_4991_G2_F {
	static int w,h,dirty, ans;
	static char[][] map;
	static int[][][] visit;
	static Queue<Data> q;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1; t<= 3; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			visit = new int[h][w][2]; // move, clear
			dirty =0;
			ans=0;
			q = new LinkedList<>();
			
			for(int i=0; i<h; i++) {
				String str = br.readLine();
				for(int j=0; j<w; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '*') {
						dirty++;
					}else if(map[i][j] == 'o') {
						// 로봇 청소기 시작
						q.add(new Data(i,j,0,0,5));
					}
				}
//				System.out.println(Arrays.toString(map[i]));
			}
			
			bfs();
			System.out.println(ans);
			
		}
	}
	static void bfs() {
		while(!q.isEmpty()) {
			Data cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int clear = cur.clear;
			int move = cur.move;
			int way = cur.way;
			System.out.println(move + " " + clear + " " + x + " " + y);
			if(move > w*h) {
				ans = -1;
				return;
			}
			for(int k=0; k<4; k++) {
				if(way == k)
					continue;
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(0<= nx && nx < h && 0<= ny && ny < w ) {
					// 이동거리가 더 큰데 clear는 작거나 같으면 패스
					if(visit[nx][ny][0] != 0 && (visit[nx][ny][0] > move && visit[nx][ny][1] <clear)) {
						continue;
					}
					
					
					if(map[nx][ny] == '*') {
						// 쓰레기일때
						if(clear+1 == dirty) {
							ans = move+1;
							return;
						}
						visit[nx][ny][0] = move+1;
						visit[nx][ny][1] = clear+1;
						q.add(new Data(nx, ny, move+1  , clear+1, k));
					}else {						
						// 빈공간일 때
						visit[nx][ny][0] = move+1;
						visit[nx][ny][1] = clear;
						q.add(new Data(nx, ny, move+1  , clear, k));
					}
					
					
				}
			}
		}
	}

	static class Data {
		int x;
		int y;
		int move;
		int clear;
		int way;
		public Data(int x, int y, int move, int clear, int way) {
			this.x = x;
			this.y = y;
			this.move = move;
			this.clear = clear;
			this.way = way;
		}
		
	}
}
