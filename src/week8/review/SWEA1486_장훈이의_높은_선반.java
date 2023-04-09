package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1486_장훈이의_높은_선반 {

	static int N;
	static int B;
	static int combi[];
	static int vistied[];
	static int arr[];

	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			answer = Integer.MAX_VALUE;
			arr = new int[N];
			vistied = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			DFS(0, 0);
			System.out.printf("#%d %d\n", tc, answer - B);
		}

		br.close();
	}

	static void DFS(int level, int sum) {
		if (answer < sum)
			return;
		if (level == N) {
			if (B <= sum && B < answer) {
				answer = Math.min(answer, sum);
			}
		} else {
			vistied[level] = 1;
			DFS(level + 1, sum + arr[level]);

			vistied[level] = 0;
			DFS(level + 1, sum);
		}
	}

}
