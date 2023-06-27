package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ10282_해킹 {

	static class Edge implements Comparable<Edge> {
		int vertex;
		int weight;

		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	static int N; // 컴퓨터 개수
	static int D; // 의존성 개수
	static int C; // 해킹당한 컴퓨터의 번호
	static ArrayList<Edge> graph[];
	static int dis[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int cnt=0; // 총 감염되는 컴퓨터 수
			int duration=0; //감염되기까지 걸리는 시간
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			graph = new ArrayList[N + 1];

			for (int i = 0; i < N + 1; i++) {
				graph[i] = new ArrayList<Edge>();
			}

			dis = new int[N + 1];
			for (int d = 0; d < D; d++) {
				st = new StringTokenizer(br.readLine());

				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

//				graph[start].add(new Edge(end, weight));
				graph[end].add(new Edge(start, weight));
			}
			disktra(C);
//			System.out.println(Arrays.toString(dis));
			
			
			for (int i = 0; i < N + 1; i++) {
				if(dis[i]==Integer.MAX_VALUE)
					continue;
				
				duration=Math.max(duration, dis[i]);
				cnt++;
				
			}		
			
			
			System.out.println(cnt+" "+duration);

		}

		br.close();

	}

	static void disktra(int end) {
		for (int i = 0; i <= N; i++) {
			dis[i] = Integer.MAX_VALUE;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(end, 0));
		dis[end] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			for (Edge next : graph[cur.vertex]) {
				if (dis[next.vertex] > dis[cur.vertex] + next.weight) {
					dis[next.vertex] = dis[cur.vertex] + next.weight;
					pq.add(new Edge(next.vertex, dis[next.vertex]));
				}
			}

		}

	}

}
