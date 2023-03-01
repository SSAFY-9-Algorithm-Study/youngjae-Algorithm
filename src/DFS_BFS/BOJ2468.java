package DFS_BFS;

import java.io.*;
import java.io.IOException;
import java.util.StringTokenizer;

//class Point {
//	int x;
//	int y;
//
//	public Point(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//}

public class BOJ2468 {

	public static int[][] board;
	public static int[][] visited;
	public static int N;
	public static int dx[] = { -1, 0, 1, 0 };
	public static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;

		for (int d = 0; d <= 100; d++) {
			int count = 0;
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {

				for (int j = 0; j < N; j++) {
					if (visited[i][j] == 0 && board[i][j] > d) {
						count++;
						visited[i][j] = 1;
						DFS(new Point(i, j), d);
					}
				}
			}

			answer = Math.max(answer, count);
		}

		System.out.println(answer);
		br.close();
	}

	public static void DFS(Point start, int depth) {

		for (int i = 0; i < 4; i++) {

			int nextX = start.x + dx[i];
			int nextY = start.y + dy[i];

			if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N && visited[nextX][nextY] == 0
					&& board[nextX][nextY] > depth) {
				visited[nextX][nextY] = 1;
				DFS(new Point(nextX, nextY), depth);
			}
		}
	}

}
