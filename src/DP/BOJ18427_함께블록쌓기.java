package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18427_함께블록쌓기 {

	static int N; // 학생 수
	static int M; // 회대 가질수 있는 블록수
	static int H; // 탑의 높이
	static int top[][];
	static int DP[];// DP테이블, 높이에 따른 경우의 수들을 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		top = new int[N][];
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			int size = str.length;
			top[i] = new int[size];
			for (int j = 0; j < size; j++) {
				top[i][j] = Integer.parseInt(str[j]);
			}
		}

		DP = new int[H + 1];
		DP[0]=1;               
		for (int i = 0; i < top[0].length; i++) { // DP값 초기화
			DP[top[0][i]]++;
		}

		for (int i = 1; i < N; i++) {
			int tmpDP[]=new int[H + 1];
			for (int j = 0; j < top[i].length; j++) {
				for(int k=0;k<=H;k++) {
					if(k+top[i][j]<=H)
						tmpDP[k+top[i][j]]=(DP[k]+tmpDP[k+top[i][j]])% 10007;	
				}


			}
			
			for (int j = 0; j <= H; j++) {
				DP[j]+=tmpDP[j];
			}

		}

		System.out.println(DP[H]%10007);
		br.close();
	}

}
