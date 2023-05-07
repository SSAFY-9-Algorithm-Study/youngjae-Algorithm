package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.security.auth.callback.ConfirmationCallback;

public class BOJ11559_뿌요뿌요 {

	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int answer = 0; //연쇄가 몇 번 연속으로 일어날지 횟수 저장하는 변수 

	static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;

		}

		//
		@Override
		public int compareTo(Point o) {
			if (this.y == o.y) {
				return o.x - this.x;
			}
			return this.y - o.y;
		}

	}

	static int row = 12;
	static int col = 6;
	static int arr[][] = new int[row][col]; // 뿌요들의 정보 저장하는 배열 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = row - 1; i >= 0; i--) {
			String str = br.readLine().toString();
			for (int j = 0; j < col; j++) {
				switch (str.charAt(j)) {
				case 'R': {
					arr[i][j] = 1;
					break;
				}
				case 'G': {
					arr[i][j] = 2;
					break;
				}
				case 'B': {
					arr[i][j] = 3;
					break;
				}
				case 'Y': {
					arr[i][j] = 4;
					break;
				}
				case 'P': {
					arr[i][j] = 5;
					break;
				}
				default: {
					arr[i][j] = 0;
					break;
				}
				}

			}
		}

		//현재 턴에서  뿌요를 터트렸는지 체크하는 변수 
		boolean flag = true;
		// 뿌요를 못터트릴때 까지 반복 
		while (flag) {
			flag = false;

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (arr[i][j] != 0) {
						// 4개가 연속으로 있는지 확인 
						Point point = BFS(new Point(i, j));
						if (point == null) {
							continue;
						} else {
							flag = true; //현재 턴에서 뿌요를 터트렸다 

						}
					}
				}
			}
			if (flag == true) {
				answer++;
			}
			// 중력 작용
			for (int k = 0; k < col; k++) {
				gravity(new Point(0, k));

			}
		}


		System.out.println(answer);
		br.close();
	}

	// 제일 왼쪽 아래의 뿌요를 리턴
	static Point BFS(Point start) {
		int visited[][] = new int[row][col];
		visited[start.x][start.y] = 1;

		Queue<Point> queue = new LinkedList<Point>();
		queue.add(start);

		// 터트릴수 있는 뿌요를 저장하는 리스트 
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(start);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if (0 <= nx && nx < row && 0 <= ny && ny < col) {
						// 같은 뿌요라면
						if (visited[nx][ny] == 0 && arr[start.x][start.y] == arr[nx][ny]) {
							visited[nx][ny] = 1;
							queue.add(new Point(nx, ny));
							list.add(new Point(nx, ny));
						}
					}
				}
			}
		}

		
		
		boolean isPuyo = false;
		if (list.size() >= 4) {// 연속된 뿌요가 4개 이상이면
			isPuyo = true;
			for (Point p : list) {
				arr[p.x][p.y] = 0;  // 뿌요를 터트림 
			}
		}

		if (isPuyo)
			return list.get(0);
		else
			return null;
	}

	// 중력 작용
	static void gravity(Point start) {
		Queue<Integer> queue = new LinkedList<Integer>();

		// 현재 세로줄에 있는모든 뿌요를 큐에 담는다. 
		for (int i = 0; i < row; i++) {
			if (arr[i][start.y] != 0) {
				queue.add(arr[i][start.y]);
				arr[i][start.y] = 0;
			}
		}

		int i = 0;
		while (!queue.isEmpty()) { // 바닥부터 차례로 쌓아올린다. 
			arr[i][start.y] = queue.poll();
			i++;
		}

	}

}
