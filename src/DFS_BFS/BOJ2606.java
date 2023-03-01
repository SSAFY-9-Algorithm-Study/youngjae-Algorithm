package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2606 {

	int n;
	int edge;

	int board[][];
	ArrayList<Integer> adjList[];
	int visited[];

	int warmCount=0;


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BOJ2606 Test = new BOJ2606();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Test.n = Integer.parseInt(br.readLine());
		Test.edge = Integer.parseInt(br.readLine());

		Test.board = new int[Test.n + 1][Test.n + 1];
		Test.visited = new int[Test.n + 1];

		StringTokenizer st;
		for (int i = 0; i < Test.edge; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			Test.board[s][e] = 1;
			Test.board[e][s] = 1;

		}

//		Test.adjList = new ArrayList[Test.n + 1];
//		for (int i = 0; i <= Test.n; i++) {
//			Test.adjList[i] = new ArrayList<Integer>();
//		}
//
//		for (int i = 0; i < Test.edge; i++) {
//			st = new StringTokenizer(br.readLine());
//			int s = Integer.parseInt(st.nextToken());
//			int e = Integer.parseInt(st.nextToken());
//
//			Test.adjList[s].add(e);
//			Test.adjList[e].add(s);
//
//		}
		Test.visited[1] = 1;
		Test.DFS(1);
		System.out.println(Test.warmCount);

		br.close();

	}

	void DFS(int v) {

		for (int i = 1; i <=n; i++) {
			if (board[v][i] == 1 && visited[i] == 0) {
				visited[i] = 1;
				DFS(i);
				warmCount++;

			}
		}

//		for (int vertex : adjList[v]) {
//			if (visited[vertex] == 0) {
//				visited[vertex] = 1;
//				warmCount++;
//				DFS(vertex);
//				
//			}
//		}

	}

}
