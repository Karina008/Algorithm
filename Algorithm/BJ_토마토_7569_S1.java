package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_토마토_7569_S1 {
	static int N,M,H;
	static int[][][] map;
	static boolean[][][] visit;
	static int[] dh = {-1, 1, 0, 0, 0, 0};
	static int[] dx = {0, 0, 1, -1, 0, 0};
	static int[] dy = {0, 0, 0, 0, 1, -1};
	static boolean change = true;
	static Queue<Data> q = new LinkedList<Data>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);
		map = new int[H+1][N+1][M+1];
		visit = new boolean[H+1][N+1][M+1];
		
		for(int h=1; h<=H; h++) {
			for(int n=1; n<=N; n++) {
				str = br.readLine().split(" ");
				for(int m=1; m<=M; m++) {
					map[h][n][m] = Integer.parseInt(str[m-1]);
					if(map[h][n][m] == 1) {
						q.offer(new Data(h,n,m,0));
					}
				}
			}
		}

		int count=0;
		while(change) {
			change = false;
			count++;
			
			bfs();
		}
		boolean chk = true;
		for(int h=1; h<=H; h++) {
			for(int n=1; n<=N; n++) {
				for(int m=1; m<=M; m++) {
					if(map[h][n][m] == 0) 
						chk = false;
				}
			}
		}
		if(!change &&  count==1)  
			System.out.println("0");
		else if(chk)
			System.out.println(count-1);
		else if(!chk)
			System.out.println("-1");
	}

	public static void bfs() {
		int size  = q.size();
		
		for(int i=0; i<size; i++) {
			Data temp  = q.poll();
			int h= temp.h;
			int x= temp.x;
			int y= temp.y;
			int cnt = temp.cnt;
			
			for(int k=0; k<6; k++) {
				int nh = h+dh[k];
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				if(isIn(nh, nx, ny)) {
					if(!visit[nh][nx][ny] && map[nh][nx][ny] == 0) {
						map[nh][nx][ny] = cnt+2;
						change= true;
						q.offer(new Data(nh,nx,ny,cnt+1));
					}
				}
			}
		}
		
	}
	
	public static boolean isIn(int h, int x, int y) {
		return (0<h && h<=H && 0<x && x<= N && 0<y && y<= M);
	}
	
}
class Data {
	int h;
	int x;
	int y;
	int cnt;
	
	public Data(int h, int x, int y, int cnt) {
		this.h = h;
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	
	
	
}
