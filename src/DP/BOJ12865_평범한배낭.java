package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ12865_평범한배낭 {

	static class Thing implements Comparable<Thing> {
		int weight;
		int value;

		public Thing(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Thing o) {
			return this.weight - o.weight;
		}
	}

	static int N; // 물품수
	static int K; // 버틸수 있는 무게
	static Thing[] thingArray; // 물건 저장 배열
	static int[] DP; // DP테이블

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		thingArray = new Thing[N];
		
		DP = new int[K + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			thingArray[i] = new Thing(weight, value);
		}
		Arrays.sort(thingArray);
		// 물건이 여러개가 있다고 착각했다.
//		for (int i = 0; i < N; i++) {
//			for (int j = thingArray[i].weight; j <= K; j++) {
//				DP[j] = Math.max(DP[j], DP[j - thingArray[i].weight] + thingArray[i].value);
//			}
//		}
		
		// 물건이 유일하게 하나만 존재하기 때문에 반복문을 뒤에서부터 돌게 되었다.
		for (int i = 0; i < N; i++) {
			for (int j = K; j >= thingArray[i].weight; j--) {
				DP[j] = Math.max(DP[j], DP[j - thingArray[i].weight] + thingArray[i].value);
			}
		}

		System.out.println(DP[K]);

		br.close();

	}

}
