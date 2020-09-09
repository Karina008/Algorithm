package algo_study_08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_최소비용구하기_1916_G5 {
	public static int map[][], n, m, dist[];
	public static final int INF = 1000000001;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int i, j, from, to, price, objFrom, objTo;
		n = Integer.parseInt(in.readLine()); m = Integer.parseInt(in.readLine());
		map = new int[n][n];
		dist = new int[n];
		for(i=0;i<n;i++){
			dist[i] = INF;
			for(j=0;j<n;j++)
				map[i][j] = INF;
		}
		String line[];
		for(i=0;i<m;i++){
			line = in.readLine().split(" ");
			from = Integer.parseInt(line[0])-1;
			to = Integer.parseInt(line[1])-1;
			price = Integer.parseInt(line[2]);
			if(map[from][to]>price)
				map[from][to] = price;
		}
		line = in.readLine().split(" ");
		objFrom = Integer.parseInt(line[0])-1;
		objTo = Integer.parseInt(line[1])-1;
		dijkstra(objFrom);
		out.write(String.valueOf(dist[objTo]));
		out.close();
		in.close();
	}
	
	private static void dijkstra(int start){
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(start);
		map[start][start] = 0;
		dist[start] = 0;
		int i, before;
		while(!pq.isEmpty()){
			before = pq.poll();
			for(i=0;i<n;i++){
				if(dist[i]>map[before][i]+dist[before]){
					dist[i] = map[before][i]+dist[before];
					pq.offer(i);
				}
			}
		}
	}
}