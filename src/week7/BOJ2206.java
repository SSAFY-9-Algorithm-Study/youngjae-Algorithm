package week7;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ2206 {

	static class Point {
		int x;
		int y;
		boolean isBroken;

		public Point(int x, int y, boolean isBroken) {
			this.x = x;
			this.y = y;
			this.isBroken = isBroken;  
			// 부순경우의 분기를 확인하기 위해, BFS에서는 부모의 분기를 확인하기 힘듦
		}

	}

	static int row;
	static int col;
	static int board[][];
	static int unbokendVisited[][];
	static int bokendVisited[][];
	static int distance = Integer.MAX_VALUE;
	static ArrayList<Point> wallList;

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		board = new int[row][col];
		unbokendVisited = new int[row][col];
		bokendVisited = new int[row][col];  // 벽 부순경우의 visited배열
		wallList = new ArrayList<Point>();
		for (int i = 0; i < row; i++) {

			String strArr = br.readLine();
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(Character.toString(strArr.charAt(j)));

			}
		}

		int dist = BFS(new Point(0, 0, false));
		if (dist != -1) {
			distance = Math.min(dist, distance);
		}

		//완전완전완전완전 탐색하면 시간초과, 벽을 찾아가며 하나 하나씩 부순후 시작지점에서BFS시작 
//		for(Point w:wallList) {
//			board[w.x][w.y] = 0;
//			int dist = BFS(new Point(0, 0));
//			if (dist != -1) {
//				distance = Math.min(dist, distance);
//			}
//			board[w.x][w.y] = 1;
//		}

		if(row==1&& col==1) {
			System.out.println(1);
		}
		else if (distance == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(distance);

		}

		br.close();
	}

	static int BFS(Point start) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(start);

		unbokendVisited[start.x][start.y] = 1;

		int level = 1;

		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					// 지금까지 벽 부순적이 없는 경우
					if (cur.isBroken == false) {
						if (0 <= nx && nx < row && 0 <= ny && ny < col) {
							if (board[nx][ny] == 0 && unbokendVisited[nx][ny] == 0) { // 그냥 지나가기
								unbokendVisited[nx][ny] = 1;
								if (nx == (row - 1) && ny == (col - 1)) {
									return level + 1;
								}
								queue.offer(new Point(nx, ny, false));
							} else if (board[nx][ny] == 1 ) {  // 벽 부수기
								bokendVisited[nx][ny] = 1;  //벽 부신위치의 visited배열을 새롭게 시작
								queue.offer(new Point(nx, ny, true));
							}
						}
					} else if (cur.isBroken == true) {// 지금까지 벽 부순적이 있는 경우
						if (0 <= nx && nx < row && 0 <= ny && ny < col && board[nx][ny] == 0) { // 부순경우 지나가기
							if (bokendVisited[nx][ny] == 0) {
								bokendVisited[nx][ny] = 1;
								if (nx == (row - 1) && ny == (col - 1)) {
									return level + 1;
								}
								queue.offer(new Point(nx, ny, true));
							}
						}
					}

				}
			}
			level++;

		}

		return -1;

	}

}
