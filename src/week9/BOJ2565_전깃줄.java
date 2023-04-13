package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2565_전깃줄 {

	static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}

	}

	static int[] DP;  // 자기보다 큰항의 갯수를 저장하는 DP테이블 
	static Point[] sortXArr;
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		sortXArr = new Point[N];
		DP = new int[N];
		DP[0] = 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			sortXArr[i] = new Point(x, y);
		}

		Arrays.sort(sortXArr);
		int realMax = Integer.MIN_VALUE;

		for (int i = 1; i < N; i++) {
			int max = 0; // 자기보다 작은 앞의 항의 갯수
			for (int j = i - 1; j >= 0; j--) {
				if (sortXArr[j].y < sortXArr[i].y && DP[j] > max) {
					max = DP[j];
				}
			}
			DP[i] = max + 1;
			realMax = Math.max(realMax, DP[i]);
		}

//		System.out.println(Arrays.toString(DP));
		System.out.println(N-realMax);

	}

}
