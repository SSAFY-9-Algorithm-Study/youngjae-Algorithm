package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15649_N과M_1 {

	static int N;
	static int R;
	static int arr[];

	static int visited[];
	static int permutation[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		permutation = new int[R];
		visited = new int[N];

		arr = new int[N];
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}
		DFS(0);
		br.close();
	}

	static void DFS(int level) {
		if (level == R) {
			for(int i:permutation) {
				System.out.print(i+" ");
			}
			System.out.println();
//			System.out.println(Arrays.toString(permutation));
		} else {
			for (int i = 0; i < N; i++) {
				if (visited[i] == 0) {
					visited[i] = 1;
					permutation[level] = arr[i];
					DFS(level + 1);
					visited[i] = 0;
				}
			}
		}
	}

}
