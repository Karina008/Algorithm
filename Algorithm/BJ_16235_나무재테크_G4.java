package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_16235_나무재테크_G4 {
	static int N, M, K;
	static int[][] map, energymap;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayList<Tree> tree, live, dead;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 땅 크기
		M = Integer.parseInt(st.nextToken()); // 심은 나무 개수
		K = Integer.parseInt(st.nextToken()); // 지난 년도 수

		map = new int[N + 1][N + 1];
		energymap = new int[N + 1][N + 1];
		tree = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				energymap[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			tree.add(new Tree(x, y, a));
		}

		start();
		System.out.println(tree.size());
	}

	public static void start() {
		for (int k = 0; k < K; k++) {
			live = new ArrayList<>(); // 살아남은 나무 저장
			dead = new ArrayList<>(); // 죽은 나무 저장
			spring();
			summer();
			fall();
			winter();
		}
	}

	public static void spring() {
		// 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수
		// 있다.
		// 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
		// 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
		Collections.sort(tree); // 나이 적은 순으로 정렬
		for (int i = 0; i <tree.size(); i++) {
			Tree cur = tree.get(i);
			int x = cur.x;
			int y = cur.y;
			int age = cur.age;

			if (map[x][y] >= age) {
				// 영양 보급 가능
				map[x][y] -= age;
				live.add(new Tree(x,y,age+1));
			} else {
				// 영앙 보급 불가능
				dead.add(new Tree(x,y,age));
			}
		}
		tree.clear();
		// 살아남은 나무들 기존 ArrayList로 저장
		tree = new ArrayList<Tree>(live); 
	}

	public static void summer() {
		// 여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
		// 소수점 아래는 버린다.
		for (int i = 0; i < dead.size(); i++) {
				// 나무 죽음
				Tree cur = dead.get(i);
				map[cur.x][cur.y] += cur.age / 2;
		}
	}

	public static void fall() {
		// 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다
		int size = live.size();
		for (int i = 0; i < size; i++) {
			if (live.get(i).age % 5 != 0) {
				continue;
			}
			// 8방향 번식 시작
			Tree cur = live.get(i);
			for (int k = 0; k < 8; k++) {
				int nx = cur.x + dx[k];
				int ny = cur.y + dy[k];
				if (1 > nx || nx > N || 1 > ny || ny > N) {
					continue;
				}
				tree.add(new Tree(nx, ny, 1));
			}

		}
	}

	public static void winter() {
		// 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += energymap[i][j];
			}
		}
	}

	static class Tree implements Comparable<Tree> {
		int x;
		int y;
		int age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		// age 순서로 정렬함
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
			
			// 아래와 같이 정렬하면 런타임 에러 발생함
//			if (o.age < this.age) {
//				return 1;
//			} else {
//				return -1;
//			}
		}
	}
}
