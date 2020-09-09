package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ_경사로_14890_G3 {
	static int N, L;
	static int[][] map;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans=0;
		for(int i=0; i<N; i++) {
			int count=0;
			boolean chk =true;
			visit = new boolean[N];
			for(int j=0; j<N-1; j++) {
				if(Math.abs(map[i][j] - map[i][j+1]) > 1) {
					chk = false;
					break;
				}
				if(map[i][j] > map[i][j+1]) { // 현재가 클 때
					if(visit[j+1]) {
						chk = false;
						break;
					}
					visit[j+1] = true;
					for(int l=1; l<L; l++) {
						if(j+l+1  >= N || map[i][j+1+l] != map[i][j+1] || visit[j+1+l]) {  
							// 큰 위치부터 L만큼 높이 같은지 확인한다. 범위 벗어나는지 체크
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
					visit[j] =true;
					for(int l=1; l<L; l++) {
						if(j-l <0 ||map[i][j] != map[i][j-l] || visit[j-l]) {
							chk = false;
							break;
						}
						visit[j-l] =true;
					}
				}
			}
			if(chk) {// 해당 경사로는 가능하다
				ans++;
			}
		}

		for(int j=0; j<N; j++) {
			int count=0;
			boolean chk =true;
			visit = new boolean[N];
			for(int i=0; i<N-1; i++) {
				if(Math.abs(map[i][j] - map[i+1][j]) > 1) {
					chk = false;
					break;
				}
				
				if(map[i][j] > map[i+1][j]) { // 현재가 클 때
					if(visit[i+1]) {
						chk = false;
						break;
					}
					visit[i+1] = true;
					for(int l=1; l<L; l++) {
						if(i+l+1  >= N || map[i+1+l][j] != map[i+1][j] || visit[i+1+l]) {  
							// 큰 위치부터 L만큼 높이 같은지 확인한다. 범위 벗어나는지 체크
							chk = false;
							break;
						}
						visit[i+l+1] = true;
					}
				}else if(map[i][j] < map[i+1][j]) { // 현재가 작을 때
					if(visit[i]) {
						chk = false;
						break;
					}
					visit[i] =true;
					for(int l=1; l<L; l++) {
						if(i-l <0 ||map[i][j] != map[i-l][j] || visit[i-l]) {
							chk = false;
							break;
						}
						visit[i-l] =true;
					}
				}
			}
			if(chk) {// 해당 경사로는 가능하다
				ans++;
			}
		}
		
		System.out.println(ans);
	}

}
