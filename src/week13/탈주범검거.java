package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class 탈주범검거 {

	static int dx[][] = { { 0, 0, 0, 0 } //
			, { -1, 0, 1, 0 }// 상하좌우
			, { -1, 1 }// 상하
			, { 0, 0, }// 좌우
			, { -1, 0 }// 상우
			, { 1, 0 }// 하우
			, { 1, 0 }// 하좌
			, { -1, 0 }// 상좌
	};
	static int dy[][] = { { 0, 0, 0, 0 }//
			, { 0, 1, 0, -1 }// 상하좌우
			, { 0, 0 }// 상하
			, { -1, 1, }// 좌우
			, { 0, 1 }// 상우
			, { 0, 1 }// 하우
			, { 0, -1 }// 하좌
			, { 0, -1 }// 상좌
	};

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N;
	static int M;
	static int R;
	static int C;
	static int L;
	static int cnt;

	static Set<String> checkSet;

	static int board[][];
	static int visited[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			board = new int[N][M];
			visited = new int[N][M];
			checkSet = new HashSet<String>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());

				}
			}
			checkSet.add(R + "," + C);
			visited[R][C] = 1;
			DFS(new Point(R, C), 1);
			cnt = 0;

			System.out.printf("#%d %d\n", tc, checkSet.size());
		}

		br.close();
	}

	static boolean canGo(int nx, int ny, int curX, int curY) {

		int nextPipe = board[nx][ny];

		for (int j = 0; j < dx[nextPipe].length; j++) {
			if (curX == -dx[nextPipe][j] && curY == -dy[nextPipe][j]) {
				return true;
			}
		}

		return false;
	}

	static void DFS(Point start, int level) {
		if (level == L) {
			return;
		} else {
			int curPipe = board[start.x][start.y];
			for (int i = 0; i < dx[curPipe].length; i++) {
				int nx = start.x + dx[curPipe][i];
				int ny = start.y + dy[curPipe][i];

				if (0 <= nx && nx < N && 0 <= ny && ny < M && board[nx][ny] != 0) {
					if (visited[nx][ny] == 0) {
						visited[nx][ny] = 1;
						if (canGo(nx, ny, dx[curPipe][i], dy[curPipe][i])) {
							checkSet.add(nx + "," + ny);
							DFS(new Point(nx, ny), level + 1);
						}
						visited[nx][ny] = 0;
					}

				}

			}
		}
	}

}
