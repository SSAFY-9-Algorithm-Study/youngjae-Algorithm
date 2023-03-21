package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.management.Query;


public class BOJ17142 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static List<Point> virusList; //

	static int row;
	static int col;

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static int board[][];
	static int visited[][];  //바이러스위치 저장할 리스트
	static int distance[][]; // 바이러스가 이동한 거리 저장하는 배열

	static int combi[];
	static int visitedCombi[];

	static int n;
	static int r;

	static Queue<Point> queue;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		row = col = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		board = new int[row][col];
		visited = new int[row][col];
		distance = new int[row][col];

		virusList = new ArrayList<Point>();

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 2) {
					//바이러스위치 저장
					virusList.add(new Point(i, j));
				}
			}
		}

		combi = new int[r];
		n = virusList.size();
		visitedCombi = new int[n + 1];

		DFS(0, 0);
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(answer);
		br.close();
	}

	// 조합 구하기
	static void DFS(int level, int start) {
		if (level == r) {
			visited = new int[row][col];
			distance = new int[row][col];
			queue = new LinkedList<Point>();
			for (int x : combi) {
				queue.add(virusList.get(x));
				visited[virusList.get(x).x][virusList.get(x).y] = 1;
			}
			BFS();

			boolean flag = true;
			int time = 0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (board[i][j] == 2 && distance[i][j] == 0) {// 활성 바이러스
						
						time = Math.max(distance[i][j], time);
					} else if (board[i][j] == -1 && (board[i][j] == 2 && distance[i][j] != 0)) { // 벽, 비활성바이러스
						continue;
					} else if (distance[i][j] == 0 && board[i][j] == 0) {// 방문 못한 경우
						flag = false;
						return;
					} else if (distance[i][j] != 0 && board[i][j] == 0) {// 감연된 경우
						time = Math.max(distance[i][j], time);
					}
				}
			}

			answer = Math.min(answer, time);

		} else {
			for (int i = start; i < n; i++) {
				if (visitedCombi[i] == 0) {
					visitedCombi[i] = 1;
					combi[level] = i;
					DFS(level + 1, i + 1);
					visitedCombi[i] = 0;
				}
			}
		}
	}

	//바이러스 퍼트리기
	static void BFS() {
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];

					if (0 <= nx && nx < row && 0 <= ny && ny < col && board[nx][ny] != 1) {
						if (visited[nx][ny] == 0) {
							visited[nx][ny] = 1;

							distance[nx][ny] = distance[cur.x][cur.y] + 1;
							queue.add(new Point(nx, ny));
						}
					}

				}

			}
		}

	}

}
