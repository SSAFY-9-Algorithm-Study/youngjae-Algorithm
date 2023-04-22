package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1277_발전소 {

	static class Edge implements Comparable<Edge> {
		int vertex;
		double weight;

		public Edge(int vertex, double weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {

			return Double.compare(this.weight, o.weight);
		}

	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N, W; // 발전소 수, 현재 전선수
	static double M; // 전선 제한길이
	static ArrayList<Edge> graph[];
	static double distance[];
	static Point arr[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		M = Double.parseDouble(br.readLine());

		arr = new Point[N + 1];
		distance = new double[N + 1];
		graph = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Point(x, y);

			distance[i] = Double.MAX_VALUE;
			graph[i] = new ArrayList<Edge>();
		}

		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double dist = getDist(i, j);
				if (dist <= M) {
					graph[i].add(new Edge(j, dist));
					graph[j].add(new Edge(i, dist));
				}
			}
		}

		// 이미 연결된 지점은 가중치 0으로 설정
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start].add(new Edge(end, 0));
			graph[end].add(new Edge(start, 0));

		}
		Dijkstra(1);
//		System.out.println(Arrays.toString(distance));
		System.out.println((int)Math.floor(distance[N]*1000));
		

		br.close();
	}

	static double getDist(int start, int end) {
		Point startPoint = arr[start];
		Point endPoint = arr[end];

		double dis = Math.pow(startPoint.x - endPoint.x, 2) + Math.pow(startPoint.y - endPoint.y, 2);
		return Math.sqrt(dis);
	}

	static void Dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			int curV = cur.vertex;
			double curW = cur.weight;

			if (distance[curV] < curW)
				continue;

			for (Edge next : graph[curV]) {
				if (distance[next.vertex] > next.weight + cur.weight) {
					distance[next.vertex] = next.weight + cur.weight;
					pq.offer(new Edge(next.vertex, distance[next.vertex]));
				}
			}

		}

	}

}
