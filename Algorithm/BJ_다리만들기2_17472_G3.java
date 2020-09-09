package algo_study_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_다리만들기2_17472_G3 {
	static int N,M, ans=0, count=0;
	static int[][] map;
	static boolean[][] visit;
	static int island=0;
	static int[] dx= {1, -1, 0, 0};
	static int[] dy= {0, 0, 1, -1};
	static int[] parents;
	static PriorityQueue<Bridge> pq = new PriorityQueue<Bridge>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1 && !visit[i][j]) {
					island++;
					bfs(i,j);
				}
			}
		}
		
		visit = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0) {
					makeBridge(i,j,map[i][j]);
				}
			}
		}
		
		parents = new int[island+1];
		for(int i=0; i<parents.length; i++) {
			parents[i]=i;
		}
		
		int size = pq.size();
		for(int i=0; i<size; i++) {
			Bridge cur = pq.poll();
			
			int a = find(cur.start);
			int b = find(cur.end);			
			
			if(a==b)
				continue;
			
			union(cur.start, cur.end);
			ans += cur.value;
			count++;	
		}
		//System.out.println(Arrays.toString(parents));
		if(ans ==0 || count != island-1) {
			System.out.println(-1);				
		}else {
			System.out.println(ans);
		}
	}
	
	public static int find(int a) {
		if(a==parents[a])
			return a;
		else
			return find(parents[a]);
//		parents[a] = find(parents[a]);
//		return parents[a];		
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		parents[y] = x;
	}
	
	public static void bfs(int r, int c) {
		Queue<Data> q = new LinkedList<>();
		visit[r][c] = true;
		map[r][c] = island;
		q.add(new Data(r,c));
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				if(0<= nx && nx<N && 0<=ny  && ny<M) {
					if(map[nx][ny] == 1 && !visit[nx][ny]) {
						q.add(new Data(nx,ny));
						map[nx][ny] = island;
						visit[nx][ny] = true;
			
					}
				}
			}
		}
	}
	
	public static void makeBridge(int r,int c, int num) {
		int x = r;
		int y = c;
		int length = 0;
		
		for(int k=0; k<4; k++) {
			while(true) {
				x= x+dx[k];
				y= y+dy[k];
				
				if(0<= x && x<N && 0<= y && y<M) {
					if(map[x][y] == num) {
						length =0;
						x = r;
						y = c;
						break;
					}else if(map[x][y] ==0) {
						length++;
					}else {
						if(length>1) {
							pq.add(new Bridge(num, map[x][y], length));
						}
						length =0;
						x = r;
						y = c;
						break;
					}
				}else {
					length =0;
					x = r;
					y = c;
					break;
				}
			}
		}
	}
	public static class Data {
		int x;
		int y;
		public Data(int x, int y) {
			this.x = x;
			this.y = y;
		}	
	}
	
	public static class Bridge implements Comparable<Bridge>{
		int start;
		int end;
		int value;
		public Bridge(int start, int end, int value) {
			this.start = start;
			this.end = end;
			this.value = value;
		}
		@Override
		public int compareTo(Bridge o) {
			if(o.value>=this.value)
				return -1;
			else
				return 1;
		}
		
	}
}
