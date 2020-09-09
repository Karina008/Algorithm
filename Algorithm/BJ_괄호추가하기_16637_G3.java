package algo_study_06;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_괄호추가하기_16637_G3 {
	static int N, Max = Integer.MIN_VALUE;
	static int[] su, arr;
	static char[] sign;
	static int ans = 0;
	static Deque<Integer> qnum = new LinkedList<>();
	static Deque<Character> qsign = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		String str = sc.next();

		su = new int[N / 2 + 1];
		arr = new int[N / 2];
		sign = new char[N / 2];
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				su[i / 2] = str.charAt(i) - '0';
			} else {
				sign[i / 2] = str.charAt(i);
			}
		}
		comb(0);
		System.out.println(Max);
	}

	public static void comb(int index) {
		if (index == N / 2) {
			qnum.add(su[0]);
			for (int i = 0; i < N / 2; i++) {
				if (arr[i] == 0) { // 나중 계산
					qnum.add(su[i + 1]);
					qsign.add(sign[i]);
				} else { // 괄호 안 계산
					int number = qnum.pollLast();
					switch (sign[i]) {
					case '+':
						number = number + su[i + 1];
						break;
					case '-':
						number = number - su[i + 1];
						break;
					case '*':
						number = number * su[i + 1];
						break;
					case '/':
						number = number / su[i + 1];
						break;
					}
					qnum.add(number);
				}
			}
			int ans = qnum.poll();

			while (!qsign.isEmpty()) {
				int number2 = qnum.poll();
				char s = qsign.poll();
				switch (s) {
				case '+':
					ans = ans + number2;
					break;
				case '-':
					ans = ans - number2;
					break;
				case '*':
					ans = ans * number2;
					break;
				case '/':
					ans = ans / number2;
					break;
				}
			}
			Max = Math.max(ans, Max);
			return;
		}

		arr[index] = 0;
		comb(index + 1);
		if (index > 0 && arr[index - 1] != 1) {
			arr[index] = 1;
			comb(index + 1);
		}
	}
}
