package algo_study_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;



public class BJ_파티_1238_G3 {
	static int N, M, X, Max = Integer.MIN_VALUE;
	static List<Node>[] list, list2;
	static int[] dist, dist2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		list = new ArrayList[N + 1];
		dist = new int[N + 1];
		list2 = new ArrayList[N + 1];
		dist2 = new int[N + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(dist2, Integer.MAX_VALUE);

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			list2[i] = new ArrayList<>();
		}

		// int[][] map = new int[N+1][N+1];
		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();

			// map[from][to] = val;
			list[start].add(new Node(end, weight));
			list2[end].add(new Node(start, weight));

		}

		dijkstra(X);
		dijkstra2(X);
		for (int i = 1; i <= N; i++) {
			if (dist[i] + dist2[i] > Max) {
				Max = dist[i] + dist2[i];
			}
		}
		System.out.println(Max);

	}

	private static void dijkstra(int start) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> q = new PriorityQueue<>();
		boolean visit[] = new boolean[N + 1];
		q.add(new Node(start, 0));
		dist[start] = 0;

		while (!q.isEmpty()) {
			Node curNode = q.poll();
			int cur = curNode.end;
			if (visit[cur])
				continue;
			visit[cur] = true;
			for (Node node : list[cur]) {
				if (dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					q.add(new Node(node.end, dist[node.end]));
				}
			}
		}
	}

	private static void dijkstra2(int start) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> q = new PriorityQueue<>();
		boolean visit[] = new boolean[N + 1];
		q.add(new Node(start, 0));
		dist2[start] = 0;

		while (!q.isEmpty()) {
			Node curNode = q.poll();
			int cur = curNode.end;
			if (visit[cur])
				continue;
			visit[cur] = true;
			for (Node node : list2[cur]) {
				if (dist2[node.end] > dist2[cur] + node.weight) {
					dist2[node.end] = dist2[cur] + node.weight;
					q.add(new Node(node.end, dist2[node.end]));
				}
			}
		}
	}
}
class Node implements Comparable<Node> {
	int end, weight;
	
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
}

