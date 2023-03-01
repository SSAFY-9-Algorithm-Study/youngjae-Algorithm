package DFS_BFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*

6
5 2 3 4 1 2
2 2 4
4 1 3 6 5
2 4 2
2 1 3
1 2
1 2

 */

public class BOJ17471 {

	public static int combi[];
	public static int num;
	public static int resionArr[];
	public static int adj[][];
	public static int visitedResion[];
	public static int total;
	public static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		num = Integer.parseInt(br.readLine());
		resionArr = new int[num + 1];
		total = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= num; i++) {
			resionArr[i] = Integer.parseInt(st.nextToken());
			total += resionArr[i];
		}

		adj = new int[num + 1][num + 1];
		for (int i = 1; i <= num; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int resion = Integer.parseInt(st.nextToken());
				adj[i][resion] = 1;
				adj[resion][i] = 1;
			}
		}

		combi = new int[num + 1];
		DFScombi(0, 0);
		if (answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);

		br.close();
	}

	public static boolean BFSConnectionCheck(int[] vistied, int count) {
		if (count == 0 || count == num) {
			return false;
		}

		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= num; i++) {
			if (vistied[i] == 1) {
				vistied[i] = 0;
				q.offer(i);
				count--;
				break;
			}
		}

		while (!q.isEmpty()) {

			int size = q.size();
			for (int s = 0; s < size; s++) {
				int cur = q.poll();
				for (int i = 1; i <= num; i++) {
					if (vistied[i] == 1 && adj[cur][i] == 1) {
						vistied[i] = 0;
						count--;
						q.offer(i);
					}
				}
			}

		}

		if (count == 0) {
			return true;
		} else {
			return false;
		}

	}

	public static void DFScombi(int levle, int sum) {
		if (levle == num) {

			int count = 0;

			int[] vistied = new int[num + 1];
			int[] nonVistied = new int[num + 1];

			for (int i = 1; i <= num; i++) {
				if (combi[i] == 1) {
					vistied[i] = 1;
					count++;
				} else {
					nonVistied[i] = 1;
				}
			}

			if (BFSConnectionCheck(vistied, count) && BFSConnectionCheck(nonVistied, num - count)) {
//				System.out.println(Arrays.toString(combi));
				answer = Math.min(answer, Math.abs(total - sum - sum));
			}

			return;
		} else {
			combi[levle + 1] = 1;
			DFScombi(levle + 1, sum + resionArr[levle + 1]);

			combi[levle + 1] = 0;
			DFScombi(levle + 1, sum);
		}
	}


}
