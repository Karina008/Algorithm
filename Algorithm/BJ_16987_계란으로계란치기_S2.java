package algo_study_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16987_계란으로계란치기_S2 {
	static int N, ans =0 ;
	static Egg[] eggs;
	static boolean[] dead;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		dead = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int hp = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			eggs[i] = new Egg(hp,weight);
		}
		
		dfs(0,0);
		System.out.println(ans);
	}
	
	public static void dfs(int index, int count) {		
		if(index== N || count == N-1) {
		// 계란 끝까지옴 or 계란 하나 남음
			ans = Math.max(ans, count);
			return;
		}
		
		// 현재 든 계란 깨진 계란
		if(eggs[index].hp <= 0 ) {
			dfs(index+1, count);
			return;
		}

		for(int i=0; i< N; i++) {			
			// 이미 깨진 계란
			if(eggs[i].hp<=0 || index == i) {
				continue;
			}
			// 깨지는 계란 개수
			int eggbreak =0;
			
			// 계란의 내구도를 각각 줄여준 후
			// dfs를 호출한 뒤에 다시 복구해준다.
			// 백트래킹
			eggs[i].hp -=  eggs[index].weight;
			eggs[index].hp -= eggs[i].weight;
					
			if(eggs[i].hp <= 0){
				eggbreak++;
			}					
			if(eggs[index].hp <= 0){
				eggbreak++;
			}
			
			dfs(index+1, count+eggbreak);

			eggs[i].hp +=  eggs[index].weight;			
			eggs[index].hp += eggs[i].weight;
		}
	}
	
	static class Egg {
		int hp;
		int weight;
		public Egg(int hp, int weight) {
			this.hp = hp;
			this.weight = weight;
		}
	}
}
