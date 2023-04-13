package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20951_유아와_곰두리차 {

	static int N;
	static int M;

	static ArrayList<Integer> graph[];
	static long DP[][]; // 간선마다 도착하는 횟수 저장하는 DP테이블

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		DP = new long[8][N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			graph[start].add(end);
			graph[end].add(start);
		}

		// 1번 만에 각 정점에 도착하는 경우의 수 초기화 저장
		for (int j = 1; j <= N; j++) {
			DP[1][j] = (long) (graph[j].size()% (Math.pow(10, 9) + 7));
		}
		for (int i = 2; i <= 7; i++) {

			for (int j = 1; j <= N; j++) {
				long sum = 0;
				for (int x : graph[j]) {
					// 직전의 도착하는 경우의 수를 더함
					sum = (long) ((DP[i - 1][x] +sum) % (Math.pow(10, 9) + 7));
				}

				DP[i][j] = (long) (sum % (Math.pow(10, 9) + 7));
			}

		}

		long sum = 0;
		for (int i = 0; i <= N; i++) {
			sum = (long) ((DP[7][i] +sum) % (Math.pow(10, 9) + 7));
		}
		System.out.println(sum);

//		for (int i = 0; i < 8; i++) {
//			System.out.println(Arrays.toString(DP[i]));
//		}

		br.close();
	}

}
