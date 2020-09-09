package algo_study_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ_타임머신_11657_G4 {
	static int N, M, Max= Integer.MIN_VALUE, Min=Integer.MAX_VALUE;
	static List<Node2>[] list;
	static int[] dist;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 도시 개수
		M = sc.nextInt(); // 버스 노선 개수
		
		list = new ArrayList[N+1];
		dist = new int[N+1];
		
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=1; i<=N; i++) {
			list[i]  = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			list[start].add(new Node2(end, weight));
		}
		
		dijkstra(1);
		for(int i=1; i<=N; i++) {
			if(dist[i] == Integer.MAX_VALUE)
				System.out.println("-1");
			else
				System.out.println(dist[i]);
		}
//		for(int i=2; i<=N; i++) {
//			if(Max < dist[i])
//				Max= dist[i];
//			if(Min > dist[i])
//				Min = dist[i];
//		}
//		System.out.println(Max);
//		System.out.println(Min);
	}


	private static void dijkstra(int start) {
		// TODO Auto-generated method stub
		PriorityQueue<Node2> q = new PriorityQueue<>();
		boolean[] visit = new boolean[N+1];
		q.add(new Node2(start,0));
		dist[start] =0;
		
		while(!q.isEmpty()) {
			Node2 curNode = q.poll();
			int cur = curNode.end;
			if(visit[cur])
				continue;
			if(cur != 1)
				visit[cur] = true;
			for(Node2 node : list[cur]) {
				if(dist[node.end] > dist[cur] + node.weight) {
					dist[node.end]= dist[cur] + node.weight;
					q.add(new Node2(node.end, dist[node.end]));
				}
			}
			
		}
	}

}

class Node2 implements Comparable<Node2>{
	int end; 
	int weight;
	
	public Node2(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node2 o) {
		// TODO Auto-generated method stub
		return weight-o.weight;
	}
	
}