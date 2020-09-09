package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_비밀모임_13424_G5 {
	static int N, M, map[][], dist[], total[];
	static final int Val = 100000001;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			dist = new int[N + 1];
			total = new int[N + 1];
			map = new int[N + 1][N + 1];

			for (int i = 1; i < N + 1; i++) {
				dist[i] = Val;
				total[i] = 0;
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = Val;
				}
			}
			int from, to, cost;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				if (map[from][to] > cost) {
					map[from][to] = cost;
					map[to][from] = cost;
				}
			}

			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= K; i++) {
				int idx = Integer.parseInt(st.nextToken());
				int temp = map[idx][idx];
				solution(idx);
//				System.out.println(Arrays.toString(dist));
				for (int j = 1; j < N + 1; j++) {
					if (dist[j] != Val)
						total[j] += dist[j];
					dist[j] = Val;
				}
//				System.out.println(Arrays.toString(total));
				map[idx][idx] = temp;
			}
//			for (int i = 1; i <= N; i++) {
//				int temp = map[i][i];
//				solution(i);
//				System.out.println(Arrays.toString(dist));
//				for (int j = 1; j < N + 1; j++) {
//					if (dist[j] != Val)
//						total[j] += dist[j];
//					dist[j] = Val;
//				}
//				System.out.println(Arrays.toString(total));
//				map[i][i] = temp;
//			}
			
			int Min= Integer.MAX_VALUE;
			for(int i=1; i<=N; i++) {
				Min = Math.min(Min, total[i]);
			}
			
			for(int i=1; i<=N; i++) {
				if(total[i] == Min) {
					System.out.println(i);
					break;
				}
			}

		}
	}

	static void solution(int start) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(start);
		map[start][start] = 0;
		dist[start] = 0;
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			for (int i = 1; i < N + 1; i++) {
				if (dist[i] > map[cur][i] + dist[cur]) {
					dist[i] = map[cur][i] + dist[cur];
					pq.add(i);
				}
			}
		}
	}

}
