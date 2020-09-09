package algo_study_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Pro_가장먼노드_코딩테스트연습 {

   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());

      int array[][] = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
      int result = solution(6, array);
      System.out.println(result);

   }

   public static int solution(int n,int[][] edge)  {
      
      int[][] map = new int[n+1][n+1];
      boolean[] visit = new boolean[n+1];
      int answer=0;

      
      ArrayList<Integer>[] list = new ArrayList[n+1];
      for(int i=0; i<list.length; i++) {
         list[i] = new ArrayList<>();
      }
      
        int len = edge.length;
//        System.out.println(len);
        for(int i=0; i<len; i++) {
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);            
        }
        
        
        // for(int i=0; i<len; i++) {
        //    System.out.println(Arrays.toString(map[i]));
        // }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] =  true;
//        int count=0;
        while(!q.isEmpty()) {
//           count++;
           int len_q = q.size();
           for(int i=0; i<len_q; i++) {
              int cur = q.poll();
              for(int j=0; j<list[cur].size(); j++) {
                 int value = list[cur].get(j);
                 if(!visit[value]) {
                         System.out.println(cur + " " + value);
                        visit[value] = true;                       
                    q.add(value);
                 }
              }
           }       
             System.out.println();
             answer = len_q;
//            int len = 
        }
        // System.out.println(answer);
        // answer = count;
        return answer;
   }
}