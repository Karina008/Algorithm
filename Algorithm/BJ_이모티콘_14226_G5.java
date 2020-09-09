package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_이모티콘_14226_G5 {
	static int S;
	static boolean[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		visit = new boolean[10001][10001];
		// 클립보드 저장
		// 붙여넣기
		// 화면에 개수 하나 삭제
		
		bfs();
	}
	
	static void bfs() {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(1, 0, 1));
		visit[1][0] = true;
		while(!q.isEmpty()) {
			Data cur = q.poll();
			int len = cur.len;
			int clib = cur.clib;
			int count = cur.count;
			
//			System.out.println(len + " " + clib + " " + count);
			// 클립보드 저장
			if(!visit[len][len]) {
				visit[len][len] = true;
				q.add(new Data(len, len, count+1));
				
			}
			// 붙여넣기
			if(len+clib == S) {
				System.out.println(count);
				return;
			}else if(clib>0 && !visit[len+clib][clib])	{			
				visit[len+clib][clib] = true;
				q.add(new Data(len+clib, clib, count+1));				
			}
			// 화면에 개수 하나 삭제
			if(len-1 == S) {
				System.out.println(count);
				return;
			}else if(len-1 >= 1 && !visit[len-1][clib]) {
				visit[len-1][clib] = true;
				q.add(new Data(len-1, clib, count+1));
				
			}
		}
	}

	static class Data {
		int len;
		int clib;
		int count;
		public Data(int len, int clib, int count) {
			super();
			this.len = len;
			this.clib = clib;
			this.count = count;
		}
		
		
	}
}
