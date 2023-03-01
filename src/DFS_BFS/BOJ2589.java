package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


//class Point {
//	int x;
//	int y;
//
//	Point(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//}
public class BOJ2589 {

	public static int row;
	public static int col;
	public static int dx[] = { -1, 0, 1, 0 };
	public static int dy[] = { 0, 1, 0, -1 };
	public static int board[][];
	public static int visited[][];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		board = new int[row][col];
		visited = new int[row][col];

		for (int i = 0; i < row; i++) {
			char charArr[] = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {

				if (charArr[j] == 'W') {
					board[i][j] = 1;
				} else {
					board[i][j] = 0;
				}

			}
		}

		int answer = Integer.MIN_VALUE;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 0) {
					answer = Math.max(BFS(new Point(i, j)), answer);
				}
			}
		}
		
		System.out.println(answer);

		br.close();
	}

	public static int BFS(Point start) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				visited[i][j] = board[i][j];

			}
		}

		Queue<Point> q = new LinkedList<Point>();
		q.offer(start);
		visited[start.x][start.y] = 1;
		int level = 0;
		while (!q.isEmpty()) {

			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point cur = q.poll();
				for (int i = 0; i < 4; i++) {

					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];

					if (nextX >= 0 && nextY >= 0 && nextX < row && nextY < col) {
						if (visited[nextX][nextY] == 0) {
							visited[nextX][nextY] = 1;
							q.offer(new Point(nextX, nextY));
						}
					}
				}
			}

			
			level++;

		}
		return level-1;
	}

}
