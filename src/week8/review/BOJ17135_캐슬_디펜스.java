package week8.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ17135_캐슬_디펜스 {

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int length;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.length == o.length) {
				return this.y - o.y;
			}
			return this.length - o.length;
		}

	}

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static int N;
	static int M;
	static int D;

	static int board[][];
	static int tmpBoard[][];
	static int viisted[];
	static int combi[];
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		tmpBoard = new int[N + 1][M];
		viisted = new int[M];
		combi = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tmpBoard[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(0, 0);
		System.out.println(answer);
		br.close();
	}

	static void DFS(int levle, int start) {
		if (levle == 3) {

			board = new int[N + 1][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					board[i][j] = tmpBoard[i][j];
				}
			}
			int count = 0;
			for (int i = 0; i < N; i++) {
				Point enemy1 = BFS(new Point(N, combi[0]));
				Point enemy2 = BFS(new Point(N, combi[1]));
				Point enemy3 = BFS(new Point(N, combi[2]));

				if (enemy1 != null) {
					board[enemy1.x][enemy1.y]=0;
					count++;
				}
				if (enemy2 != null) {
					if(board[enemy2.x][enemy2.y]==1) {
						board[enemy2.x][enemy2.y]=0;
						count++;
					}
					
				}
				if (enemy3 != null) {
					if(board[enemy3.x][enemy3.y]==1) {
						board[enemy3.x][enemy3.y]=0;
						count++;
					}
					
				}

				moveEnemy();
			}
			answer = Math.max(answer, count);

		} else {
			for (int i = 0; i < M; i++) {
				if (viisted[i] == 0) {
					viisted[i] = 1;
					combi[levle] = i;
					DFS(levle + 1, i + 1);
					viisted[i] = 0;
				}
			}
		}
	}

	static void moveEnemy() {
		for (int i = 0; i < M; i++) {
			for (int j = N - 2; j >= 0; j--) {
				board[j + 1][i] = board[j][i];
			}
		}

		for (int j = 0; j < M; j++) {
			board[0][j] = 0;
		}
	}

	static Point BFS(Point start) {
		Queue<Point> queue = new LinkedList<Point>();
		ArrayList<Point> list = new ArrayList<Point>();
		queue.add(start);

		int enemyVisited[][] = new int[N][M];

		int level = 0;
		while (!queue.isEmpty()) {
			if (level == D) {
				break;
			}
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (enemyVisited[nx][ny] == 0) {
							enemyVisited[nx][ny] = 1;
							Point point = new Point(nx, ny);

							if (board[nx][ny] == 1) {
								point.length = level + 1;
								list.add(point);
							}
							queue.offer(point);
						}
					}

				}
			}

			level++;
		}

		Collections.sort(list);
		if (!list.isEmpty())
			return list.get(0);
		else
			return null;

	}

}
