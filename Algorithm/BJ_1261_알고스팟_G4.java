package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1261_알고스팟_G4 {
	static int N,M;
	static int[][] map, dist;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // 해당 지점의 벽 유무
		dist = new int[N][M]; // 해당 지점까지의 최소 벽 부순 값
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
				dist[i][j] = Integer.MAX_VALUE; //  최대값으로 초기화 해줌
			}			
		}
		bfs();
		System.out.println(dist[N-1][M-1]);
	}

	public static void bfs( ) {
		PriorityQueue<Data> pq = new PriorityQueue<>();
		pq.add(new Data(0,0,map[0][0]));
		dist[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			Data cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];

				if(nx<0 || nx >= N || ny <0 || ny >= M) {
					continue;
				}
				
				// 다음 지점까지의 최소이동 비용이 현재지점 이동거리 + 다음지점 벽유무의 값보다 크면
				if(dist[nx][ny] > dist[x][y] + map[nx][ny]) {
					dist[nx][ny] = dist[x][y] + map[nx][ny];
					pq.add(new Data(nx,ny,dist[nx][ny]));
				}
			}
		}
		
	}
	static class Data implements Comparable<Data>{
		int x;
		int y;
		int value;
		public Data(int x, int y, int value) {
			super();
			this.x = x;
			this.y = y;
			this.value = value;
		}
		@Override
		public int compareTo(Data o) {
			return this.value - o.value;
		}
	}
}
