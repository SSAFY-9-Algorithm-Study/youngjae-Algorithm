package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1922_네트워크_연결 {

	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		int cost;

		public Edge(int v1, int v2, int cost) {
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	static int[] unf; // 자신의 집합번호를 저장하는 배열
	static ArrayList<Edge> arr; // 트리정보 저장한는 리스트
	static int N; //컴퓨터의 수 N 
	static int M; // 연결할 수 있는 선의 수 M 
	static int answer; // 컴퓨터를 연결하는데 필요한 최소비용

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		arr=new ArrayList<Edge>();
		unf = new int[N+1];

		for (int i = 1; i <= N; i++) {
			unf[i] = i;
		}

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			arr.add(new Edge(v1, v2, cost));
		}

		answer = 0;
		Collections.sort(arr);  // 비용을 기준으로 우선 정렬

		for (Edge cur : arr) {
			int fv1 = Find(cur.v1);
			int fv2 = Find(cur.v2);
			if (fv1 != fv2) { // 두 집합의 번호가 다르면, 순환이 되지 않으면, 결합 
				answer+=cur.cost; // 연결비용을 더한다.
				Union(cur.v1, cur.v2); // 두 집합을 합친다.
			}
				
		}
		System.out.println(answer);

		br.close();
	}

	// 정점들의 집합 번호를 찾는다.
	static int Find(int v) {
		if (v == unf[v])  // 현재 정점이 자신의 집합번호인 경우
			return v;
		else // 현재 정점이 자신의 집합번호가 이닌 경우 재귀
			return unf[v] = Find(unf[v]);  // 정점들의 집합 번호를 갱신해줌, 트리 압축
	}

	// 두 집합을 순환이 되지 않도록 합친다.
	static void Union(int v1, int v2) {
		int fv1 = Find(v1);
		int fv2 = Find(v2);
		if (fv1 != fv2) // 두 집합의 번호가 다르면 합쳐줌, 트리가 압축되지는 않는다.
			unf[fv1] = fv2;
	}

}
