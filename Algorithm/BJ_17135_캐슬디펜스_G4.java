package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_17135_캐슬디펜스_G4 {
	static int N, M, D, ans = 0;
	static int[][] map;
	static boolean[] archer;
	static LinkedList<Enemy> enemy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 사정거리

		map = new int[N][M];
		enemy = new LinkedList<>();
		archer = new boolean[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		make(0);
		System.out.println(ans);
	}

	// 궁수 공격
	public static void attack() {
		int arr[] = new int[3];
		for (int i = 0; i < 3; i++) {
			arr[i] = -1;
		}
		int arri = 0;
		for (int i = 0; i < M; i++) {
			// 궁수 선택
			if (archer[i]) {
				int archerindex = i;
				int enemysize = enemy.size();
				int index1 = -1;// 적 인덱스 저장
				int dist1 = Integer.MAX_VALUE; // 적 거리 저장
				for (int j = 0; j < enemysize; j++) {
					Enemy enemy1 = enemy.get(j);
					// 적이 이미 죽었음
					if (!enemy1.alive) {
						continue;
					}
					int dist = Math.abs(enemy1.y - archerindex) + Math.abs(enemy1.x - N);

					if (dist < dist1 && dist <= D) {
						// 새롭게 발견한 적이 가까울 때
						dist1 = dist;
						index1 = j;
					} else if (dist == dist1) {
						// 거리가 일치하면 왼쪽 적 선택
						if (enemy1.y < enemy.get(index1).y) {
							dist1 = dist;
							index1 = j;
						}
					}
				}

				if (index1 != -1) {
					// 해당 적 번호 저장
					arr[arri++] = index1;
				}
			}
		}

		// 해당 적 죽임
		for (int i = 0; i < 3; i++) {
			int idx = arr[i];
			if (idx != -1) {
				enemy.get(idx).alive = false;
			}
		}
	}

	// 적 이동
	public static void move() {
		int enemysize = enemy.size();
		for (int i = 0; i < enemysize; i++) {
			Enemy enemy1 = enemy.get(i);
			// 살아있는 적 선택
			if (enemy1.alive) {
				if (enemy1.x != N) {
					// 한칸 아래로
					enemy1.x++;
				}
			}
		}

		// 성에 도착한 적 list에서 제거
		for (int i = 0; i < enemy.size(); i++) {
			Enemy enemy1 = enemy.get(i);
			if (enemy1.x == N) {
				enemy.remove(i);
				i--;
			}
		}
	}

	public static void make(int index) {
		if (index == 3) {
			enemy.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						enemy.add(new Enemy(i, j, true));
					}
				}
			}

			boolean finish = false;
			while (!finish) {
				finish = true;
				for (int i = 0; i < enemy.size(); i++) {
					Enemy enemy1 = enemy.get(i);
					if (enemy1.alive) {
						attack();
						move();
						finish = false;
						break;
					}
				}
			}
			// 죽인 적 갱신
			ans = Math.max(ans, enemy.size());
			return;
		}

		for (int i = 0; i < M; i++) {
			if (archer[i]) {
				continue;
			}
			archer[i] = true;
			make(index + 1);
			archer[i] = false;
		}
	}

	public static class Enemy {
		int x;
		int y;
		boolean alive;

		public Enemy(int x, int y, boolean alive) {
			this.x = x;
			this.y = y;
			this.alive = alive;
		}
	}
}
