package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056_마법사_상어와_파이어볼 {

	static class FireBall {
		int m; // 질량
		int s; // 속력
		int d; // 방향

		public FireBall(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static int N;  // 격자 크기
	static int M;  // 파이어볼 갯수
	static int K;  // 반복 횟수

	// 8방향
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, - 1, -1, -1 };

	// 파이어볼의 상태를 저장하는 2차원 리스트, 격자판에 파이어볼이 몇개 들어갈지 예측 불가
	static ArrayList<FireBall> board[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = new ArrayList<FireBall>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			board[r][c].add(new FireBall(m, s, d));
		}

		for (int i = 0; i < K; i++) {
			moveFireBall();
			divFireBall();
		}

		// 남아있는 파이어볼 질량의 합
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].size() > 0) {

					for (FireBall fire : board[i][j]) {
						sum += fire.m;
					}
				}
			}
		}
		System.out.println(sum);

		br.close();
	}

	static void moveFireBall() {

		// 이동 처리한 파이어볼이 중복해서 이동할 수 있으므로 파이어볼의 이동 상태를 미리 저장하는 2차원 리스트
		ArrayList<FireBall> tmpBoard[][];

		tmpBoard = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmpBoard[i][j] = new ArrayList<FireBall>();
			}
		}

		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 파이어볼이 존재하면 이동
				if (board[i][j].size() > 0) {

					for (FireBall fire : board[i][j]) {

						// 상하좌우로 한바퀴 회전가능하기 (하나의 구 처럼 이어져있기)때문에, N으로 나눈 나머지 값을 더해준다.
						// 음수로 나오는것을 방지하기 위해 N으로 더해
						int nx = (((i + dx[fire.d] * fire.s) % N) + N);
						int ny = (((j + dy[fire.d] * fire.s) % N) + N);

						tmpBoard[nx % N][ny % N].add(new FireBall(fire.m, fire.s, fire.d));
					}

				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = tmpBoard[i][j];
			}
		}

	}

	static void divFireBall() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				// 2개 이상 존재할 경우
				if (board[i][j].size() >= 2) {
					int sumM = 0; // 합쳐진 파이어볼 질량의 합
					int sumS = 0; // 합쳐진 파이어볼 속력의 합

					// 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 
					// 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
					boolean evenFlag = true; // 모든방향이 짝
					boolean oddFlag = true; // 모든방향이 홀

					for (FireBall fire : board[i][j]) {
						sumM += fire.m;
						sumS += fire.s;
						if (fire.d % 2 != 0) // 짝수가 아인 경우
							evenFlag = false;
						else // 홀수가 아닌 경우
							oddFlag = false;
					}

					int calM = sumM / 5;  //질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다
					int calS = sumS / board[i][j].size(); //속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.

					// 원래 파이어볼이 있던곳 새로운 파이볼로 갱신
					board[i][j] = new ArrayList<FireBall>();

					// 파이어볼이 소멸되지 않으면
					if (calM > 0) {
						// 4개의 파이어볼로 나누기
						for (int k = 0; k < 4; k++) {
							if (oddFlag == true || evenFlag == true) {
								board[i][j].add(new FireBall(calM, calS, k * 2));
							} else {
								board[i][j].add(new FireBall(calM, calS, k * 2 + 1));
							}
						}

					}

				}

			}
		}

	}

}
