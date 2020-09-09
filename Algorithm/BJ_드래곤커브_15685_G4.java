package algo_study_05;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ_드래곤커브_15685_G4 {
	static int N;
	static int[][] map = new int[101][101];
	static int dy[] = {0, -1, 0, 1};
	static int dx[] = {1, 0, -1, 0};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=0; i<N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt(); // 동 북 서 남
			int g = sc.nextInt();
			
			makedir(x,y,d,g);
		}
		int ans=0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1) {
					ans++;
				}
			}
		}
		
//		for(int i=0; i<100; i++) {
//			for(int j=0; j<100; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
		
		System.out.println(ans);
	}
	
	public static void makedir(int x, int y, int d, int g) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(d);
		
		for(int i=0; i<g; i++) {
			for(int j=list.size()-1; j>=0; j--) {
				list.add((list.get(j)+1)%4);
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			map[x][y] = 1;
			x += dx[list.get(i)];
			y += dy[list.get(i)];
		}
		
		map[x][y]=1;
	}

}
