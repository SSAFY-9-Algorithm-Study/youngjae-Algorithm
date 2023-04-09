package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA2382_미생물_격리 {

	static class Bug implements Comparable<Bug> {
		int number;
		int direction;

		public Bug(int number, int direction) {
			this.number = number;
			this.direction = direction;
		}

		@Override
		public int compareTo(Bug o) {

			return o.number - this.number;
		}

		@Override
		public String toString() {
			return "Bug [number=" + number + ", direction=" + direction + "]";
		}

	}

	// 상,하,좌,우
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static int N;
	static int M;
	static int K;

	static ArrayList<Bug> board[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new ArrayList[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = new ArrayList<Bug>();
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int number = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				board[x][y].add(new Bug(number, direction - 1));

			}

			for (int i = 0; i < M; i++) {
				move();
				sumBug();
			}

			// 남아있는 미생의 합
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j].size() > 0) {
						sum += board[i][j].get(0).number;

					}
				}
			}
			System.out.printf("#%d %d\n", tc, sum);

		}

		br.close();

	}

	static void move() {

		ArrayList<Bug> tmpBoard[][] = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmpBoard[i][j] = new ArrayList<Bug>();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].size() > 0) {
					Bug bug = board[i][j].get(0);
					int nx = 0;
					int ny = 0;
					if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						nx = dx[bug.direction] + i;
						ny = dy[bug.direction] + j;
					} else {
						nx = dx[bug.direction] * (-1) + i;
						ny = dy[bug.direction] * (-1) + j;

						int dir = 0;
						for (int d = 0; d < 4; d++) {
							if (dx[bug.direction] * (-1) == dx[d] && dy[bug.direction] * (-1) == dy[d]) {
								dir = d;
							}
						}

						bug.direction = dir;
					}
					if (nx == 0 || nx == (N - 1) || ny == 0 || ny == (N - 1))
						bug.number = bug.number / 2;
					tmpBoard[nx][ny].add(bug);
				}
			}
		}
		board = tmpBoard;

	}

	static void sumBug() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].size() > 1) {
					Collections.sort(board[i][j]);

					Bug sumBug = board[i][j].get(0);

					for (int s = 1; s < board[i][j].size(); s++) {
						Bug bug = board[i][j].get(s);

						sumBug.number += bug.number;
					}
					board[i][j].clear();
					board[i][j].add(sumBug);

				}
			}
		}

	}

}
