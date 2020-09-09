package algo_study_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_구슬탈출2_13460_G3 {
	static int N, M, Rx, Ry, Bx, By;
	static char[][] map;
	static boolean[][][][] visit = new boolean[10][10][10][10];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		Node node = new Node(0, 0, 0, 0, 0);

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					node.Rx = i;
					node.Ry = j;
				} else if (map[i][j] == 'B') {
					node.Bx = i;
					node.By = j;
				}
			}
		}
		bfs(node);

	}

	public static void bfs(Node ball) {
		Queue<Node> q = new LinkedList<>();
		q.add(ball);

		while (!q.isEmpty()) {
			Node cur = q.poll();
			visit[cur.Rx][cur.Ry][cur.Bx][cur.By] = true;

			if (cur.cnt >= 10) {
				System.out.println(-1);
				return;
			}

			for (int k = 0; k < 4; k++) {
				int nBx = cur.Bx;
				int nBy = cur.By;
				while (map[nBx + dx[k]][nBy + dy[k]] != '#') {
					nBx += dx[k];
					nBy += dy[k];
					if (map[nBx][nBy] == 'O')
						break;
				}

				int nRx = cur.Rx;
				int nRy = cur.Ry;
				while (map[nRx + dx[k]][nRy + dy[k]] != '#') {
					nRx += dx[k];
					nRy += dy[k];
					if (map[nRx][nRy] == 'O')
						break;
				}

				if (map[nBx][nBy] == 'O')
					continue;
				if (map[nRx][nRy] == 'O') {
					System.out.println(cur.cnt + 1);
					return;
				}

				if (nBx == nRx && nBy == nRy) {
					switch (k) {
					// 북동남서
					case 0:
						if (cur.Bx > cur.Rx) {
							nBx += 1;
						} else {
							nRx += 1;
						}
						break;

					case 1:
						if (cur.By > cur.Ry) {
							nRy -= 1;
						} else {
							nBy -= 1;
						}
						break;
					case 2:
						if (cur.Bx > cur.Rx) {
							nRx -= 1;
						} else {
							nBx -= 1;
						}
						break;
					case 3:
						if (cur.By > cur.Ry) {
							nBy += 1;
						} else {
							nRy += 1;
						}
					}
				}
				
				if(!visit[nRx][nRy][nBx][nBy]) {
					q.add(new Node(nRx,nRy,nBx,nBy, cur.cnt+1));
				}
			}
		}
		System.out.println("-1");
	}

	public static class Node {
		int Rx;
		int Ry;
		int Bx;
		int By;
		int cnt;

		public Node(int rx, int ry, int bx, int by, int cnt) {
			Rx = rx;
			Ry = ry;
			Bx = bx;
			By = by;
			this.cnt = cnt;
		}

	}
}
