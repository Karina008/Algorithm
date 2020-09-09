package algo_study_05;

import java.util.Scanner;

public class PRO_종이접기_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int ans[] = solution(N);
	}

	public static int[] solution(int n) {
		StringBuilder sb = new StringBuilder("0");
		
		while((--n) > 0) {
			String str = sb.toString();
			sb.append(0);
			
			for(int i=str.length()-1; i>=0; i--) {
				if(str.charAt(i) == '1')
					sb.append(0);
				else
					sb.append(1);
			}
		}
		
		int[] answer = new int[sb.length()];
		
		for(int i=0; i< answer.length; i++) {
			answer[i] = sb.charAt(i) - '0';
		}
        return answer;
    }
}
