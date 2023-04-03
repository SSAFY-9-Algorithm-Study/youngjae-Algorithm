package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class BOJ20057_마법사_상어와_토네이도 {

	static class Sand {
		int x;
		int y;
		int direction;

		public Sand(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

	}

	// 우, 하, 좌 , 상
	static int[] turnDx = { 0, 1, 0, -1 };
	static int[] turnDy = { 1, 0, -1, 0 };

	// 좌, 하, 우, 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	static int[] percent = { 1, 1, 7, 7, 10, 10, 2, 2, 5 };

	static int[][] windDx = { { -1, 1, -1, 1, -1, 1, -2, 2, 0 }, // 좌
			{ -1, -1, 0, 0, 1, 1, 0, 0, 2 }, // 하
			{ -1, 1, -1, 1, -1, 1, -2, 2, 0 }, // 우
			{ 1, 1, 0, 0, -1, -1, 0, 0, -2 }, // 상
	};

	static int[][] windDy = { { 1, 1, 0, 0, -1, -1, 0, 0, -2 }, // 좌
			{ -1, 1, -1, 1, -1, 1, -2, 2, 0 }, // 하
			{ -1, -1, 0, 0, 1, 1, 0, 0, 2 }, // 우
			{ -1, 1, -1, 1, -1, 1, -2, 2, 0 }, // 상
	};

	static int[][] board;
	static int[][] turnPoint;
	static int[][] visited;
	static int N;
	static int startX;
	static int startY;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		turnPoint = new int[N][N];
		visited = new int[N][N];
		StringTokenizer st;

		startX = N / 2;
		startY = N / 2;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		makeTurnPoint(); // 꺾이는 지점 찾기
		/*
		 * 
		 * 꺾이는 지점 출력 for (int i = 0; i < N; i++) {
		 * System.out.println(Arrays.toString(turnPoint[i])); }
		 * 
		 */
		moveSand(); // 모래 이동 및 흩날림

		System.out.println(answer);

		br.close();
	}

	// 꺾이는 지점 찾기
	static void makeTurnPoint() {
		int direction = 0;
		visited[0][0] = 1;
		int x = 0;
		int y = 0;
		for (int i = 0; i < N * N; i++) {

			int nx = x + turnDx[direction];
			int ny = y + turnDy[direction];

			// 범위를 벗어나거나, 이미 방문한 곳이라면, 다음 방향으 꺾음
			if (!(0 <= nx && nx < N && 0 <= ny && ny < N) || (visited[nx][ny] == 1)) {

				turnPoint[x][y] = 1;
				direction = (direction + 1) % 4;
				nx = x + turnDx[direction];
				ny = y + turnDy[direction];

			}
			x = nx;
			y = ny;
			visited[nx][ny] = 1;

		}
		turnPoint[startX][startY] = 0;
	}

	// 모래 이동
	static void moveSand() {

		int x = startX;
		int y = startY;
		int direction = 0;
		for (int i = 0; i < N * N; i++) {
			// 모래 흩날리기
			splitSand(x, y, direction);

			if (turnPoint[x][y] == 1) {
				direction = (direction + 1) % 4;
			}

			int nx = x + dx[direction];
			int ny = y + dy[direction];
			x = nx;
			y = ny;

		}
	}

	// 모래 흩날리기
	static void splitSand(int x, int y, int direction) {


		int sand = board[x][y];
		for (int i = 0; i < 9; i++) {
			int splitX = x + windDx[direction][i];
			int splitY = y + windDy[direction][i];

			int split = (sand * percent[i]) / 100;
			// 흩날림
			board[x][y] -= split;
			
			
			// 격자 밖으로 나간 경우
			if (!(0 <= splitX && splitX < N && 0 <= splitY && splitY < N)) {
				answer += split;
				continue;
			}

			board[splitX][splitY] += split;

		}

		// 나머지 모두 이동
		int splitX = x + dx[direction];
		int splitY = y + dy[direction];

		// 격자 밖으로 나간 경우
		if (!(0 <= splitX && splitX < N && 0 <= splitY && splitY < N)) {
			answer += board[x][y];
			board[x][y] = 0;
			return;
		}

		board[splitX][splitY] += board[x][y];
		board[x][y] = 0;

	}

}
