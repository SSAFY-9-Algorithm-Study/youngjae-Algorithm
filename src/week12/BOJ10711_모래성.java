package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ10711_모래성 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		

	}

	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int H, W;
	static int board[][];
	static int boardCnt[][];
	static int visited[][];
	static Queue<Point> queue;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		board = new int[H][W];
		boardCnt = new int[H][W];

		queue = new LinkedList<Point>();

		for (int i = 0; i < H; i++) {
			String input = br.readLine();
			for (int j = 0; j < W; j++) {
				if (input.charAt(j) == '.') {
					board[i][j] = 0;
				} else {
					board[i][j] = input.charAt(j) - '0';
				}
			}
		}
		
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (0 < board[i][j] && board[i][j] < 9) {
					int count = 0;
					for (int k = 0; k < 8; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if (board[nx][ny] == 0) {
							count++;
							boardCnt[i][j]++;
						}
					}
					if (boardCnt[i][j] >= board[i][j]) {
						queue.offer(new Point(i, j));
					}
				}
			}
		}
		
		
		if(queue.size()==0) {
			System.out.println(0);

		}else {
			int answer = BFS();
			System.out.println(answer);
		}
		

		

		br.close();

	}

	static int BFS() {
		int level = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean flag = false;
			
			for (int s = 0; s < size; s++) {
				Point curPoint = queue.poll();
				board[curPoint.x][curPoint.y]=0;
				for (int i = 0; i < 8; i++) {
					int nx = curPoint.x + dx[i];
					int ny = curPoint.y + dy[i];

					boardCnt[nx][ny]++;
					if (board[nx][ny] != 0 && boardCnt[nx][ny] == board[nx][ny]) {
						queue.offer(new Point(nx, ny));
						flag = true;
					}
				}
			}

			if (!flag) {
				break;
			}
			level++;

		}

		return level;
	}

}
