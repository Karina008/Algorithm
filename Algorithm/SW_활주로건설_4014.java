package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_활주로건설_4014 {
	static int N, X, ans;
	static int[][] map;
	static boolean[] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 변길이
			X = Integer.parseInt(st.nextToken()); // 경사로 길이
			
			map = new int[N][N];
			//visit = new boolean[N][N];
			ans=0;
			
			for(int i=0; i<N; i++) {
				 st = new StringTokenizer(br.readLine());
				 for(int j=0; j<N; j++) {
					 map[i][j] = Integer.parseInt(st.nextToken());
				 }
			}
			
			
			calc();
			System.out.println("#" + t+ " " + ans);
		}
	}
	
	public static void calc() {
		for(int i=0; i<N; i++) {
			visit = new boolean[N];
			boolean chk = true;
			for(int j=0; j<N-1; j++) {
				if(Math.abs(map[i][j] - map[i][j+1]) > 1) {
					chk = false;
					break;
				}
				if(map[i][j] > map[i][j+1]) { // 현재가 더 클 때
					if(visit[j+1]) { // 경사로 둘 수 없음.
						chk = false;
						break;
					}
					visit[j+1] = true;
					for(int l=1; l<X; l++) {
						if(j+l+1 >= N || map[i][j+1+l] != map[i][j+1] || visit[j+1+l]) {
							chk = false;
							break;
						}
						visit[j+l+1] = true;
					}
				}else if(map[i][j] < map[i][j+1]) { // 현재가 작을 때
					if(visit[j]) {
						chk = false;
						break;
					}
					visit[j] = true;
					for(int l=1; l<X; l++) {
						if(j-l <0 || map[i][j] != map[i][j-l] || visit[j-l]) {
							chk =false;
							break;
						}
						visit[j-l] = true;
					}
					
				}
			}
			if(chk)
				ans++;
		}
		
		for(int i=0; i<N; i++) {
			visit = new boolean[N];
			boolean chk = true;
			for(int j=0; j<N-1; j++) {
				if(Math.abs(map[j][i] - map[j+1][i]) > 1) {
					chk = false;
					break;
				}
				if(map[j][i] > map[j+1][i]) { // 현재가 더 클 때
					if(visit[j+1]) { // 경사로 둘 수 없음.
						chk = false;
						break;
					}
					visit[j+1] = true;
					for(int l=1; l<X; l++) {
						if(j+l+1 >= N || map[j+1+l][i] != map[j+1][i] || visit[j+1+l]) {
							chk = false;
							break;
						}
						visit[j+l+1] = true;
					}
				}else if(map[j][i] < map[j+1][i]) { // 현재가 작을 때
					if(visit[j]) {
						chk = false;
						break;
					}
					visit[j] = true;
					for(int l=1; l<X; l++) {
						if(j-l <0 || map[j][i] != map[j-l][i] || visit[j-l]) {
							chk =false;
							break;
						}
						visit[j-l] = true;
					}
					
				}
			}
			if(chk)
				ans++;
		}
	}

}
