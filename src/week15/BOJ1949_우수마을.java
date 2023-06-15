package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1949_우수마을 {

	static int N;
	static int weight[];
	static List<Integer> list[];
	static int dp[][];
	static int visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		visited = new int[N];
		dp = new int[N][2];
		weight = new int[N];
		list = new ArrayList[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;

			list[start].add(end);
			list[end].add(start);
		}

		DFS(0);

		System.out.println(Math.max(dp[0][0], dp[0][1]));

		br.close();
	}

	static void DFS(int vertex) {

		dp[vertex][0] = 0; // 현재 노드가 '우수 마을'로 선정되지 못한 마을
		dp[vertex][1] = weight[vertex]; // 현재노드가 '우수 마을'로 선정
		visited[vertex] = 1; // 방문처리

		for (Integer next : list[vertex]) {
			if (visited[next] == 0) { // 방문한적이 없으면
				DFS(next);

				// 현재 노드가 '우수 마을'아니면, 자식은 '우수'이거나 아니거나 둘중 하나.
				dp[vertex][0] = dp[vertex][0] + Math.max(dp[next][0], dp[next][1]);
				// 현재 노드가 '우수 마을'이면, 자식은 '우수 마을'이 아니다.
				dp[vertex][1] = dp[vertex][1] + dp[next][0];

			}
		}

	}

}
