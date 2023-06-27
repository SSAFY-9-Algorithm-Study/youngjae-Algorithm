package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ4386_별자리만들기 {

	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		double weight;

		public Edge(int v1, int v2, double weight) {

			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

	}

	static class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N;
	static Point[] arr;
	static List<Edge> list;
	static int combi[];
	static int visited[];
	static double answer;
	static int unf[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new Point[N];
		combi = new int[2];
		visited = new int[N];

		if (N > 1) {
			unf = new int[(N * (N - 1)) / 2];
		} else {
			unf = new int[1];
		}
		list = new ArrayList<Edge>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			arr[i] = new Point(x, y);
		}

		DFS(0, 0);

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			unf[i] = i;
		}

		for (Edge next : list) {
			int fv1 = Find(next.v1);
			int fv2 = Find(next.v2);
			if (fv1 != fv2) {
				Union(fv1, fv2);
				answer += next.weight;
			}

		}

		System.out.println(answer);

		br.close();

	}

	static double getDist(double x1, double x2, double y1, double y2) {
		return Math.floor(Math.sqrt(Math.abs((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))) * 100.0) * 0.01;
	}

	static void DFS(int level, int start) {
		if (level == 2) {
			list.add(new Edge(combi[0], combi[1],
					getDist(arr[combi[0]].x, arr[combi[1]].x, arr[combi[0]].y, arr[combi[1]].y)));
		} else {
			for (int i = start; i < N; i++) {
				if (visited[i] == 0) {
					visited[i] = 1;
					combi[level] = i;
					DFS(level + 1, i + 1);
					visited[i] = 0;

				}
			}
		}
	}

	// 정점들의 집합 번호를 찾는다.
	static int Find(int v) {
		if (v == unf[v]) // 현재 정점이 자신의 집합번호인 경우
			return v;
		else // 현재 정점이 자신의 집합번호가 이닌 경우 재귀
			return unf[v] = Find(unf[v]); // 정점들의 집합 번호를 갱신해줌, 트리 압축
	}

	// 두 집합을 순환이 되지 않도록 합친다.
	static void Union(int v1, int v2) {
		int fv1 = Find(v1);
		int fv2 = Find(v2);
		if (fv1 != fv2) // 두 집합의 번호가 다르면 합쳐줌, 트리가 압축되지는 않는다.
			unf[fv1] = fv2;
	}

}
