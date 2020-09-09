package algo_study_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_프린터큐_1966_S3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			Queue<Data> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서의 수
			int M = Integer.parseInt(st.nextToken()); // 궁금한 위치

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {

				q.add(new Data(i, Integer.parseInt(st.nextToken())));
			}
			int ans=1;
			while (!q.isEmpty()) {
				Data cur = q.poll();
				boolean chk = true;

				Iterator it = q.iterator();
				while (it.hasNext()) {
					Data idx = (Data) it.next();
					if (idx.key > cur.key) {
						chk = false;
						break;
					}
				}

				if (!chk) {
					q.add(cur);
				}else {
					if(cur.index == M) {
						System.out.println(ans);
					}else {
						ans++;
					}
				}
			}
		}

	}

	public static class Data {
		int index;
		int key;
		public Data(int index, int key) {
			this.index = index;
			this.key = key;
		}
		
		
	}
}
