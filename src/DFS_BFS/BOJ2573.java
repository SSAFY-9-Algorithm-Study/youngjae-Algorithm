package DFS_BFS;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Point2573 {
	int x;
	int y;

	Point2573(int x, int y) {
		this.x = x;
		this.y = y;
	}
}


public class BOJ2573 {

	public static int board[][];
	public static int visitedBFS[][];
	public static int visited[][];
	public static int visitedDFS[][];

	public static int dx[] = { -1, 0, 1, 0 };
	public static int dy[] = { 0, 1, 0, -1 };

	public static int row;
	public static int col;
	public static int count;
	public static boolean flag;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		flag=true;

		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		board = new int[row][col];

		

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		
		count = 0;
		visitedDFS = new int[row][col];
		
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {
				if (board[i][j] != 0) {
					visitedDFS[i][j] = 1;
				}
			}

		}
		
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {
				if (visitedDFS[i][j] != 0) {
					count++;
					visitedDFS[i][j] = 0;
					DFS(new Point2573(i, j));
				}
			}

		}

		if(count!=1) {
			
			flag=false;
		}
		
		
		while(flag) {
			count = 0;
			visited = new int[row][col];
			visitedBFS = new int[row][col];
			visitedDFS = new int[row][col];


//			
//			count = 0;
//			visitedDFS = new int[row][col];

			Point2573 start = find();
			if(start == null) {
				count = 0;
				break;
			}
			BFS(start);
//			System.out.println("board");
//			for (int k = 0; k < row; k++) {
//
//				System.out.println(Arrays.toString(board[k]));
//			}
			
			for (int i = 0; i < row; i++) {

				for (int j = 0; j < col; j++) {
					if (board[i][j] != 0) {
						visitedDFS[i][j] = 1;
					}
				}

			}
			
//			System.out.println("visitedDFS");
//			for (int k = 0; k < row; k++) {
//
//				System.out.println(Arrays.toString(visitedDFS[k]));
//			}

			
			for (int i = 0; i < row; i++) {

				for (int j = 0; j < col; j++) {
					if (visitedDFS[i][j] != 0) {
						count++;
						visitedDFS[i][j] = 0;
						DFS(new Point2573(i, j));
					}
				}

			}
//			System.out.println("count: "+count);
//			System.out.println("answer: "+answer);
			
			
			if(count!=1) {
				
				flag=false;
			}
			answer++;
		}
		
		
			
		if(count>=2)
			System.out.println(answer);
		else
			System.out.println(0);

		br.close();

	}

	public static Point2573 find() {
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {

				if (board[i][j] != 0) {
//					System.out.println(i + "," + j);
					return new Point2573(i, j);
				}
			}

		}
		return null;

	}

	public static void DFS(Point2573 start) {
		for (int i = 0; i < 4; i++) {
			int nextX = start.x + dx[i];
			int nextY = start.y + dy[i];

			if (nextX >= 0 && nextY >= 0 && nextX < row && nextY < col) {

				if (visitedDFS[nextX][nextY] == 1) {
					visitedDFS[nextX][nextY] = 0;
					DFS(new Point2573(nextX, nextY));
				}

			}

		}

	}

	public static int BFS(Point2573 start) {
		Queue<Point2573> queue = new LinkedList<Point2573>();
		queue.offer(start);
		visited[start.x][start.y] = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point2573 cur = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];

					if (nextX >= 0 && nextY >= 0 && nextX < row && nextY < col) {
						if (board[nextX][nextY] == 0) {

							visitedBFS[cur.x][cur.y]++;
						}
						if (visited[nextX][nextY] == 0 && board[nextX][nextY] != 0) {
							visited[nextX][nextY] = 1;

							queue.offer(new Point2573(nextX, nextY));
						}

					}

				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] -= visitedBFS[i][j];
				if (board[i][j] < 0) {
					board[i][j] = 0;
				}
			}

		}

		return 0;

	}


}
