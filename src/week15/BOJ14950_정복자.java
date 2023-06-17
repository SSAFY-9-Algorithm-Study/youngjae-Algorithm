package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14950_정복자 {

	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		int weight;

		public Edge(int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {

			return this.weight - o.weight;
		}
	}

	static int N; // 도시의 개수
	static int M; // 도로의 개수
	static int T; // 도로의 비용
	static int unf[];

	static List<Edge> oneList;
	static List<Edge> otherList;
	static int count;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		unf = new int[N + 1];
		oneList = new ArrayList<Edge>();
		otherList = new ArrayList<Edge>();

		count = 1;
		answer = 0;

		for (int i = 0; i <= N; i++) {
			unf[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			if (start == 1) {
				oneList.add(new Edge(start, end, weight));
			} else {
				otherList.add(new Edge(start, end, weight));
			}

		}

		for (Edge next : oneList) {
			otherList.add(next);
		}

		Collections.sort(oneList);
		Collections.sort(otherList);

		Union(oneList.get(0).v1, oneList.get(0).v2);
		answer = oneList.get(0).weight;

		for (Edge next : otherList) {
			int fv1 = Find(next.v1);
			int fv2 = Find(next.v2);
			if (fv1 != fv2) {
				answer += count * T + next.weight;
				Union(next.v1, next.v2);
				count++;
			}
		}

		System.out.println(answer);

		br.close();
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
