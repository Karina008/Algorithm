package algo_study_05;

import java.util.Scanner;

public class BJ_트리순회_1991_S1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		int[][] map = new int[26][2];
		for(int i=0; i<N; i++) {
			String str = sc.nextLine();
			
			int x = str.charAt(0)-'A';
			int y = str.charAt(2);
			int z = str.charAt(4);
			
			if(y=='.') 
				map[x][0] = -1;
			else
				map[x][0] = y-'A';
			if(z=='.')
				map[x][1] = -1;
			else
				map[x][1] = z-'A';
		}
		
		preorder(map,0);
		System.out.println();
		inorder(map,0);
		System.out.println();
		postorder(map, 0);
		System.out.println();
	}

	private static void postorder(int[][] map, int i) {
		if(i==-1)
			return;
		postorder(map, map[i][0]);
		postorder(map, map[i][1]);
		System.out.print((char)(i+'A'));
	}

	private static void inorder(int[][] map, int i) {
		if(i==-1)
			return;
		inorder(map, map[i][0]);
		System.out.print((char)(i+'A'));
		inorder(map, map[i][1]);
	}

	private static void preorder(int[][] map, int i) {
		if(i==-1)
			return;
		System.out.print((char)(i+'A'));
		preorder(map, map[i][0]);
		preorder(map, map[i][1]);
	}

}
