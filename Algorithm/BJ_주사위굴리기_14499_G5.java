package algo_study_05;

import java.util.Scanner;

public class BJ_주사위굴리기_14499_G5 {
	static int N,M,x,y,K;
	static int[][] map;
	static int[] dir;
	static int[] dice = new int[7];
	static int[] dx= {0, 0, -1, 1};
	static int[] dy= {1,-1, 0, 0};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		N = sc.nextInt(); 
		M = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[N][M];
		dir = new int[K];
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for(int k=0; k<K; k++) {
			int way= sc.nextInt();
			int nx = x+ dx[way-1];
			int ny = y+ dy[way-1];
			if( 0<= nx && nx <N && 0<= ny && ny<M) {
				sol(way);
				
				if(map[nx][ny] ==0) {
					map[nx][ny] = dice[6];
				}else if(map[nx][ny] !=0) {
					dice[6] = map[nx][ny];
					map[nx][ny]=0;
				}
				
				x = nx;
				y = ny;
				System.out.println(dice[1]);
			}
			
			
		}
		
		
	}

	public static void sol(int way) {
		int[] temp = dice.clone();
		// 1윗면, 6밑면
		// 동서북남, 1234
		if(way == 1) {
			dice[1] = temp[4];
			dice[4] = temp[6];
			dice[3] = temp[1];
			dice[6] = temp[3];			
		}else if(way == 2) {
			dice[1] = temp[3];
			dice[6] = temp[4];
			dice[3] = temp[6];
			dice[4] = temp[1];
		}else if(way== 3) {
			dice[1] = temp[5];
			dice[5] = temp[6];
			dice[6] = temp[2];
			dice[2] = temp[1];
		}else if(way==4) {
			dice[1] = temp[2];
			dice[2] = temp[6];
			dice[6] = temp[5];
			dice[5] = temp[1];
		}
	}
}
