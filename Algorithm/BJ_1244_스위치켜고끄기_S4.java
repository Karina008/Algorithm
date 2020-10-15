package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1244_스위치켜고끄기_S4 {
	static int N, M;
	static int[] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 전구 수
		map = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine()); // 학생수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); // 남:1, 여:2
			int index = Integer.parseInt(st.nextToken()); // 받은 수

			onoff(gender, index);
		}
		
		for(int i = 1; i <= N; i++) {
	         System.out.print(map[i] + " ");
	         if(i % 20 == 0) { // 20개 출력하고 줄바꿈
	        	 System.out.println();
	         }
	      }
	}

	public static void onoff(int gender, int index) {
		if (gender == 1) {
			// 남성
			// 위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다.
			for (int i = index; i <= N; i++) {
				if (i % index != 0) {
					continue;
				}
				if (map[i] == 0) {
					map[i] = 1;
				} else {
					map[i] = 0;
				}
			}
		} else {
			// 여성
			// 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서
			// 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를 모두 바꾼다.
			// 구간에 속한 스위치 개수는 항상 홀수
			// 현재 위치 값 바꿔줌 
			if (map[index] == 0) {
				map[index] = 1;
			} else {
				map[index] = 0;
			}
			int num = 1;
			while (0 < index - num && index + num <= N) {
				if (map[index - num] == map[index + num]) {
					if (map[index - num] == 0) {
						map[index - num] = 1;
						map[index + num] = 1;
					} else {
						map[index - num] = 0;
						map[index + num] = 0;
					}
				} else {
					break;
				}
				num++;
			}
		}
	}
}
