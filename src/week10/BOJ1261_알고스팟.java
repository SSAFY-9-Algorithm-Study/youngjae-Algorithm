package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1261_알고스팟 {

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int weigth;

		public Point(int x, int y, int weigth) {
			this.x = x;
			this.y = y;
			this.weigth = weigth;
		}

		@Override
		public int compareTo(Point o) {

			return this.weigth - o.weigth;
		}

	}

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int N, M; // 미로의 가로 크기 M, 세로 크기 N
	static boolean[][] board;
	static boolean[][] visited;
	static int dist[][];

	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new boolean[N][M];
		visited = new boolean[N][M];
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j) == '1';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		dijkstra(0,0);
		System.out.println(dist[N - 1][M - 1]);

		br.close();
	}

	static void dijkstra(int x, int y) {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();

		pq.add(new Point(x, y, 0));

		dist[x][y] = 0;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (0 <= nx && nx < N && 0 <= ny && ny < M) {


					if (board[nx][ny] == true) {
						if (dist[nx][ny] > dist[cur.x][cur.y] + 1) {
							dist[nx][ny] = dist[cur.x][cur.y] + 1;
							pq.offer(new Point(nx, ny, dist[nx][ny]));
						}
					} else {
						if (dist[nx][ny] > dist[cur.x][cur.y]) {
							dist[nx][ny] = dist[cur.x][cur.y];
							pq.offer(new Point(nx, ny, dist[nx][ny]));
						}
					}

				}

			}

		}

	}

}
