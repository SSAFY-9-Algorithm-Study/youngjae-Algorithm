package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549_숨바꼭질3 {

	static class Point implements Comparable<Point> {
		int x;
		int level;

		public Point(int x, int level) {
			this.x = x;
			this.level = level;
		}

		@Override
		public int compareTo(Point o) {
			return this.level - o.level;
		}

	}

	static int start;
	static int end;
	static int dist[];
	static int answer;
	static int count2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dist = new int[200001];

		if (end <= start) {
			answer = start - end;
		} else {
			answer = BFS(start);
		}

		System.out.println(answer);
		br.close();
	}

	static int BFS(int start) {
		PriorityQueue<Point> queue = new PriorityQueue();
		queue.add(new Point(start, 0));

		for (int i = 0; i < 200001; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		while (!queue.isEmpty()) {

			Point cur = queue.poll();

			if (cur.x + 1 <= 100000 && dist[cur.x + 1] > cur.level + 1) {
				dist[cur.x + 1] = cur.level + 1;
				queue.add(new Point(cur.x + 1, dist[cur.x + 1]));
			}

			if (cur.x - 1 >= 0 && dist[cur.x - 1] > cur.level + 1) {
				dist[cur.x - 1] = cur.level + 1;
				queue.add(new Point(cur.x - 1, dist[cur.x - 1]));
			}

			if (cur.x * 2 <= 200000 && dist[cur.x * 2] > cur.level) {
				dist[cur.x * 2] = cur.level;
				queue.add(new Point(cur.x * 2, dist[cur.x * 2]));
			}

		}
		return dist[end];
	}

}
