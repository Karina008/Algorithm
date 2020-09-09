package algo_study_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_미로만들기_S4 {
	static int dx[] = {-1,0,1, 0}; // 북동남서
	static int dy[] = { 0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];
		int x=50;
		int y=50;
		int d=2; // 0: 북, 1: 동, 2: 남, 3: 서
		map[x][y] = 1;
		int minWidth =  50; // 왼쪽 너비값 위치
		int maxWidth = 50;  // 오른쪽 너비값 위치
		int minHeight = 50; // 위쪽 높이값 위치
		int maxHeight = 50; // 아래쪽 높이값 위치
		String str = br.readLine();
		for(int t=0; t<T; t++) { 
			if(str.charAt(t) == 'F') { // 전진일 때
				x+=dx[d];
				y+=dy[d];
				map[x][y]=1;
				minWidth = Math.min(minWidth, y);
				minHeight = Math.min(minHeight, x);
				maxWidth = Math.max(maxWidth, y);
				maxHeight = Math.max(maxHeight, x);				
			}else if(str.charAt(t) == 'L') { // 왼쪽 전환
				if(d==0) {
					d=3;
				}else {
					d--;
				}
			}else if(str.charAt(t) == 'R') { // 오른쪽 전환
				d= (d+1)%4;	
			}else {

			}
		}		
		
		for(int i=minHeight; i<=maxHeight; i++) { // 제일 위쪽 높이부터 아래쪽 높이까지
			for(int j=minWidth; j<= maxWidth; j++) { // 왼쪽 너비부터 오른쪽 너비까지
				if(map[i][j] == 1) { // 이동한 구역일 때
					System.out.print('.');
				}else {
					System.out.print('#');
				}
			}
			System.out.println();
		}
		
	}

}
