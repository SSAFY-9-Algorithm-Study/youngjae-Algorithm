package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA0321_1 {

	static int chage;
	static int number;
	static int numArr[];
	static int sortNumArr[];
	static String numStr;
	static int max;
	static int swapCount;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			numStr = st.nextToken();
			chage = Integer.parseInt(st.nextToken());
			max = Integer.MIN_VALUE;
			swapCount = 0;

			numArr = new int[numStr.length()];
			sortNumArr = new int[numStr.length()];
			for (int i = 0; i < numStr.length(); i++) {
				numArr[i] = Integer.parseInt(String.valueOf(numStr.charAt(i)));
			}

			for (int i = 0; i < numStr.length(); i++) {
				sortNumArr[i] = numArr[i];
			}


			int maxChange = select_sortCount(sortNumArr);
			

			StringBuffer sb = new StringBuffer();
			maxChange = Math.min(getMinswap(), maxChange);
			if (chage >= maxChange) {
				
				while (chage > maxChange) {
					if (!isDuplicated())
						swapMin();
					maxChange++;
				}

				for (int i = 0; i < sortNumArr.length; i++) {
					sb.append(String.valueOf(sortNumArr[i]));
				}
				System.out.printf("#%d %s\n", tc, sb.toString());

			} else {

			
				// 횟수만큼만 선택정렬 스왑
				int size = numArr.length;
				int swapcount = 0;
				for (int i = 0; i < size - 1; i++) {
					int max_index = i;

					// 최댓값을 갖는 인덱스 찾기
					for (int j = i; j < size; j++) {
						if (numArr[j] >= numArr[max_index]) {
							max_index = j;
						}
					}

					if (max_index != i) {
						swapcount++;
						int temp = numArr[max_index];
						numArr[max_index] = numArr[i];
						numArr[i] = temp;
					}

					if (swapcount == chage) {
						break;
					}

				}

				for (int i = 0; i < sortNumArr.length; i++) {
					sb.append(String.valueOf(numArr[i]));
				}
				System.out.printf("#%d %s\n", tc, sb.toString());

			}

		}

		br.close();

	}

	static int getMinswap() {
		int size = numArr.length;
		int swapcount = 0;
		for (int i = 0; i < size ; i++) {
			if (numArr[i] != sortNumArr[i]) {
				swapcount++;
			}
		}

		
		if (swapcount % 2 == 0)
			return swapcount / 2;
		else
			return Integer.MAX_VALUE;
	}

	static boolean isDuplicated() {
		int size = sortNumArr.length;
		int swapcount = 0;
		for (int i = 0; i < size - 2; i++) {
			if (sortNumArr[i] == sortNumArr[i + 1]) {
				return true;
			}
		}
		return false;
	}

	static void swapMin() { // 최소 인덱스 끼리 교환
		int minIdx1 = sortNumArr.length - 2;
		int minIdx2 = sortNumArr.length - 1;

		Integer temp = sortNumArr[minIdx1];
		sortNumArr[minIdx1] = sortNumArr[minIdx2];
		sortNumArr[minIdx2] = temp;
	}

	static int select_sortCount(int arr[]) {

		int size = arr.length;
		int swapcount = 0;
		for (int i = 0; i < size - 1; i++) {
			int max_index = i;

			// 최댓값을 갖는 인덱스 찾기
			for (int j = i; j < size; j++) {
				if (arr[j] > arr[max_index]) {
					max_index = j;
				}
			}

			if (max_index != i) {
				swapcount++;
				int temp = arr[max_index];
				arr[max_index] = arr[i];
				arr[i] = temp;
			}

		}

		return swapcount;
	}
}
