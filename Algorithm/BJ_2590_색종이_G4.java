package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2590_색종이_G4 {
	static int[] page = new int[7];
	static int ans = Integer.MAX_VALUE;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=1; i<7; i++) {
			page[i] = Integer.parseInt(br.readLine());
		}
		// 6칸 짜리 색종이는 바로 종이 한장씩 사용
		int count = page[6]; 
		map = new int[6][6];
		
		boolean chk = false; // 넣어야하는 종이 있는지 체크
		for(int k=1; k<=5; k++) {
			if(page[k] != 0) {
				chk = true;
				break;
			}
		}
		if(chk) {
			// 함수 호출해 색종이를 넣어줌
			start(0,0, count+1);
			System.out.println(ans);
		}else {
			// 넣어야할 색종이 없으므로 바로 출력
			System.out.println(count);
		}
	}
	
	public static void start(int x, int y, int count) {		
		for(int i=x; i<6; i++) {
			for(int j=0; j<6; j++) {
				if(map[i][j] == 1) {
					continue;
				}
				for(int k=5; k>0; k--) {
					if(page[k] == 0 || i+k >6 || j+k >6) {
						continue;
					}
					
					if(check(i,j,k,1)) {
						page[k]--;						
						fill(i,j,k,1);
						start(i,j,count);
						return;
					}
				}
			}
		}
		
		for(int k=1; k<=5; k++) {
			if(page[k] != 0) {
				map = new int[6][6];
				start(0,0,count+1);
				return;
			}
		}
		ans = Math.min(ans, count);
		return;
	}
	
	// 해당 범위 만큼 value 값이 이미 있는지 검사함
	public static boolean check(int x, int y, int k, int value) {
		for(int i=x; i<x+k; i++) {
			for(int j=y; j<y+k; j++) {
				if(map[i][j] == value) {
					return false;
				}
			}
		}
		return true;		
	}
	
	// 해당 범위 만큼 value 값으로 채워줌
	public static void fill(int x, int y, int k, int value) {
		for(int i=x; i<x+k; i++) {
			for(int j=y; j<y+k; j++) {
				map[i][j] = value;
			}
		}
	}

}
