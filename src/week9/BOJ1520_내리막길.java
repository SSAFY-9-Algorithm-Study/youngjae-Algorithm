package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1520_내리막길 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static int row;
	static int col;

	static int board[][];
	static int DP[][]; // 경로의 개수저장
	// 끝지점 까지 가는 경로의 수
	//이미 탐색해서 경로의 개수가 파악된 지점을 또 탐색하게 되면, 저장된 경로의 개수를 반환하면 됩니다.

	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		board = new int[row][col];
		DP = new int[row][col];
		answer = 0;

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {
				DP[i][j] = -1;
			}
		}

		DFS(0, 0);

//		
//		for(int i=0;i<row;i++) {
//			System.out.println(Arrays.toString(DP[i]));
//		}

		System.out.println(DP[0][0]);
		br.close();
	}

	static int DFS(int x, int y) {

		if (DP[x][y] != -1) {
			// (메모이제이션)
			return DP[x][y];
		}

		if (x == row - 1 && y == col - 1) {
			//도달했을때 추가탐색 필요 없다
			return 1;
		} else {
			DP[x][y] = 0;
			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + x;
				int ny = dy[i] + y;

				if (0 <= nx && nx < row && 0 <= ny && ny < col) {
					if (board[nx][ny] < board[x][y]) {

						DP[x][y] += DFS(nx, ny);

					}
				}

			}

		}
		return DP[x][y];

	}

}
