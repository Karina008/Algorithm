package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_최소비용구하기_1916_G5_2 {
	static int N, M, map[][], dist[];
	static final int Val = Integer.MAX_VALUE ;
//	static final int Val = Integer.MAX_VALUE - 100001;
//	static final int Val = 1000000001;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		M = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dist = new int[N];
		
		for(int i=0; i<N; i++) {
//			dist[i] = Integer.MAX_VALUE;
			dist[i] = Val;
			for(int j=0; j<N; j++) {
//				map[i][j] = Integer.MAX_VALUE;
				map[i][j] = Val;
			}
		}
		StringTokenizer st;
		int from, to, cost;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken())-1;
			to = Integer.parseInt(st.nextToken())-1;
			cost = Integer.parseInt(st.nextToken());
			if(map[from][to] > cost) 
				map[from][to] = cost;
		}
		
//		for(int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1;
		System.out.println(Arrays.toString(dist));
		dijkstra(start);
//		System.out.println(Arrays.toString(dist));
		System.out.println(dist[end]);
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Integer> pq =  new PriorityQueue<>();
		pq.add(start);
		map[start][start] = 0;
		dist[start] =0;
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			for(int i=0; i<N; i++) {
				if(dist[i] > map[cur][i] + dist[cur]) {
					dist[i] = map[cur][i] + dist[cur];
					pq.add(i);
//					System.out.println(Arrays.toString(dist));
				}
			}
		}
	}

}
