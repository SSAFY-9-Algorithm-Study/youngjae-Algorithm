package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;


public class BOJ17352_여러분의_다리가_되어_드리겠습니다 {

	static int N;

	static int unf[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		unf = new int[N + 1];
		for (int i = 0; i <= N; i++) { // 현재 정점을 집합번호로 초기화
			unf[i] = i;
		}

		for (int i = 0; i < N - 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			Union(v1, v2);
		}

		HashSet<Integer> set = new HashSet();
		for (int i = 1; i <= N; i++) {
			int fv = Find(i);
			set.add(fv);
		}

		for(Integer i:set) {
			System.out.print(i+" ");
		}

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
