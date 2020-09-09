package algo_study_05;

import java.util.Arrays;
import java.util.Scanner;

public class PRO_소수찾기_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println(solution(str));

	}

	public static int solution(String numbers) {
		int answer = 0;
		int[] num = new int[10];
		for (int i = 0; i < numbers.length(); i++) {
			num[numbers.charAt(i) - '0']++;
		}
		//System.out.println(Arrays.toString(num));
		for (int i = 2; i <= Math.pow(10, numbers.length()); i++) {
			int count = 0;
			int[] copy_map = Arrays.copyOf(num, 10);
			if (check(i, copy_map)) {
				//System.out.println(i);
				for (int j = 2; j < i; j++) {
					if (count > 1)
						break;
					if (i % j == 0)
						count++;

				}
				if (count == 0) {
					answer++;
					//System.out.println(i);
				}
			}
		}

		return answer;
	}

	public static boolean check(int number, int[] num) {
		while (true) {
			//System.out.println(number);
			if (number < 10) {
				//System.out.println(Arrays.toString(num));
				if (num[number]>0)
					return true;
				else
					return false;
			}
			int su = number % 10;
			number = number / 10;
			if (num[su] <1) {
				break;
			}else
				num[su]--;
		}

		return false;
	}
}
