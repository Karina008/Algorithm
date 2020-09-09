package algo_study_05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_트리_1068_S1 {
	static int N, ans=0;
	static int[][] map;
	static boolean[] visit;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1][2];
		visit = new boolean[N + 1];
		
		sc.nextInt();
		int idx=1;
		for(int i=2; i<=N; i++) {
			int left = sc.nextInt();
			map[idx][0] = left;
			if(N%2==0) {
				int right = sc.nextInt();
				map[idx][1] = right;
			}
			idx++;
		}
		
		bfs();
	}
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visit[1] = true;
		for (int t = 0; t < 2; t++) {
			int size = q.size();
			for(int z=0; z<size; z++) {
				int cur = q.poll();

				if(map[cur][0] == 0 && map[cur][1] == 0 ) {
					// 리프노드
					ans++;
				}
				if(map[cur][0] != )
				
				for (int i = 1; i <= N; i++) {
					if (!visit[i] && map[cur][i] == 1) {
						q.add(i);
						visit[i] = true;
						//ans++;
					}
				}
			}
		}

	}
}
