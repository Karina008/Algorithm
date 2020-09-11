package algo_study_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_프린터큐_1966_S3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Queue<Data> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			int cur_max =0,temp=0;
			for (int i = 0; i < N; i++) {
				temp = Integer.parseInt(st.nextToken());
				q.add(new Data(temp, i));
				cur_max = Math.max(cur_max, temp); // 현재 큐에 가장 높은 수 저장
			}

			int count=1;
			while(true) {
				Data cur = q.poll();
				if(cur.num == cur_max) { // 큐에서 가장 큰 값
					if(cur.index == M) { // 출력값 찾음
						System.out.println(count);
						break;						
					}
					count++;
					int size = q.size();
					cur_max=0;
					for(int i=0; i<size; i++) { // 큐를 돌며 큰값 업데이트
						Data t_cur = q.poll();
						cur_max= Math.max(cur_max, t_cur.num);
						q.add(new Data(t_cur.num, t_cur.index));
					}
				}else { // 큰값 아니면 뒤로 다시 넣음
					q.add(new Data(cur.num, cur.index));
				}
			}
		}
	}

	static class Data{
		int num;
		int index;
		public Data(int num, int index) {
			this.num = num;
			this.index = index;
		}
	}
}
