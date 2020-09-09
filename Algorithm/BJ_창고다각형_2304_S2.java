package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_창고다각형_2304_S2 {
	static int N, map[][], max_x = 0, max_y = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int L, H;
		max_x = 0;
		max_y = 0;

		map = new int[1001][1001];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			for (int j = 0; j < H; j++) {
				map[j][L] = 1;
			}
			max_x = Math.max(max_x, H);
			max_y = Math.max(max_y, L);
		}

		for (int i = 0; i <= max_y; i++) {
			if (map[0][i] == 1) {
				for (int j = 0; j < 1001; j++) {
					if (map[j][i] != 1) {				
						find(i, j - 1);
						break;
					}
				}
			}
		}		
		for (int i = max_y; i >=0; i--) {
			if (map[0][i] == 1) {
				for (int j = 0; j < 1001; j++) {
					if (map[j][i] != 1) {					
						find2(i, j - 1);
						break;
					}
				}
			}
		}		
		int ans=0;
		for (int i = 0; i <= max_x; i++) {
			for (int j = 0; j <= max_y; j++) {
				if(map[i][j] == 1) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	public static void find(int x, int y) {
		for(int i=x+1; i<=max_y; i++) {
			if(map[y][i] == 1 ) {
				make(x,y,i);
				return;
			}
		}
	}
	public static void make(int x, int y, int index) {
		for(int i=x; i<=index; i++) {
			for(int j=0; j<=y; j++) {
				map[j][i] = 1;
			}
		}
	}
	public static void find2(int x, int y) {
		for(int i=x-1; i>=0; i--) {
			if(map[y][i] == 1 ) {
				make2(x,y,i);
				return;
			}
		}
	}	
	public static void make2(int x, int y, int index) {
		for(int i=index; i<=x; i++) {
			for(int j=0; j<=y; j++) {
				map[j][i] = 1;
			}
		}
	}
}
