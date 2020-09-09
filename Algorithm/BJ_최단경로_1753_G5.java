package algo_study_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ_최단경로_1753_G5 {
	static int V, E, startV;
	static List<Node>[] list;
	static int[] dist;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt(); // 정점 개수 1 <= <= 20000
		E = sc.nextInt(); // 간선 개수 1 <=  <= 300,000
		startV = sc.nextInt(); // 시작 위치
		list = new ArrayList[V+1];
		dist = new int[V+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=1; i<=V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			list[start].add(new Node(end, weight));
		}
		
		dijkstra(startV);
		for(int i=1; i<= V; i++) {
			if(dist[i] ==  Integer.MAX_VALUE)
				System.out.println("INF");
			else 
				System.out.println(dist[i]);
		}
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<>();	
		boolean[] visit = new boolean[V+1];
		q.add(new Node(start, 0));
		dist[start] =0;
		
		while(!q.isEmpty()) {
			Node curNode = q.poll();
			int cur = curNode.end;
			if(visit[cur])
				continue;
			visit[cur] = true;
			for(Node node : list[cur]) {
				if(dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					q.add(new Node(node.end, dist[node.end]));
				}
			}
		}
	}

}

//class Node {
class Node implements Comparable<Node> {
	int end, weight;

	
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}


	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return weight - o.weight;
	}
}