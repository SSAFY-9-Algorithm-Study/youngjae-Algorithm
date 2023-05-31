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
		dist = new int[200001]; // 동생은 점 K(0 ≤ K ≤ 100,000) 있을수 있다.
		// 2배로 많이 멀리 보냈다가 점차 작아지는게 더 빨리 찾을 수 있어서, 200001로 잡음

		if (end <= start) { // 동생지점이 수빈이 지점보다 낮은 경우 -1로만 이동
			answer = start - end;
		} else {
			answer = BFS(start);
		}

		System.out.println(answer);
		br.close();
	}

	// 다익스트라
	static int BFS(int start) {
		PriorityQueue<Point> queue = new PriorityQueue();
		queue.add(new Point(start, 0));

		for (int i = 0; i < 200001; i++) { // 거리 최대로 초기화
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		while (!queue.isEmpty()) {

			Point cur = queue.poll();

			// 더 짧은 거리인지 확인
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
