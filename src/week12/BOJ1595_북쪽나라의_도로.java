package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1595_북쪽나라의_도로 {

	static class Edge {
		int to;
		int cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

	}

	static int maxDepth;
	static int leafNode;
	static int visited[];

	public static void main(String[] args) throws IOException {
		
		ArrayList<Edge> graph[];

		StringTokenizer st;
		graph = new ArrayList[10001];

		for (int i = 0; i < 10001; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		String intput;
		int from=0;
		
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
			while (!(intput = br.readLine()).isEmpty()) {
				if(intput!=null) {
					st = new StringTokenizer(intput);
					from = Integer.parseInt(st.nextToken());
					int to = Integer.parseInt(st.nextToken());
					int cost = Integer.parseInt(st.nextToken());

					graph[from].add(new Edge(to, cost));
					graph[to].add(new Edge(from, cost));
				}
				
			}
		}catch (Exception e) {}
		
		

		if(from==0) {
			System.out.println(0);
		}else {
			maxDepth = 0;
			leafNode = 1;
			visited = new int[10001];
			visited[leafNode]=1;
			DFS(graph, leafNode, 0);
			
			maxDepth=0;
			visited[leafNode]=1;
			visited=new int[10001];
			DFS(graph,leafNode,0);

			System.out.println(maxDepth);
		}
		

		
	}

	static void DFS(ArrayList<Edge> graph[], int start, int level) {

		for (Edge edge : graph[start]) {
			if (visited[edge.to] == 0) {
				visited[edge.to] = 1;
				
				DFS(graph, edge.to, level + edge.cost);

				if (level + edge.cost > maxDepth) {
					maxDepth = level + edge.cost;
					leafNode = edge.to;
				}

			}

		}

	}

}
