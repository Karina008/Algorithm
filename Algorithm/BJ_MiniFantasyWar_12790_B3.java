package algo_study_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_MiniFantasyWar_12790_B3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int hp=0, mp=0, at=0, sh=0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<2; i++) {
				hp += Integer.parseInt(st.nextToken());
				mp += Integer.parseInt(st.nextToken());
				at += Integer.parseInt(st.nextToken());
				sh += Integer.parseInt(st.nextToken());				
			}
			if(hp < 1) 
				hp =1;
			if(mp <1)
				mp =1;
			if(at <0)
				at =0;
//			int ans = hp + 5*mp + 2*at + 2*sh;
//			System.out.println(hp + " " + mp + " " + at + " " + sh);
			System.out.println(hp + 5*mp + 2*at + 2*sh);
		}
	}

}
