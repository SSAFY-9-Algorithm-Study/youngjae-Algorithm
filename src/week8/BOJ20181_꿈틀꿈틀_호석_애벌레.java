package week8;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ20181_꿈틀꿈틀_호석_애벌레 {

	static class Energy {
		long energy;
		int left;
		int rigth;

		public Energy(long energy, int left, int rigth) {
			this.energy = energy;
			this.left = left;
			this.rigth = rigth;
		}

	}

	static Map<Integer, ArrayList<Energy>> map;

	static int N; // 먹이 개수
	static long K; // 최소 만족도

	static long DP[]; // DP테이블, i번쨰 부터 나뭇가지를 먹는다.

	static long branch[];
	static ArrayList<Energy> energyList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		DP = new long[N];
		branch = new long[N];
		
		map=new HashMap<Integer, ArrayList<Energy>>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			branch[i] = Long.parseLong(st.nextToken());
		}

		energyList = new ArrayList<Energy>();

		int lt = 0;
		int sum = 0;
		for (int rt = 0; rt < N; rt++) {
			sum += branch[rt];
			if (sum >= K) {

				if (!map.containsKey(lt)) {
					map.put(lt, new ArrayList<>());

				}

				map.get(lt).add(new Energy(sum - K, lt, rt));
//				System.out.println(sum);
//				System.out.println(lt + " " + rt);
//				energyList.add(new Energy(sum - K, lt, rt));

				while (sum >= K) {
					sum -= branch[lt];
					lt++;
				}
			}
		}

		for (int i = N - 1; i >= 0; i--) {

			if (map.containsKey(i)) {
				energyList = map.get(i);
				for (Energy e : energyList) {

					if (e.left == i) {
						long max = 0;
						for (int j = i + 1; j < N; j++) {
							max = Math.max(max, DP[j]);
						}

						if (e.rigth + 1 < N)
							DP[i] = Math.max(DP[e.rigth + 1] + e.energy, max);
						else
							DP[i] = Math.max(max, e.energy);
					}

				}
			}

//			for (Energy e : energyList) {
//
//				if (e.left == i) {
//					long max = 0;
//					for (int j = i + 1; j < N; j++) {
//						max = Math.max(max, DP[j]);
//					}
//
//					if (e.rigth + 1 < N)
//						DP[i] = Math.max(DP[e.rigth + 1] + e.energy, max);
//					else
//						DP[i] = Math.max(max, e.energy);
//				}
//
//			}

		}

//		System.out.println(Arrays.toString(DP));

		System.out.println(DP[0]);
		br.close();
	}

}
