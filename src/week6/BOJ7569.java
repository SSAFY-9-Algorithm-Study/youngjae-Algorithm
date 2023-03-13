package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ7569 {

	static class Position {
		int x;
		int y;
		int z;

		public Position(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}

	static int board[][][];
	static int dis[][][];
	static int dx[] = { 1, 0, -1, 0, 0, 0 };
	static int dy[] = { 0, 1, 0, -1, 0, 0 };
	static int dz[] = { 0, 0, 0, 0, -1, 1 };
	static int n;
	static int m;
	static int h;
	static Queue<Position> queue = new LinkedList<>();
	static boolean flag = true;
	static int answer = -1;

	public static void main(String[] args) throws IOException {
		BOJ7569 T = new BOJ7569();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		board = new int[h][n][m];
		dis = new int[h][n][m];
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					board[k][i][j] = Integer.parseInt(st.nextToken());
					if (board[k][i][j] == 1) {
						queue.add(new Position(i, j, k));
					}
				}
			}
		}

		T.BFS();
		isZero();
		if (flag == true) {
			for (int k = 0; k < h; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						answer = Math.max(dis[k][i][j], answer);
					}
				}
			}

		}
		System.out.println(answer);

		br.close();

	}

	private static void isZero() {
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (board[k][i][j] == 0) {
						flag = false;
						return;
					}
				}
			}
		}

	}

	public void BFS() {
		while (!queue.isEmpty()) {
			Position p = queue.poll();
			for (int i = 0; i < 6; i++) {
				int nx = dx[i] + p.x;
				int ny = dy[i] + p.y;
				int nz = dz[i] + p.z;

				if (nx >= 0 && nx < n && ny >= 0 && ny < m && nz >= 0 && nz < h && board[nz][nx][ny] == 0) {
					board[nz][nx][ny] = 1;
					dis[nz][nx][ny] = dis[p.z][p.x][p.y] + 1;
					queue.add(new Position(nx, ny, nz));
				}
			}
		}

	}

}
