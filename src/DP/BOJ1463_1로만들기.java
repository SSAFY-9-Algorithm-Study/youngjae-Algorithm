package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1463_1로만들기 {

	static int N; // 입력값
	static int DP[];// DP테이블

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		DP = new int[N + 1];

		// DP테이블을 초기화
		for (int i = 1; i <= N; i++) {
			DP[i] = i - 1;
		}

		boolean flag=true;
		while(flag) { //변화가 없을때 까지 반복
			flag=false;
			// X가 3으로 나누어 떨어지면, 3으로 나눈다.
			for (int i = 3; i <= N; i = i + 3) {
				if(DP[i]>(DP[i / 3] + 1)) {
					DP[i]=DP[i / 3] + 1;
					flag=true;
				}
			}
			// X가 2로 나누어 떨어지면, 2로 나눈다.
			for (int i = 2; i <= N; i = i + 2) {
				if(DP[i]>(DP[i / 2] + 1)) {
					DP[i]=DP[i / 2] + 1;
					flag=true;
				}
			}
			// 1을 뺀다.
			for (int i = 1; i <= N; i++) {
				if(DP[i]>(DP[i - 1] + 1)) {
					DP[i]=DP[i - 1] + 1;
					flag=true;
				}
			}
		}


		System.out.println(DP[N]);

		br.close();
	}

}
