package week18;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2457_공주님의정원 {

	static class Flower implements Comparable<Flower> {
		int start;
		int end;

		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Flower o) {
			if (this.start == o.start) {
				return o.end - this.end;
			}
			return this.start - o.start;
		}
	}

	static int month[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	static int day[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	static int arr[][];

	static int N;

	static Flower[] fArr;

	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		arr = new int[13][32];

		int index = 1;
		for (int i = 1; i < month.length; i++) {
			for (int j = 1; j < day[i] + 1; j++) {
				arr[i][j] = index;
				index++;
			}
		}

		N = Integer.parseInt(br.readLine());
		fArr = new Flower[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startM = Integer.parseInt(st.nextToken());
			int startD = Integer.parseInt(st.nextToken());
			int endM = Integer.parseInt(st.nextToken());
			int endD = Integer.parseInt(st.nextToken());

			if (arr[startM][startD] < arr[3][1]) {
				arr[startM][startD] = arr[3][1];
			}

			if (arr[endM][endD] < arr[3][1]) {
				arr[endM][endD] = arr[3][1];
				continue;
			}
			

			if (arr[startM][startD] > arr[12][1]) {
				arr[startM][startD] = arr[12][1];
				continue;
			}

			if (arr[endM][endD] > arr[12][1]) {
				arr[endM][endD] = arr[12][1];
			}
			
//			if(arr[startM][startD] == arr[endM][endD]) {
//				continue;
//			}

			fArr[i] = new Flower(arr[startM][startD], arr[endM][endD]);
		}

		if (fArr.length == 0) {
			System.out.println(0);
			return;
		}
		
		Arrays.sort(fArr);

		int start = fArr[0].start;
		int end = fArr[0].end;
		int count = 1;

		if (start != arr[3][1]) {
			System.out.println(0);
			return;
		}


		for (int i = 1; i < fArr.length; i++) {
			
			if (fArr[i].start <= start && fArr[i].end > end) {
				end = fArr[i].end;
			}
			if (fArr[i].start >= start && fArr[i].end > end //
					&& end >= fArr[i].start) {
				start = end;
				end = fArr[i].end;

				count++;
			} 
		
		}

		if (fArr.length == 0) {
			System.out.println(0);
		} else if (end != arr[12][1]) {
			System.out.println(0);
		} else {
			System.out.println(count);
		}

		br.close();
	}

}