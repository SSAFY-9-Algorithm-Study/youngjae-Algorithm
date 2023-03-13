package week6;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ21610 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int num;
	static int M;
	static int d[];
	static int s[];
	static int board[][];
	static int cloud[][];
	static int visited[][];
	static List<Point> list;

	static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		num = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[num][num];

		

		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < num; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		d = new int[M];
		s = new int[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			d[i] = Integer.parseInt(st.nextToken());
			s[i] = Integer.parseInt(st.nextToken());
		}

		cloud = new int[num + 1][num + 1];

		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(num - 2, 0));
		queue.add(new Point(num - 2, 1));
		queue.add(new Point(num - 1, 0));
		queue.add(new Point(num - 1, 1));

		for (int i = 0; i < M; i++) {
			visited = new int[num][num];
			int length = queue.size();
			for (int l = 0; l < length; l++) {
				Point cur = queue.poll();
				// 구름 이동
				cur.x = (num + (cur.x + (dx[d[i]] * s[i])% num)) % num;
				cur.y = (num + (cur.y + (dy[d[i]] * s[i])% num)) % num;
				board[cur.x][cur.y]++;
				visited[cur.x][cur.y]=1;
				queue.add(new Point(cur.x, cur.y));

			}
			
			
			
			for (int l = 0; l < length; l++) {
				// 물복사
				Point cur = queue.poll();
				for (int j = 2; j < 9; j = j + 2) {
					
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];

					if (0 <= nx && nx < num && 0 <= ny && ny < num && board[nx][ny] > 0) {
						board[cur.x][cur.y]++;

					}

				}

				
			}
			
			
			
			for(int row=0;row<num;row++) {
				for(int col=0;col<num;col++) {
					if(board[row][col]>=2&&visited[row][col]==0) {
						queue.add(new Point(row, col));
						board[row][col]-=2;
					}
						
				}
			}
			
			
		}
		
		int sum=0;
		for(int row=0;row<num;row++) {
			for(int col=0;col<num;col++) {
				sum+=board[row][col];
					
			}
		}

//		for (int i = 0; i < num; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}

		System.out.println(sum);
		br.close();

	}

	static void DFS(int direction, int cnt) {

	}

}
