package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18430_무기공학 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int bu[][][] = { { { -1, 0 }, { 0, 1 } }, // ㄴ
			{ { 0, 1 }, { 1, 0 } }, // 반대 ㄱ
			{ { 1, 0 }, { 0, -1 } }, // ㄱ
			{ { 0, -1 }, { -1, 0 } } };// 반대 ㄴ

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static int dx2[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy2[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int board[][];
	static int visited[][];
	static int answer;

	static int R;
	static int C;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		visited = new int[R][C];
		answer = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int x = Integer.parseInt(st.nextToken());
				board[i][j] = x;

			}
		}

		DFS(0, 0);
		System.out.println(answer);

		br.close();
	}

	static void DFS(int index, int sum) {
		if (index == R * C) {
			return;
		} else {

			int x = index / C;
			int y = index % C;
			for (int i = 0; i < 4; i++) {
				int nx1 = x + dx[i];
				int ny1 = y + dy[i];

				int nx2 = x + dx[(i + 1) % 4];
				int ny2 = y + dy[(i + 1) % 4];
				if (0 <= nx1 && nx1 < R && 0 <= nx2 && nx2 < R //
						&& 0 <= ny1 && ny1 < C && 0 <= ny2 && ny2 < C) {
					if (visited[nx1][ny1] == 0 && visited[nx2][ny2] == 0 && visited[x][y] == 0) {
						visited[nx1][ny1] = 1;
						visited[nx2][ny2] = 1;
						visited[x][y] = 1;

						answer = Math.max(answer, sum + (board[x][y] * 2) + board[nx1][ny1] + board[nx2][ny2]);
						DFS(index + 1, sum + (board[x][y] * 2) + board[nx1][ny1] + board[nx2][ny2]);

						visited[x][y] = 0;
						visited[nx1][ny1] = 0;
						visited[nx2][ny2] = 0;
					}

				}

			}
			
			DFS(index + 1, sum );
			
		}
	}

}
