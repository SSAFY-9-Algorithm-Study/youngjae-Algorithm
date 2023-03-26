package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14501_퇴사 {

	static class Consulting {
		int time;
		int price;

		public Consulting(int time, int price) {
			this.time = time;
			this.price = price;
		}

	}

	static int N; // 상담 일수
	static Consulting arr[]; // 상담 배열
	static int DP[]; // DP테이블, x일부터 상담 시작

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new Consulting[N + 1];
		DP = new int[N + 2];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			arr[i] = new Consulting(time, price);
		}

		for (int i = N; i >= 1; i--) {
			int max = 0;
			for (int j = i + 1; j <= N + 1; j++) {
				max = Math.max(max, DP[j]);
			}
			if (i + arr[i].time <= N + 1)
				DP[i] = Math.max(max, DP[i + arr[i].time] + arr[i].price);
			else
				DP[i] = max;

		}
		System.out.println(DP[1]);

		br.close();

	}
}