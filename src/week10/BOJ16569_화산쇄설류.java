package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16569_화산쇄설류 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static class Volcanic implements Comparable<Volcanic> {
		int x;
		int y;
		int t;

		public Volcanic(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}

		@Override
		public int compareTo(Volcanic o) {
			// TODO Auto-generated method stub
			return this.t - o.t;
		}

	}

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static int M, N, V; // 행, 열, 화산수
	static int manX, manY; // 사람 위치
	static int board[][];
	static int visited[][];
	static int answerH;
	static int answerT;
	static ArrayList<Volcanic> list;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		manX = Integer.parseInt(st.nextToken())-1;
		manY = Integer.parseInt(st.nextToken())-1;

		board = new int[M][N];
		visited = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answerH = board[manX][manY];
		answerT = 0;

		list = new ArrayList<Volcanic>();
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) -1;
			int y = Integer.parseInt(st.nextToken()) -1;
			int t = Integer.parseInt(st.nextToken());
			visited[x][y] = 1;
			list.add(new Volcanic(x, y, t));
		}

		Collections.sort(list);
		BFS(new Point(manX, manY));
		System.out.println(answerH +" "+answerT);

		br.close();

	}

	static void BFS(Point start) {
		Queue<Point> queue = new LinkedList<Point>();
		visited[start.x][start.y] = 1;
		queue.offer(start);
		int level = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {

				Point cur = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if (0 <= nx && nx < M && 0 <= ny && ny < N) {
						if (visited[nx][ny] == 0) {
							visited[nx][ny] = 1;
							boolean flag = true;
							for (Volcanic v : list) {
								if ((level - v.t) > 0) {
									if ((Math.abs(nx - v.x) + Math.abs(ny - v.y)) <= (level - v.t)) {
										flag = false; // 화산재 안에 있다.
										break;
									}
								}
							}

							
							if (flag == true) {
//								System.out.println(board[nx][ny]);
								queue.offer(new Point(nx, ny));
								if (answerH < board[nx][ny]) {
									answerH = board[nx][ny];
									answerT = level;
								}
							}

						}
					}

				}

			}
//			System.out.println();

			level++;

		}
		
//		System.out.println(level);
	}

}
