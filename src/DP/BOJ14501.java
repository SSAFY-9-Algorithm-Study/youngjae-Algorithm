package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14501 {

	static class Consulting implements Comparable<Consulting> {
		int start;
		int end;
		int price;

		public Consulting(int start, int end, int price) {
			this.start = start; // 시작일
			this.end = end; // 상담 끝나는날
			this.price = price; // 가격
		}

		@Override
		public int compareTo(Consulting o) {
			return this.end - o.end;
		}

	}

	static int N;
	static Consulting consultingArr[]; // 상담 배열
	static int DP[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		consultingArr = new Consulting[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken()) - 1;
			int price = Integer.parseInt(st.nextToken());
			consultingArr[i] = new Consulting(i + 1, i + 1 + time, price);
		}

		Arrays.sort(consultingArr);
		DP = new int[consultingArr[N - 1].end + 1];

		for (int i = 0; i < N; i++) {
			for (int j = consultingArr[N - 1].end; j >= consultingArr[i].end; j--) {
				int time = consultingArr[i].end - consultingArr[i].start;
				DP[j] = Math.max(DP[j], DP[j - time] + consultingArr[i].price);
			}
		}

		System.out.println(Arrays.toString(DP));
		System.out.println(DP[N]);
		br.close();
	}

}
