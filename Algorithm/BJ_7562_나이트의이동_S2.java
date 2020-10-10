package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7562_나이트의이동_S2 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,-2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2,-1, 1, 2, 2, 1, -1, -2};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			int ans = search(startX, startY, endX, endY);
			System.out.println(ans);
		}
	}
	
	public static int search(int startX, int startY, int endX, int endY) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(startX, startY, 0));
		visited[startX][startY] = true;
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int move = cur.move;
			for(int k=0; k<8; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				if(0<= nx && nx <N && 0<= ny && ny < N && !visited[nx][ny]) {
					if(nx == endX && ny == endY) {
						// 도착
						return move+1;						
					}
					visited[nx][ny] = true;
					q.add(new Data(nx,ny, move+1));
				}
			}
		}
		
		return 0;
	}

	static class Data {
		int x;
		int y;
		int move;
		public Data(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
		
		
	}
}
