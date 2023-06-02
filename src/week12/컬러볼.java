package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 컬러볼 {

	static class Ball implements Comparable<Ball> {
		int index;
		int color;
		int size;
		int sort_index;

		int sum;

		public Ball(int index, int color, int size) {
			this.index = index;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {

			return this.size - o.size;
		}

	}

	static ArrayList<Ball> list;

	static int sum[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sum = new int[2000][200001];

		int num = Integer.parseInt(br.readLine());
		StringTokenizer st;
		list = new ArrayList();
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			Ball ball = new Ball(i, color, size);
			list.add(ball);
		}

		Collections.sort(list);

		for (int i = 1; i <= 4; i++) {
			if (i == list.get(0).color||list.get(0).size == list.get(1).size) {
				sum[i][0] = 0;
			} else {
				sum[i][0] = list.get(0).size;

			}

		}
		
		for (int i = 1; i < list.size(); i++) {
			list.get(i).sort_index = i;
//			if (i > 0) {
//				sum[0][i] = sum[0][i - 1] + list.get(i).size;
//			} else {
//				sum[0][i] = list.get(i).size;
//
//			}

			for (int j = 1; j <= 4; j++) {
				if (j == list.get(i).color || list.get(i).size == list.get(i - 1).size) {

					sum[j][i] = sum[j][i - 1];
				} else {
					sum[j][i] = sum[j][i - 1] + list.get(i).size;// 색이 겹치는 사이즈를 저장

				}

			}
		}

//		for (int i = 0; i < 5; i++) {
//			System.out.println(i);
//			System.out.println(Arrays.toString(sum[i]));
//		}

		for (int i = 0; i < list.size(); i++) {
//			if (i == 0) {
////				System.out.println(0);
//				list.get(0).sum = 0;
//			} else {
//				list.get(i).sum = sum[0][i - 1] - sum[list.get(i).color][i - 1];
////				System.out.println(sum[0][i-1]-sum[list.get(i).color][i-1]);
//			}

			list.get(i).sum = sum[list.get(i).color][i];

		}
		Collections.sort(list, new Comparator<Ball>() {

			@Override
			public int compare(Ball o1, Ball o2) {
				return o1.index - o2.index;
			}
		});

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).sum);
		}

		br.close();

	}

}
