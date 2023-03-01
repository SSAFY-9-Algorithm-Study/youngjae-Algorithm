package backtracking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ15649 {

	public static int n;
	public static int r;
	public static int visited[];
	public static int arr[];
	public static int permutation[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		visited = new int[n + 1];
		arr = new int[n + 1];
		permutation = new int[r];

		for (int i = 1; i <= n; i++) {
			arr[i] = i;
		}

		DFS(0);

		br.close();
	}

	public static void DFS(int level) {
		if (level == r) {
			for (int i = 0; i < r; i++) {

				System.out.print(permutation[i] + " ");

			}
			System.out.println();
		} else {

			for (int i = 1; i <= n; i++) {
				if (visited[i] == 0) {
					visited[i]=1;
					permutation[level] = arr[i];
					DFS(level + 1);
					visited[i]=0;
				}

			}
		}

	}


}
