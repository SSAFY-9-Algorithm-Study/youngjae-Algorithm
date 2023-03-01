package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {

	public static int N;
	public static int M;
	public static int V;
	public static int adjArray[][];
	public static int vistited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		adjArray = new int[N + 1][N + 1];
		vistited = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			adjArray[s][e] = 1;
			adjArray[e][s] = 1;

		}

		vistited[V] = 1;
		DFS(V);
		System.out.println();
		
		Arrays.fill(vistited, 0);
		vistited[V] = 1;
		BFS(V);
		
		br.close();

	}

	public static void DFS(int V) {

		System.out.print(V + " ");

		for (int i = 1; i <= N; i++) {
			if (vistited[i] == 0 && adjArray[V][i] == 1) {
				vistited[i] = 1;
				DFS(i);
			}
		}

	}

	public static void BFS(int V) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(V);
		int level = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 1; i <= size; i++) {
				int curV = q.poll();
				
				System.out.print(curV+" ");
				
				for (int nextV = 1; nextV <= N; nextV++) {
					if(vistited[nextV]==0&&adjArray[curV][nextV]==1) {
						q.offer(nextV);
						vistited[nextV]=1;

					}
				}

			}
			level++;

		}

	}
	

}
