package algo_study_05;

import java.util.Scanner;

public class SW_숫자게임_7206_D5 {
	static int num;
	static int Max = Integer.MIN_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int t=1; t<=T; t++) {
//			num = sc.next();
			num = sc.nextInt();
			int count=0;
			Max = Integer.MIN_VALUE;
			//System.out.println(num);
//			if(Integer.parseInt(num) < 10) {
			if(num < 10) {
				System.out.println("#" + t + " " + count);
			}else {
				sol(num, count);
				System.out.println("#" + t + " " + Max);
				//System.out.println(Integer.parseInt(num));
			}
			
		}
	}
	public static void sol(int num, int cnt) {
		int len = Integer.toString(num).length();
		String str="";
		if(num < 10) {
			if(Max < cnt) 
				Max=cnt;
			//System.out.println("return");
			return;
		}
		
		for(int i=0; i<len-1; i++) {
			int su1 = (int) (num/(Math.pow(10, i+1)));
			int su2 = (int) (num%(Math.pow(10, i+1)));
			
//			System.out.println(su1);
//			System.out.println(su2);
//			System.out.println(su1*su2);
			sol(su1*su2, cnt+1);
			
			int len2 = Integer.toString(su2).length();
			for(int j=0; j<len2-1; j++)  {
				int su3 = (int) (su2/(Math.pow(10, i+1)));
				int su4 = (int) (su2%(Math.pow(10, i+1)));
				
//				System.out.println(su1);
//				System.out.println(su3);
//				System.out.println(su4);
//				System.out.println(su1*su3*su4);
				sol(su1*su3*su4, cnt+1);
			}
			
			
			
		}
		
//		for(int i=1; i<len; i++) {
//			for(int j=i+1; j<len; j++) {
//				String str1 = str.substring(0, i);
//				String str2 = str.substring(i, j);
//				String str3 = str.substring(j, len);
////				System.out.println("str1 : " + str1);
////				System.out.println("str2 : " + str2);
////				System.out.println("str3 : " + str3);
//				
//				num = Integer.toString(Integer.parseInt(str1) *  Integer.parseInt(str2) *  Integer.parseInt(str3));
//				//System.out.println(num);
//				
//				if(Integer.parseInt(num)>10)
//					sol(num, cnt+1);
//				
//			}
//			String str1 = str.substring(0, i);
//			String str2 = str.substring(i, len);
//			//System.out.println("str1 : " + str1);
//			//System.out.println("str2 : " + str2);
//			
//			num = Integer.toString(Integer.parseInt(str1) *  Integer.parseInt(str2));
//			//System.out.println(num);
//			if(Integer.parseInt(num)>10)
//				sol(num, cnt+1);
//		}
//		if(Max < cnt)
//			Max = cnt;
	}
}
