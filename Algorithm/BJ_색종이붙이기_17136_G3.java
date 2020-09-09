package algo_study_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_색종이붙이기_17136_G3 {
	static int[][] map = new int[10][10];
	static int[] paper = {0,5,5,5,5,5};
	static int Min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0);
		if(Min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(Min);
		
	}
	public static void dfs(int index, int count) {
		if(index==100) {
			Min = Math.min(Min, count);
			return;
		}
		if(Min <= count)
			return;
		int x=index/10;
		int y=index%10;
		if(map[x][y]==1) {
			for(int i=5; i>0; i--) {
				if(paper[i]>0) {
					if(check(x,y,i)) {
						fill(x,y,i,0);
						paper[i]--;
						dfs(index+1,count+1);
						fill(x,y,i,1);
						paper[i]++;
					}
				}
			}
		}else {
			dfs(index+1, count);
		}
	}
	
	public static boolean check(int x, int y, int size) {
		if(x+size>10 || y+size>10)
			return false;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[x+i][y+j]!=1)
					return false;
			}
		}
		return true;
	}
	
	public static void fill(int x,  int y, int size, int state) {
		for(int i=0; i<size; i++) { 
			for(int j=0; j<size; j++) { 
				map[x+i][y+j] = state;
			}
		}
	}

}
