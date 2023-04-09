package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14889_스타트와_링크 {

	static int N;
	static int board[][];

	static int visited[];
	static int combi[];
	static int R;
	static int arr[];
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		R = N / 2;
		board = new int[N][N];
		arr = new int[N];
		visited = new int[N];
		combi = new int[R];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			arr[i] = i;
		}

		DFS(0, 0);
		System.out.println(answer);
		br.close();
	}

	static void DFS(int level, int start) {
		if (level == R) {
//			System.out.println(Arrays.toString(visited));

			int visitedLink[] = new int[N];
			for (int i = 0; i < N; i++) {
				visitedLink[i] = (visited[i] == 0) ? 1 : 0;
			}
//			System.out.println(Arrays.toString(visitedLink));

			int sumStart = 0;
			int sumLink = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j) {
						if (visited[i] == 1 && visited[j] == 1) {
							sumStart += board[i][j];
						} else if (visitedLink[i] == 1 && visitedLink[j] == 1) {
							sumLink += board[i][j];
						}
					}
				}
			}
//			System.out.println(sumStart);
//			System.out.println(sumLink);
			answer = Math.min(answer, Math.abs(sumLink - sumStart));

		} else {
			for (int i = start; i < N; i++) {
				if (visited[i] == 0) {
					visited[i] = 1;

					DFS(level + 1, i);
					visited[i] = 0;

				}
			}
		}
	}

}
