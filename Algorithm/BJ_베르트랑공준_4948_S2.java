package algo_study_07;

import java.util.Scanner;

public class BJ_베르트랑공준_4948_S2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while(true) {
			int n = sc.nextInt();
			if(n==0) 
				break;
			boolean[] arr = new boolean[n*2+1];
			arr[0]=true;
			arr[1] = true;
			for(int i=2; i<=2*n; i++) {
				for(int j=2; j*i<=2*n; j++) {
					arr[j*i]=true;
				}
			}
			
			
			int count=0;
			for(int i=n+1; i<=2*n; i++) {
				if(!arr[i]) {
					count++;
				}
			}
			System.out.println(count);
		}
	}
}
