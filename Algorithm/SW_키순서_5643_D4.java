package algo_study_05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SW_키순서_5643_D4 {
	static int N,M;
	static int degree[];
	static ArrayList<Integer>[] list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			N= sc.nextInt();
			M=sc.nextInt();
			degree = new int[N+1];
			list = new ArrayList[N+1];
			Queue<Integer> q = new LinkedList<Integer>();
			for(int i=1; i<N+1; i++) 
				list[i] = new ArrayList<>();
			
			for(int i=0; i<M; i++) {
				int from = sc.nextInt();
				int to =  sc.nextInt();
				
				list[from].add(to);
				degree[to]++;
			}
			
			for(int i=1; i<=N; i++) {
				if(degree[i] ==0 ) {
					q.offer(i);
				}
					
			}
			System.out.print("#" + t + " ");
			while(!q.isEmpty()) {
				int node = q.poll();
				System.out.print(node + " ");
				
				for(int i=0; i<list[node].size(); i++) {
					int next = list[node].get(i);
					degree[next]--;
					if(degree[next]==0) 
						q.offer(next);
				}
			}
		}
	}

}
