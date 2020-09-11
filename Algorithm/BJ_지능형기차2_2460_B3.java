package algo_study_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_지능형기차2_2460_B3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int total=0, max=0;
		for(int i=1; i<=10; i++) {
			st= new StringTokenizer(br.readLine());
			total -= Integer.parseInt(st.nextToken()); // 승객이 내림
			total += Integer.parseInt(st.nextToken()); // 승객이 탑승함
			max = Math.max(max, total);
		}
		System.out.println(max);
	}

}
