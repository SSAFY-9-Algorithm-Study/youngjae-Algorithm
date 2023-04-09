package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.PrimitiveIterator.OfDouble;

public class BOJ1182_부분수열의_합 {

	static int N;
	static int S;
	static int arr[];
	static int visited[];
	static int answer = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		visited = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		DFS(0, 0);
		System.out.println(answer);
		br.close();
	}

	static void DFS(int level, int sum) {
		if (level == N) {
			if (sum == S) {
				int check=0;
				for(int i=0;i<N;i++) {
					check+=visited[i];
				}
				if(check>0)
					answer++;
			}
		} else {
			visited[level] = 1;
			DFS(level + 1, sum + arr[level]);

			visited[level] = 0;
			DFS(level + 1, sum);
		}
	}

}
