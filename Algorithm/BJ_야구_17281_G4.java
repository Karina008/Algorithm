package algo_study_05;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_야구_17281_G4 {
	static int N, Max = Integer.MIN_VALUE;
	static boolean[] visit;
	static int[] player;
	static int[][] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		// 점수 구함.
		arr = new int[N][10];
		for (int n = 0; n < N; n++) {
			for (int j = 1; j <= 9; j++) {
				arr[n][j] = sc.nextInt();
			}
		}
		visit = new boolean[10];
		player = new int[10];
		player[4] = 1;
		make(1);
		System.out.println(Max);
		
	}

	public static int game() {
		int score = 0;
		int out=0;
		int roo[];
		int hit=1;
		
		for(int n=0; n<N; n++) {
			out =0;
			roo = new int[4];
			while(true) {
				int cur = arr[n][player[hit]];
				if(hit==9)
					hit=1;
				else 
					hit++;
				if(cur ==1 ) { // 안타
					 if(roo[3]>0) {
						 score++;
						 roo[3]=0;
					 }if(roo[2]>0) {
						 roo[3]=1;
						 roo[2]=0;
					 }if(roo[1]>0) {
						 roo[2]=1;
						 roo[1]=0;
					 }
					 roo[1] = 1;
					 
				}else if(cur ==2) {  // 2안타
					if(roo[3]>0) {
						 score++;
						 roo[3]=0;
					 }if(roo[2]>0) {
						 score++;
						 roo[2]=0;
					 }if(roo[1]>0) {
						 roo[3]=1;
						 roo[1]=0;
					 }
					 roo[2] = 1;
				}else if(cur ==3 ) { // 3안타
					if(roo[3]>0) {
						 score++;
						 roo[3]=0;
					 }if(roo[2]>0) {
						 score++;
						 roo[2]=0;
					 }if(roo[1]>0) {
						 score++;						 
						 roo[1]=0;
					 }
					 roo[3] = 1;
				}else if(cur == 4) { // 홈런
					if(roo[3]>0) {
						 score++;
						 roo[3]=0;
					 }if(roo[2]>0) {
						 score++;
						 roo[2]=0;
					 }if(roo[1]>0) {
						 score++;						 
						 roo[1]=0;
					 }
					 score++;
				}else if(cur ==0) {  // 아웃
					out++;
					if(out==3) {
						break;
					}
				}
				
			}
			
		}
		
		return score;
	}

	public static void make(int cnt) {
		if (cnt == 4) {
			make(cnt + 1);
			return;
		}
		if (cnt == 10) {
			int score = game();
			if (Max < score)
				Max = score;
			return;
		}
		for (int i = 2; i < 10; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			player[cnt] = i;
			make(cnt + 1);
			visit[i] = false;
		}
	}
}
