package DFS_BFS;

import java.io.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class BOJ2178 {

	public static int N;
	public static int M;
	public static int board[][];
	public static int dis[][];
	public static int dx[] = { -1, 0, 1, 0 };
	public static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N + 1][M + 1];
		dis = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();

			for (int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(String.valueOf(line.charAt(j-1)));
			}
		}
		dis[1][1] = 1;
		System.out.println(BFS(new Point(1,1)));

		br.close();

	}

	public static int BFS(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);

		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point curPoint = q.poll();
				for (int i = 0; i < 4; i++) {
					int nextX = curPoint.x + dx[i];
					int nextY = curPoint.y + dy[i];

					if (nextX > 0 && nextY > 0 && nextX <= N && nextY <= M && board[nextX][nextY] == 1) {
						board[nextX][nextY] = 0;
						q.offer(new Point(nextX, nextY));
						dis[nextX][nextY] = dis[curPoint.x][curPoint.y] + 1;
						if(nextX==N && nextY==M) {
							return dis[nextX][nextY] ;
						}
					}
				}
			}

		}
		return 0;

	}

}
