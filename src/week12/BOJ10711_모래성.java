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

	}

	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int H, W;
	static int board[][];
	static int visited[][];
	static Queue<Point> queue;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		board = new int[H][W];
		
		queue = new LinkedList<Point>();

		for (int i = 0; i < H; i++) {
			String input = br.readLine();
			for (int j = 0; j < W; j++) {
				if (input.charAt(j) == '.') {
					board[i][j] = 0;
				} else {
					board[i][j] = input.charAt(j) - '0';
					if (0<board[i][j]  && board[i][j]<9) {
						queue.offer(new Point(i, j));
					}
				}
			}
		}

		int answer = BFS();
		System.out.println(answer);

		br.close();

	}

	static int BFS() {
		int level = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean flag = false;
			visited  = new int[H][W];
			for (int s = 0; s < size; s++) {
				
				Point curPoint = queue.poll();
				int count = 0;
				for(int i=0;i<8;i++) {
					int nx = curPoint.x +dx[i];
					int ny = curPoint.y +dy[i];
					
					if(board[nx][ny]==0&& visited[nx][ny]==0) {
						count++;
					}
				}
				
				visited[curPoint.x][curPoint.y]=count;
				
				if(count>=board[curPoint.x][curPoint.y]) {
					board[curPoint.x][curPoint.y]=0;
					flag=true;
				}else {
					if (0<board[curPoint.x][curPoint.y] && board[curPoint.x][curPoint.y]<9) {
						queue.add(curPoint);
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
