package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.BlockingDeque;

public class BOJ17822 {

	static int N; // 반지름 N인 원판
	static int M; // 원판에는 적혀있는 M개 정수
	static int T; // T번 회전

	static int x; // 번호가 x인 배수 원판
	static int d; // d방향 (0인 경우는 시계 방향, 1인 경우는 반시계 방향)
	static int k; // k칸 회전

	static int board[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		board = new int[N + 1][M];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			// 원판 회전
			spin();

			// 인접하면서 같은 수를 모두 지운다.
			if (!findAdj()) {
				updateBoard();

			}
		}

		int sum = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				sum += board[i][j];
			}
		}

		System.out.println(sum);

		br.close();
	}

	// 원판 회전
	static void spin() {

		for (int s = 0; s < k; s++) {
			for (int r = x; r <= N; r = r + x) {
				if (d == 1) { // 반시계
					for (int i = 0; i < M - 1; i++) {

						int tmp = board[r][i];
						board[r][i] = board[r][i + 1];
						board[r][i + 1] = tmp;
					}
				} else {// 시계
					for (int i = M - 1; i >= 1; i--) {
						int tmp = board[r][i];
						board[r][i] = board[r][i - 1];
						board[r][i - 1] = tmp;
					}
				}

			}
		}

	}

	// 인접하면서 같은 수를 모두 지운다.
	static boolean findAdj() {
		boolean flag = false;

		//미리 인접한수들을 미리 체크해놓고, 나중에 지워줘야한다.
		boolean check[][] = new boolean[N + 1][M];

		//가로방향 인접 확인
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0 && board[i][j] == board[i][(j + 1) % M]) {
					flag = true;
					check[i][j] = true;
					check[i][(j + 1) % M] = true;

				}
			}
		}

		//세로방향 인접 확인
		for (int j = 0; j < M; j++) {
			for (int i = 1; i < N; i++) {
				if (board[i][j] != 0 && board[i][j] == board[i + 1][j]) {
					flag = true;
					check[i][j] = true;
					check[i + 1][j] = true;

				}

			}

		}

		//인접한게 있었다면 check배열을 참고해  0으로 바꿔준다.
		if (flag) {
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j]) {
						board[i][j] = 0;
					}
				}
			}
		}

		return flag;
	}

	//평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
	static void updateBoard() {
		double sum = 0;
		int count = 0;
		double ave;
		
		//0이 아닌 값 카운트 및 합계산
		for (int i = 1; i <= N; i++) {

			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0) {
					sum += board[i][j];
					count++;
				}
			}

		}
		//평균 계산
		ave = sum / count;
		for (int i = 1; i <= N; i++) {

			
			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0 && board[i][j] > ave) { //평균보다 큰 수에서 1을 빼
					board[i][j]--;

				} else if (board[i][j] != 0 && board[i][j] < ave) { //평균보다 작은 수에는 1을 더한다
					board[i][j]++;
				}
			}

		}

	}

}
