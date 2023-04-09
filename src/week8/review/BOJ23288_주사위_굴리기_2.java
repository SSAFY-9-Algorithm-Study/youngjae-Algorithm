package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ23288_주사위_굴리기_2 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Dice {
		int x = 0;
		int y = 0;
		int up = 1;
		int down = 6;
		int right = 3;
		int left = 4;
		int front = 5;
		int back = 2;
		int direction = 0;

		@Override
		public String toString() {
			return "Dice [x=" + x + ", y=" + y + ", up=" + up + ", down=" + down + ", right=" + right + ", left=" + left
					+ ", front=" + front + ", back=" + back + ", direction=" + direction + "]";
		}

	}

	// 동, 남, 서, 북
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

	static int N; // 세로 크기 N
	static int M; // 가로 크기 M
	static int K; // 이동하는 횟수 K

	static int score[][];
	static int board[][];
	static int answer = 0;
	static Dice dice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		score = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dice = new Dice();

		for (int s = 0; s < K; s++) {
			moveDice();

			rotaeDice();
//			System.out.println(dice);
			answer += calScoreBFS(new Point(dice.x, dice.y));

			if (dice.down > board[dice.x][dice.y]) {
				dice.direction = (dice.direction + 1) % 4;
			} else if (dice.down < board[dice.x][dice.y]) {
				dice.direction = (dice.direction -1 +4) % 4;
			}

		}
		System.out.println(answer);

		br.close();
	}

	static void moveDice() {
		int nx = dice.x + dx[dice.direction];
		int ny = dice.y + dy[dice.direction];
		int curDir = dice.direction;
		if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
			nx = dice.x + dx[(dice.direction + 2) % 4];
			ny = dice.y + dy[(dice.direction + 2) % 4];
			curDir = (dice.direction + 2) % 4;
		}
		dice.x = nx;
		dice.y = ny;
		dice.direction = curDir;

	}

	static void rotaeDice() {
		Deque<Integer> dque = new LinkedList<Integer>();
		if (dice.direction == 0 || dice.direction == 2) { // 동쪽, 서쪽
//			dque.offer(dice.down);
			dque.offer(dice.left);
			dque.offer(dice.up);
			dque.offer(dice.right);
			dque.offer(dice.down);

			if (dice.direction == 0) {
				dque.offerFirst(dque.pollLast());
//				dice.left = dque.poll();
//				dice.up = dque.poll();
//				dice.right = dque.poll();
//				dice.down = dque.poll();
			} else {
				dque.offer(dque.poll());
//				dice.right = dque.pollLast();
//				dice.up = dque.pollLast();
//				dice.left = dque.pollLast();
//				dice.down = dque.pollLast();
			}
			dice.left = dque.poll();
			dice.up = dque.poll();
			dice.right = dque.poll();
			dice.down = dque.poll();

		}

		else if (dice.direction == 1 || dice.direction == 3) { // 남, 북
			
			dque.offer(dice.back);
			dque.offer(dice.up);
			dque.offer(dice.front);
			dque.offer(dice.down);

			
			if (dice.direction == 1) {
				dque.offerFirst(dque.pollLast());
			} else {
				dque.offer(dque.poll());
			}
			dice.back = dque.poll();
			dice.up = dque.poll();
			dice.front = dque.poll();
			dice.down = dque.poll();
//			if (dice.direction == 1) {// 남
//				dice.back = dque.poll();
//				dice.up = dque.poll();
//				dice.front = dque.poll();
//				dice.down = dque.poll();
//			} else { // 북
//				dice.front = dque.pollLast();
//				dice.up = dque.pollLast();
//				dice.back = dque.pollLast();
//				dice.down = dque.pollLast();
//			}

		}

	}

	static int calScoreBFS(Point start) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(start);
		int visited[][] = new int[N][M];
		visited[start.x][start.y] = 1;
		int curScore = board[start.x][start.y];
		int sumScore = curScore;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (visited[nx][ny] == 0 && board[nx][ny] == curScore) {
							visited[nx][ny] = 1;
							sumScore += curScore;
							queue.offer(new Point(nx, ny));
						}
					}
				}
			}
		}

		return sumScore;
	}

}
