package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA4193_수영대회_결승전 {

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

	static int board[][];
	static int visited[][];
	static int N;
	static int countDown;
	static Point startPoint;
	static Point endPoint;
	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			visited = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			startPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			endPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			answer = 0;

			BFS(startPoint);
			System.out.printf("#%d %d\n", tc, answer);

		}

		br.close();
	}

	static void BFS(Point start) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(start);
		int level = 0;
		visited[start.x][start.y] = 1;
		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();

				for (int i = 0; i < 4; i++) {

					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if (0 <= nx && nx < N && 0 <= ny && ny < N) {
						if (visited[nx][ny] == 0 && board[nx][ny] != 1) {

							if (board[cur.x][cur.y] == 2 && (level % 3 != 0)) {
								queue.offer(cur);
							} else {
								visited[nx][ny] = 1;
								queue.offer(new Point(nx, ny));
								if (nx == endPoint.x && ny == endPoint.y) {
									answer = level + 1;
									return;
								}
							}
						}
					}
				}

			}
			level++;
		}

	}

}
