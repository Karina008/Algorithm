package algo_study_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_보물상자비밀번호_5658 {
	static int N, K;
	static int[] arr;
	static String str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[N];
			str = br.readLine();

			int idx = 0;
			int gap = N/4;
			for (int j = 0; j < gap; j++) {
				int idx2 = 0;
				for (int i = 0; i < gap-1; i++) {
//				String temp = Character.toString(str.charAt(i));
					String temp = str.substring(idx2 * gap, idx2 * gap + gap);
					arr[idx++] = Integer.parseInt(temp, 16);
					idx2++;
				}
				rotate();

			}
			Arrays.sort(arr);
			//System.out.println(Arrays.toString(arr));
//			int count = 0;
//			if (K == 1)
//				System.out.println(arr[N-1]);
//			else {
//				for (int i = N-2; i >=0; i--) {
//					if (count == K - 1) {
//						System.out.println(arr[count]);
//						break;
//					}
//					if (arr[i + 1] != arr[i])
//						count++;
//				}
//			}
			if (K == N)
				System.out.println(arr[0]);
			else if(K==1) 
				System.out.println(arr[N-1]);
			else {
				int idx2 = N - 1;
				for (int i = N - 1; i >= 0; i--) {
					if (K == 0) {
						// System.out.println(arr[i]);
						break;
					}
					if (arr[i] != arr[i - 1]) {
						K--;
						idx2--;
					}
					// System.out.println(arr[i]);
				}
				System.out.println(arr[idx2]);
			}


		}
	}

	public static void rotate() {
		int size = str.length();
		str = str.substring(size - 1) + str.substring(0, size - 1);
		// System.out.println(str);
	}

}
