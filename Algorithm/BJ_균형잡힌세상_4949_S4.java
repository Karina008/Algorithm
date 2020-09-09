package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_균형잡힌세상_4949_S4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		Stack<Character> stack;
		while(true) {
			stack = new Stack<>();
			str = br.readLine();
			if(str.length()==1 && str.charAt(0) == '.')
				break;
			boolean check=true;
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i) == '.') {
					break;
				}
				if(str.charAt(i) == '(' || str.charAt(i) == '[') {
					stack.push(str.charAt(i));
				}else if(str.charAt(i) == ')') {
					if( stack.isEmpty() || stack.pop() != '(') {
						check = false;
						break;
					}
				}else if(str.charAt(i) == ']') {
					if( stack.isEmpty() || stack.pop() != '[') {
						check = false;
						break;
					}
				}
			}
			
			if(check && stack.isEmpty())
				System.out.println("yes");
			else
				System.out.println("no");
//			System.out.println(check);
			
		}
	}

}
