package algo_study_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_무선충전_5644 {
	static int M,A;
	static int[] Ainfo;
	static int[] Binfo;
	static ArrayList<AP> list;
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			Ainfo = new int[M];
			Binfo = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				Ainfo[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				Binfo[i] = Integer.parseInt(st.nextToken());
			}
			
			list = new ArrayList<>();
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int y= Integer.parseInt(st.nextToken());
				int x= Integer.parseInt(st.nextToken());
				int c= Integer.parseInt(st.nextToken());
				int p= Integer.parseInt(st.nextToken());
				
				list.add(new AP(x,y,c,p));
			}
			
			int ans = move();
			System.out.println("#"+t+" "+ ans);
		}
	}
	
	public static int move() { 
		int x1 = 1;
		int y1 = 1;
		int x2 = 10;
		int y2 = 10;
		
		int sum = getMax(x1,y1,x2,y2);
		
		for(int t=0; t<M; t++) {
			x1 += dx[Ainfo[t]];
			y1 += dy[Ainfo[t]];
			x2 += dx[Binfo[t]];
			y2 += dy[Binfo[t]];
			sum += getMax(x1,y1,x2,y2);
		}
		
		return sum;
	}
	
	public static int getMax(int x1, int y1, int x2, int y2) {
		int[][] amount = new int[2][A];
		
		for(int i=0; i<A; i++) {
			amount[0][i] = check(x1,y1,i);
		}

		for(int i=0; i<A; i++) {
			amount[1][i] = check(x2,y2,i);
		}
		
		int max=0;
		for(int i=0; i<A; i++) {
			for(int j=0; j<A; j++) {
				int sum = amount[0][i] +amount[1][j];
				if(i==j && amount[0][i] == amount[1][j]) {
					sum = sum/2;
				}
				if(sum > max)
					max = sum;
				
			}
		}
		
		return max;
	}
	
	public static int check(int x, int y, int index) {
		int a = Math.abs(x-list.get(index).x);
		int b = Math.abs(y-list.get(index).y);
		int dist = a+b;
		
		if(dist <= list.get(index).c)
			return list.get(index).p;
		else
			return 0;
	}
	
	static class AP {
		int x;
		int y;
		int c;
		int p;
		public AP(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		
	}

}
