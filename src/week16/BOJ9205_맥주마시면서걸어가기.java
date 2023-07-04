package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205_맥주마시면서걸어가기 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static Point arr[];
	static int visited[];
	static int N;
	static Point start;
	static boolean answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {

			N = Integer.parseInt(br.readLine());
			visited = new int[N + 1];

			arr = new Point[N + 1];
			answer = false;

			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			start = new Point(x, y);

			for (int i = 0; i < N + 1; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				arr[i] = new Point(x, y);
			}

			BFS();

			if (answer) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}

		}

		br.close();

	}

	static void BFS() {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(start);

		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();
				for (int i = 0; i < N + 1; i++) {
					if (visited[i] == 0 && getDist(cur, arr[i]) <= 1000) {
						visited[i] = 1;
						int dist = getDist(cur, arr[i]);

						if (i == N) {
							answer = true;
							return;
						}

						queue.add(arr[i]);

					}
				}
			}

		}
	}

	static int getDist(Point a, Point b) {
		return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
	}

}
