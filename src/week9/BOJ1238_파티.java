package week9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238_파티 {

	static class Edge implements Comparable<Edge> {
		int vertex;
		int weight;

		Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {

			return this.weight - o.weight;
		}

	}

	static int N, M, X;// N명의 학생, 총 M개의 단방향 도로, 파티 장소X  
	static ArrayList<Edge> graph[];
	static ArrayList<Edge> graphReverce[];
	static int[] distance;
	static int[] distanceReverce;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		graphReverce = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Edge>();
			graphReverce[i] = new ArrayList<Edge>();
		}

		distance = new int[N + 1];
		distanceReverce = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[start].add(new Edge(end, weight));
			graphReverce[end].add(new Edge(start, weight));
		}

		distance = Dijksta(X, graph);
		distanceReverce = Dijksta(X, graphReverce);

//		System.out.println(Arrays.toString(distance));
//		System.out.println(Arrays.toString(distanceReverce));

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, distance[i] + distanceReverce[i]);
		}

		System.out.println(answer);
		br.close();

	}

	static int[] Dijksta(int start, ArrayList<Edge> board[]) {
		int[] dis = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			dis[i] = Integer.MAX_VALUE;

		}

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.offer(new Edge(start, 0));
		dis[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			int curV = cur.vertex;
			int curW = cur.weight;

			if (curW > dis[curV])
				continue;
			for (Edge next : board[curV]) {
				if (dis[next.vertex] > curW + next.weight) {
					dis[next.vertex] = curW + next.weight;
					pq.offer(new Edge(next.vertex, dis[next.vertex]));
				}
			}

		}

		return dis;
	}

}
