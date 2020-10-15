package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14696_딱지놀이_B1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int n=0; n<N; n++) {
			int[] player1 = new int[5];
			int[] player2 = new int[5];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for(int m=0; m<M; m++) {
				int index = Integer.parseInt(st.nextToken());
				player1[index]++;
			}
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			for(int m=0; m<M; m++) {
				int index = Integer.parseInt(st.nextToken());
				player2[index]++;
			}
			
			int ans = whowin(player1,player2);
			if(ans == 1) {
				System.out.println("A");
			}else if(ans == 2){
				System.out.println("B");
			}else {
				System.out.println("D");
			}
		}
	}
	
	public static int whowin(int[] player1, int[] player2) {
		for(int i=4; i>=0; i--) {
			if(player1[i] > player2[i]) {
				return 1;
			}else if(player1[i] < player2[i]) {
				return 2;
			}
		}
		return 0;
	}

}
