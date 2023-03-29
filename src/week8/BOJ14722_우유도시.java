package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ14722_우유도시 {

	// 딸기->초코, 초코->바나나, 바나나->딸기
	static Map<Integer, Integer> milkMap = new HashMap<Integer, Integer>() {
		{
			put(0, 1); // 딸기->초
			put(1, 2); // 초코->바나나
			put(2, 0); // 바나나->딸기
		}
	};

	static int N; // 영역 크기
	static int[][] board; // 영역 정보
	static int[][] DP; //

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		board = new int[N][N]; // 0,0부터 시작
		DP = new int[N][N];

		int startX = N;
		int startY = N;

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Loop: for (int i = 0; i < N; i++) {

			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
					startX = i;
					startY = j;
					DP[startX][startY] = 1;
					break Loop;
				}
			}
		}
		
		

		// 제일 위 가로 한줄 채우기
		for (int i = startY + 1; i < N; i++) {
			// 마실수 음료인지 확인
			if (milkMap.get(board[startX][i - 1]) == board[startX][i]) {
				DP[startX][i] = DP[startX][i - 1] + 1;
			} else
				DP[startX][i] = DP[startX][i - 1];

		}

		// 제일 왼쪽 세로 한줄 채우기
		for (int i = startX + 1; i < N; i++) {
			// 마실수 음료인지 확인
			if (milkMap.get(board[i - 1][startY]) == board[i][startY]) {
				DP[i][startY] = DP[i - 1][startY] + 1;
			} else
				DP[i][startY] = DP[i - 1][startY];
		}

		// 위쪽, 왼쪽 체크하면서 DP테이블 업데이트

		for (int i = startX + 1; i < N; i++) {
			for (int j = startY + 1; j < N; j++) {

				// 위쪽에서 체크
				int maxUp = DP[i - 1][j];
				if (milkMap.get(board[i - 1][j]) == board[i][j]) {
					maxUp = DP[i - 1][j] + 1;
				}

				int maxLeft = DP[i][j - 1];
				if (milkMap.get(board[i][j - 1]) == board[i][j]) {
					maxLeft = DP[i][j - 1] + 1;
				}

				DP[i][j] = Math.max(maxLeft, maxUp);

			}
		}

//		if (DP[N - 1][N - 2] == 0 && DP[N - 2][N - 1] == 0 && DP[N - 1][N - 1] == 0) {
//			DP[N - 1][N - 1] = 1;
//		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(DP[i]));
//		}

		System.out.println(DP[N - 1][N - 1]);

		br.close();
	}

}
