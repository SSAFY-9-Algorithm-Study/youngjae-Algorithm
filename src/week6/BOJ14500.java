package week6;

import java.io.*;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ14500 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int row;
	static int col;
	static int board[][];
	static int visited[][];
	static int answer;
	static int OBoard[][][] = { { { 0, 0 }, { 0, 1 }, { 0, 2 }, { -1, 1 } } // ㅗ
			, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } } // ㅜ
			, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } } // ㅏ
			, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, -1 } } }; // ㅓ

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		answer = Integer.MIN_VALUE;

		board = new int[row][col];
		visited = new int[row][col];
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				visited[i][j] = 1;
				DFS(new Point(i, j), board[i][j], 1);
				visited[i][j] = 0;
			}
		}

		for (int o = 0; o < 4; o++) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {

					int sum=0;
					if(0<= OBoard[o][0][0]+i && OBoard[o][0][0]+i< row &&  0<=OBoard[o][0][1]+j && OBoard[o][0][1]+j<col) {
						if(0<= OBoard[o][1][0]+i && OBoard[o][1][0]+i< row &&  0<=OBoard[o][1][1]+j && OBoard[o][1][1]+j<col) {
							if(0<= OBoard[o][2][0]+i && OBoard[o][2][0]+i< row &&  0<=OBoard[o][2][1]+j && OBoard[o][2][1]+j<col) {
								if(0<= OBoard[o][3][0]+i && OBoard[o][3][0]+i< row &&  0<=OBoard[o][3][1]+j && OBoard[o][3][1]+j<col) {
									
									sum= board[OBoard[o][0][0]+i][OBoard[o][0][1]+j]
											+board[OBoard[o][1][0]+i][OBoard[o][1][1]+j]
													+board[OBoard[o][2][0]+i][OBoard[o][2][1]+j]
															+board[OBoard[o][3][0]+i][OBoard[o][3][1]+j];
									
									
									answer = Math.max(answer, sum);
								}
							}
						}
					}
				}
			}
		}

		System.out.println(answer);

		br.close();

	}

	static void DFS(Point start, int sum, int cnt) {
		if (cnt == 4) {
			answer = Math.max(answer, sum);
			return;
		} else {
			for (int i = 0; i < 4; i++) {
				int nx = start.x + dx[i];
				int ny = start.y + dy[i];

				if (0 <= nx && nx < row && 0 <= ny && ny < col) {
					if (visited[nx][ny] == 0) {
						visited[nx][ny] = 1;
						DFS(new Point(nx, ny), sum + board[nx][ny], cnt + 1);
						visited[nx][ny] = 0;
					}
				}

			}
		}
	}

}