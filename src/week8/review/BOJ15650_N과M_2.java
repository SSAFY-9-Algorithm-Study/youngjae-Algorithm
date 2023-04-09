package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15650_N과M_2 {

	static int N;
	static int R;
	static int arr[];

	static int visited[];
	static int combi[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		combi = new int[R];
		visited = new int[N];

		arr = new int[N];
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}
		DFS(0, 0);
		br.close();
	}

	static void DFS(int level, int start) {
		if (level == R) {
			for (int i = 0; i < R; i++) {
				System.out.print(combi[i]+" ");
			}
			System.out.println();
//			System.out.println(Arrays.toString(combi));
		} else {

			for (int i = start; i < N; i++) {
				if (visited[i] == 0) {
					visited[i] = 1;
					combi[level] = arr[i];
					DFS(level + 1, i + 1);
					visited[i] = 0;
				}
			}

		}
	}

}
