package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_숨바꼭질_6118_S1 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List[] nodes = new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) {
			nodes[i] = new ArrayList();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			nodes[v1].add(v2);
			nodes[v2].add(v1);
			
		}
		
		
		int number = 2;
		int maxDistance = -1;
		int count =1;
		
		Queue<Node> q = new LinkedList<>();
		boolean[] visit = new boolean[N+1];
		
		q.add(new Node(1,0));
		visit[1] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(maxDistance < cur.distance) {
				maxDistance = cur.distance;
				number = cur.index;
				count = 1;
			}
			else if(maxDistance == cur.distance) {
				count++;
				
				if(number > cur.index) {
					number  = cur.index;
				}
			}
			 for(Integer  next : nodes[cur.index]) {
				 if(visit[next])
					 continue;
			}
		}
	}

static 	class Node {
		int index;
		int distance;
		
		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}
		
	}
}

