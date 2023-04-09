package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15652_N과M_4 {

	static int N;
	static int R;
	static int arr[];

	static int combi[];
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N];
		combi = new int[R];

		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		DFS(0, 0);
		System.out.println(sb.toString());
		br.close();
	}

	static void DFS(int level, int start) {

		if (level == R) {
			for (int i = 0; i < R; i++) {
				sb.append(combi[i] + " ");
			}
			sb.append("\n");
//			System.out.println(Arrays.toString(combi));
		} else {
			for (int i = start; i < N; i++) {
				combi[level] = arr[i];
				DFS(level + 1, i);
			}
		}

	}
}
