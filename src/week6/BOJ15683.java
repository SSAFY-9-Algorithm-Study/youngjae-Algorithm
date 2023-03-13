package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ15683 {

	static class Point {
		int x;
		int y;
		int cheked;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int row;
	static int col;
	static int board[][];
	static int visited[][];
	static int answer;
	// 상 우 하 좌
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int visitedDirection[];
	static List<Point> list;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;
		board = new int[row][col];
		visited = new int[row][col];
		list = new ArrayList<Point>();
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (0 < board[i][j] && board[i][j] < 6) {
					list.add(new Point(i, j));
					visited[i][j] = board[i][j];
				}
			}
		}

		DFS(0);
		System.out.println(answer);

		br.close();
	}

	static void DFS(int cnt) {
		if (cnt == list.size()) {

			int count = 0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (visited[i][j] == 0 && board[i][j] == 0) {
						count++;
					}
				}
			}

			
			answer = Math.min(answer, count);
			return;

		} else {
			Point start = list.get(cnt);

			
			for (int dir = 0; dir < 4; dir++) {
				List<Point> visitedList = new ArrayList<Point>();
				if (board[start.x][start.y] == 1) {
					paintDFS(start, dir, visitedList);
				} else if (board[start.x][start.y] == 2) {
					for (int i = 0; i <= 2; i = i + 2) {
						paintDFS(start, (dir + i) % 4, visitedList);
					}
				} else if (board[start.x][start.y] == 3) {
					for (int i = 0; i < 2; i++) {
						paintDFS(start, (dir + i) % 4, visitedList);
					}
				} else if (board[start.x][start.y] == 4) {
					for (int i = 0; i < 3; i++) {
						paintDFS(start, (dir + i) % 4, visitedList);
					}
				} else if (board[start.x][start.y] == 5) {
					for (int i = 0; i < 4; i++) {
						paintDFS(start, (dir + i) % 4, visitedList);
					}
				}

				DFS(cnt + 1);

				for (Point visit : visitedList) {
					visited[visit.x][visit.y] = 0;
				}

			}

			

		}
	}

	static void paintDFS(Point start, int dir, List viList) {
		int nx = start.x + dx[dir];
		int ny = start.y + dy[dir];

		if (0 <= nx && nx < row && 0 <= ny && ny < col) {
			if (board[nx][ny] != 6) {
				if(visited[nx][ny]==0)
					viList.add(new Point(nx, ny));
				visited[nx][ny] = 1;
				
				paintDFS(new Point(nx, ny), dir, viList);
			}

		}

	}

}
