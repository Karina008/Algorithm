package algo_study_05;

import java.util.Scanner;

public class BJ_게리맨더링2_17779_G4 {
	static int N;
	static int[][] map, mask;
	static int[] sum;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						// 기준점 (x, y)와 경계의 길이 d1, d2를 정한다.
						// (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
						if (x + d1 + d2 <= N && 1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N) {
							mask = new int[N + 1][N + 1];
							sum = new int[6]; // 각 선거구별 인구수를 저장할 배열
							make(x,y,d1,d2);
							Area(x,y,d1,d2);
							calculation();
							
							
//							for(int a=1; a<=N; a++) {
//								for(int b=1; b<=N; b++) {
//									System.out.print(mask[a][b]);
//								}
//								System.out.println();
//							}
//							System.out.println(ans);
						}

					}
				}
			}
		}
		System.out.println(ans);
	}
	
	public static void make(int x, int y, int d1, int d2) {
		//1. (x, y), (x+1, y-1), ..., (x+d1, y-d1)
        //2. (x, y), (x+1, y+1), ..., (x+d2, y+d2)
        //3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
        //4. (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
		mask[x][y]  = 5;
        int add1=1, add2=1;	
        
//		int row = x, col = y;
//		int width = d1, height = d2;
		
        while(add1 <= d1) {
        	mask[x+add1][y-add1] = 5;
        	add1++;
        }
        while(add2 <= d2) {
        	mask[x+add2][y+add2] = 5;
        	add2++;
        }
        
        add1=1;
        add2=1;
        while(add2 <= d2) {
        	mask[x+d1+add2][y-d1+add2] = 5;
        	add2++;
        }
        while(add1 <= d1) {
        	mask[x+d2+add1][y+d2-add1] = 5;
        	add1++;
        }
        
      //경계선과 경계선의 안에 포함되어있는 5번 선거구이다.
        for(int i=1; i<=N; i++) {
        	int left=1; 
        	int right = N;
        	while(left <=N && mask[i][left] != 5)
        		left++;
        	while(right >=1 && mask[i][right] !=5 ) 
        		right--;
        	if(left != right && left-right != N+1) {//left가 오른쪽 끝에 왔으면 n+1, right가 왼쪽끝에왔으면 0
        		for(int j=left+1; j<right; j++) { // 따라서 양쪽끝에있다면 n+1 - 0 = n+1
        			mask[i][j]=5;
        		}
        	}
        }
	}

	
	public static void Area(int x, int y, int d1, int d2) {
		//1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
        //2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
        //3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
        //4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
		
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				if(mask[r][c] ==5 )
					continue;
				
				if(r < x+d1 && c <=y)
					mask[r][c] = 1;
				else if( r<= x+d2 && y<c)
					mask[r][c] = 2;
				else if( x+d1 <= r && c < y-d1+d2)
					mask[r][c] = 3;
				else if( x+d2 < r &&  y-d1+d2 <=c)
					mask[r][c] = 4;
				else
					mask[r][c] = 5;
			}
		}
	}
	
	public static void calculation() {
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				sum[mask[r][c]] += map[r][c];
			}
		}
		
		max = min = sum[1];
		for(int i=2; i<=5; i++) {
			if( max < sum[i])
				max = sum[i];
			if( min > sum[i])
				min = sum[i];			
		}
		
		ans = Math.min(ans, max-min);
	}
}
