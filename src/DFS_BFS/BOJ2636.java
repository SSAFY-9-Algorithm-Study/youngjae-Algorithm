package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


/*
class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

 */

public class BOJ2636 {

	public static int N;
	public static int M;
	public static int[][] board;
	public static int[][] visited;
	public static int[][] bound;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer=0;
		ArrayList<Integer> count=new ArrayList<Integer>();
		

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			int cnt=0;
			visited = new int[N][M];
			bound = new int[N][M];
			
			visited[0][0] = 1;
			DFS(new Point(0, 0));

			for (int i = 0; i < N; i++) {

				for (int j = 0; j < M; j++) {
					if (bound[i][j] == 1) {
						board[i][j] = 0;
						cnt++;
					}
						
				}
			}
			count.add(cnt);
			
			boolean flag=true;
			for (int i = 0; i < N; i++) {

				for (int j = 0; j < M; j++) {
					if (bound[i][j] == 1) {
						flag=false;
						break;
					}	
				}
				
			}
			
			if(flag)
				break;
			
			answer++;

		}
		System.out.println(answer);
		System.out.println(count.get(answer-1));
		
		
		br.close();
	}

	public static void DFS(Point start) {

		if (board[start.x][start.y] == 1) {
			bound[start.x][start.y] = 1;
			return;
		} else {
			for (int i = 0; i < 4; i++) {

				int nextX = start.x + dx[i];
				int nextY = start.y + dy[i];

				if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
					if (visited[nextX][nextY] == 0) {
						visited[nextX][nextY] = 1;
						DFS(new Point(nextX, nextY));
					}
				}

			}
		}

	}

}
