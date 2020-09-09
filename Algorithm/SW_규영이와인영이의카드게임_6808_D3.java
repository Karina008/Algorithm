package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_규영이와인영이의카드게임_6808_D3 {
	static int[] num, map, map2;
	
	static int win, lose, user1, user2;
	static boolean[] visit = new boolean[9];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<= T; t++) {
			num = new int[9];
			map = new int[9];
			map2 = new int[9];
			visit = new boolean[19];
			win=0;
			lose=0;
			
			st = new StringTokenizer(br.readLine()); 
			for(int i=0; i<9; i++) {
				map[i] = Integer.parseInt(st.nextToken()); // 규영 카드(고정)
				visit[map[i]] = true;
			}
			
			int index=0;
			for(int i=1; i<=18; i++) {
				if(!visit[i]) {
					map2[index++] = i; // 인영 카드(유동)
				}
			}
			visit = new boolean[9];
			make(0); // 순열 생성
			System.out.println("#" + t + " " + win + " " + lose);
		}
	}
	
	public static void make(int index) {
		if(index==9) {
			user1=0; // 인영
			user2=0; // 규영(고정)
			calc(); // 점수계산
			if(user1>user2) {
				lose++;
			}else if(user1 <user2) {
				win++;
			}
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(visit[i]) 
				continue;
			visit[i] = true;
			num[index] = i;
			make(index+1);
			visit[i] = false;
		}
	}
	
	//승패 계산
	public static void calc() {
		for(int i=0; i<9; i++) {
			if(map2[num[i]] > map[i] ) {
				user1 += map2[num[i]]+ map[i];
			}else if(map2[num[i]] < map[i]) {
				user2 += map2[num[i]]+ map[i];
			}
		}		
	}
}
