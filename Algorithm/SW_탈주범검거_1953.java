package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_탈주범검거_1953 {
	static int N,M,R,C,L;
	static int[][] map;
	static boolean[][] visit;
	static int Max = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로
			M = Integer.parseInt(st.nextToken()); // 가로
			R = Integer.parseInt(st.nextToken()); // 맨홀뚜겅 세로
			C = Integer.parseInt(st.nextToken()); // 맨홀뚜겅 가로
			L = Integer.parseInt(st.nextToken()); // 탈출 소요시간
			
			map = new int[N][M];
			visit = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] =  Integer.parseInt(st.nextToken()); 
				}
			}
			
			bfs();
			System.out.println("#" + t+ " " + Max);
			Max = Integer.MIN_VALUE;
		}
	}
	
	public static boolean isIn(int x, int y) {		
		return (x >=0 && x<N && y>=0 && y<M);
	}
	public static boolean possi(int x, int y, int d) {
		// d: 북 동 남 서
		if(d==1) { // 북
			return (map[x][y] == 1 || map[x][y] == 2 || map[x][y] ==5 || map[x][y] ==6);
		}else if(d==2) // 동
			return (map[x][y] == 1 || map[x][y] ==3 || map[x][y] ==6 || map[x][y] ==7);
		else if(d==3) // 남
			return (map[x][y] == 1 || map[x][y] ==2 || map[x][y] ==4 || map[x][y] ==7);
		else if(d==4) // 서
			return (map[x][y] == 1 || map[x][y] ==3 || map[x][y] ==4 || map[x][y] ==5);
		return false;
	}
	public static void bfs() {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(R,C,1));
		visit[R][C] = true;
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int time = cur.time;
			if(time >= L )
				continue;
			switch(map[x][y]) {
			case 1: // 상하좌우
				// 북
				if(isIn(x-1, y) && possi(x-1,y,1) && !visit[x-1][y]) {
					q.add(new Data(x-1,y,time+1));
					visit[x-1][y] = true;
				}
				// 남
				if(isIn(x+1, y) && possi(x+1,y,3) && !visit[x+1][y]) {
					q.add(new Data(x+1,y,time+1));
					visit[x+1][y] = true;
				}	
				// 서
				if(isIn(x, y-1) && possi(x,y-1,4) && !visit[x][y-1]) {
					q.add(new Data(x,y-1,time+1));
					visit[x][y-1] = true;
				}	
				// 동
				if(isIn(x, y+1) && possi(x,y+1,2) && !visit[x][y+1]) {
					q.add(new Data(x,y+1,time+1));
					visit[x][y+1] = true;
				}					
				break;
			case 2: // 상하
				// 북
				if(isIn(x-1, y) && possi(x-1,y,1) && !visit[x-1][y]) {
					q.add(new Data(x-1,y,time+1));
					visit[x-1][y] = true;
				}
				// 남
				if(isIn(x+1, y) && possi(x+1,y,3) && !visit[x+1][y]) {
					q.add(new Data(x+1,y,time+1));
					visit[x+1][y] = true;
				}	
				break;
			case 3: // 좌우
				// 서
				if(isIn(x, y-1) && possi(x,y-1,4) && !visit[x][y-1]) {
					q.add(new Data(x,y-1,time+1));
					visit[x][y-1] = true;
				}	
				// 동
				if(isIn(x, y+1) && possi(x,y+1,2) && !visit[x][y+1]) {
					q.add(new Data(x,y+1,time+1));
					visit[x][y+1] = true;
				}			
				break;
			case 4: // 상우
				// 동
				if(isIn(x, y+1) && possi(x,y+1,2) && !visit[x][y+1]) {
					q.add(new Data(x,y+1,time+1));
					visit[x][y+1] = true;
				}	
				// 북
				if(isIn(x-1, y) && possi(x-1,y,1) && !visit[x-1][y]) {
					q.add(new Data(x-1,y,time+1));
					visit[x-1][y] = true;
				}

				break;
			case 5: // 하우
				// 동
				if(isIn(x, y+1) && possi(x,y+1,2) && !visit[x][y+1]) {
					q.add(new Data(x,y+1,time+1));
					visit[x][y+1] = true;
				}	
				// 남
				if(isIn(x+1, y) && possi(x+1,y,3) && !visit[x+1][y]) {
					q.add(new Data(x+1,y,time+1));
					visit[x+1][y] = true;
				}	
				break;
			case 6: // 하좌
				// 남
				if(isIn(x+1, y) && possi(x+1,y,3) && !visit[x+1][y]) {
					q.add(new Data(x+1,y,time+1));
					visit[x+1][y] = true;
				}	
				// 서
				if(isIn(x, y-1) && possi(x,y-1,4) && !visit[x][y-1]) {
					q.add(new Data(x,y-1,time+1));
					visit[x][y-1] = true;
				}	
				break;
			case 7: // 상좌
				// 서
				if(isIn(x, y-1) && possi(x,y-1,4) && !visit[x][y-1]) {
					q.add(new Data(x,y-1,time+1));
					visit[x][y-1] = true;
				}	
				// 북
				if(isIn(x-1, y) && possi(x-1,y,1) && !visit[x-1][y]) {
					q.add(new Data(x-1,y,time+1));
					visit[x-1][y] = true;
				}
				break;
				
			}
		}
		int count=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visit[i][j]) {
					count++;
				}
			}
		}
		Max = Math.max(Max, count);
		return;
	}
	
	public static class Data{
		int x;
		int y;
		int time;
		public Data(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		
	}
}
