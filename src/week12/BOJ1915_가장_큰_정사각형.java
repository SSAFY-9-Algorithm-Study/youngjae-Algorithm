package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1915_가장_큰_정사각형 {

	static int N;
	static int M;
	static int board[][];
	static int DP[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board=new int[N+1][M+1];
		DP=new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			String input=br.readLine();
			for(int j=0;j<M;j++) {
				board[i][j+1]=input.charAt(j)-'0';
			}
			
		}  

		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(board[i][j]==1) {
					DP[i][j]=Math.min(DP[i-1][j], DP[i][j-1]);
					 DP[i][j]=Math.min(DP[i][j], DP[i-1][j-1])+1; 
				}
				else {
					DP[i][j]=0;
				}
				 
			}
		}
		
		int answer=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				answer=Math.max(DP[i][j], answer);
				 
			}
		}
		
		System.out.println(answer*answer);
		
		br.close();
	}

}
