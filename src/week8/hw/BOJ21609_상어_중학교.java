package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21609_상어_중학교 {

	// 중력 작용시 블록의 위치저장하기 위한 클래스
	static class Block {
		int x;
		int y;
		int data;

		public Block(int x, int y, int data) {
			this.x = x;
			this.y = y;
			this.data = data;
		}

	}

	// 블록 위치, 갯수,무지개 블록 갯수저장하기 위한 클래스
	static class Point implements Comparable<Point> {
		int x;
		int y;
		int blockCnt;
		int rainbowCnt;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (o.x == this.x && o.blockCnt == this.blockCnt && o.rainbowCnt == this.rainbowCnt) {
				return o.y - this.y;
			}

			if (o.blockCnt == this.blockCnt && o.rainbowCnt == this.rainbowCnt) {
				return o.x - this.x;
			}

			if (o.blockCnt == this.blockCnt) {
				return o.rainbowCnt - this.rainbowCnt;
			}

			return o.blockCnt - this.blockCnt;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", blockCnt=" + blockCnt + ", rainbowCnt=" + rainbowCnt + "]";
		}

	}

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int N; // 크기
	static int M; // 블록의 색상가지 수

	static int[][] board;
	static int[][] vistied;
	static ArrayList<Point> blockGroupList;
	static ArrayList<Point> zeroList;
	static int countRainbow; // 무지개 블록 수
	static int countBlock;
	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		vistied = new int[N][N];
		answer = 0;
		blockGroupList = new ArrayList<Point>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {

			// 블록 그룹 찾기
			blockGroupList = findBlockGroup();

			// 정렬
			Collections.sort(blockGroupList);

			if (blockGroupList.isEmpty())
				break;

			if (!blockGroupList.isEmpty()) {
				Point point = blockGroupList.get(0);

				// 점수 구하기
				answer += point.blockCnt * point.blockCnt;

				// 블록 지우기
				removeBlockGroup(point);
			}

			// 중력
			gravity();
			// 90 반시계 회전
			rotate();
			// 중력
			gravity();
		}

		System.out.println(answer);

		br.close();

	}

	static void removeBlockGroup(Point point) {

		vistied = new int[N][N];
		vistied[point.x][point.y] = 1;
		DFS(point, board[point.x][point.y]);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (vistied[i][j] == 1) {
					board[i][j] = Integer.MIN_VALUE;
				}

			}
		}
	}

	static ArrayList<Point> findBlockGroup() {
		ArrayList<Point> list = new ArrayList<Point>();
		vistied = new int[N][N];
		// 블록의 행이 가장 큰 것 -> 열이 가장 큰 것을 찾기 때문에 오른쪽 아래부터 탐
		for (int i = N - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {

				if (board[i][j] > 0 && vistied[i][j] == 0) {

					zeroList = new ArrayList<Point>();
					countRainbow = 0;
					countBlock = 1;
					vistied[i][j] = 1;
					Point point = new Point(i, j);
					DFS(point, board[i][j]);

					point.blockCnt = countBlock;
					point.rainbowCnt = countRainbow;

//					System.out.println(point);
//					for (int k = 0; k < N; k++) {
//						System.out.println(Arrays.toString(vistied[k]));
//					}
//					System.out.println(zeroList);
//					System.out.println();

					// 무지개 방문 안했다고 돌려놓기
					for (Point zero : zeroList) {
						vistied[zero.x][zero.y] = 0;
					}

					// 그룹에 속한 블록의 개수는 2보다 크거나 같아야한다.
					if (point.blockCnt >= 2) {
						list.add(point);
					}
				}

			}
		}
//		System.out.println("=============끝");
		return list;
	}

	static void DFS(Point start, int num) {

		for (int i = 0; i < 4; i++) {
			int nx = start.x + dx[i];
			int ny = start.y + dy[i];

			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (vistied[nx][ny] == 0 && (board[nx][ny] == num || board[nx][ny] == 0)) {

					// 무지개인 경우, 무지개 갯수 카운트
					if (board[nx][ny] == 0) {
						countRainbow++;
						zeroList.add(new Point(nx, ny));
					}
					// 블록갯수 카운트
					countBlock++;
					vistied[nx][ny] = 1;
					DFS(new Point(nx, ny), num);

				}
			}
		}

	}

	static void gravity() {

		int[][] tmpBoard = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				tmpBoard[i][j] = Integer.MIN_VALUE;

			}
		}

		for (int j = 0; j < N; j++) {
			Queue<Block> queue = new LinkedList();
			for (int i = N - 1; i >= 0; i--) {
				if (board[i][j] != Integer.MIN_VALUE)
					queue.add(new Block(i, j, board[i][j]));
			}

			for (int i = N - 1; i >= 0; i--) {

				if (!queue.isEmpty()) {
					Block block = queue.poll();
					if (block.data != -1) {
						tmpBoard[i][j] = block.data;
					} else {
						tmpBoard[block.x][block.y] = block.data;
						i = block.x;
					}
				}

			}

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				board[i][j] = tmpBoard[i][j];

			}
		}

	}

	static void rotate() {
		int[][] tmpBoard = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				tmpBoard[N - 1 - j][i] = board[i][j];

			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				board[i][j] = tmpBoard[i][j];

			}
		}

	}

}
