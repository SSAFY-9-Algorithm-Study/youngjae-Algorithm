package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Point10026 {
	int x;
	int y;

	Point10026(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class BOJ10026 {

	public static int num;
	public static int[][] boardRGB;
	public static int[][] boardGB;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		boardRGB = new int[num][num];

		boardGB = new int[num][num];

		for (int i = 0; i < num; i++) {
			char charArr[] = br.readLine().toCharArray();
			for (int j = 0; j < charArr.length; j++) {
				if (charArr[j] == 'R') {
					boardRGB[i][j] = 1;
					boardGB[i][j] = 1;
				} else if (charArr[j] == 'G') {
					boardRGB[i][j] = 2;
					boardGB[i][j] = 1;
				} else if (charArr[j] == 'B') {
					boardRGB[i][j] = 3;
					boardGB[i][j] = 3;
				}

			}
		}

		int countRGB = 0;
		for (int i = 0; i < num; i++) {

			for (int j = 0; j < num; j++) {
				if (boardRGB[i][j] == 1) {
					boardRGB[i][j] = 0;
					countRGB++;
					DFSRGB(new Point10026(i, j), 1);
				} else if (boardRGB[i][j] == 2) {
					boardRGB[i][j] = 0;
					countRGB++;
					DFSRGB(new Point10026(i, j), 2);
				} else if (boardRGB[i][j] == 3) {
					boardRGB[i][j] = 0;
					countRGB++;
					DFSRGB(new Point10026(i, j), 3);
				}

			}

		}

		int countGB = 0;
		for (int i = 0; i < num; i++) {

			for (int j = 0; j < num; j++) {
				if (boardGB[i][j] == 1) {
					boardGB[i][j] = 0;
					countGB++;
					DFSGB(new Point10026(i, j), 1);
				} else if (boardGB[i][j] == 3) {
					boardGB[i][j] = 0;
					countGB++;
					DFSGB(new Point10026(i, j), 3);
				}

			}

		}
		System.out.println(countRGB + " " + countGB);

		br.close();

	}

	public static void DFSRGB(Point10026 start, int check) {

		for (int i = 0; i < 4; i++) {
			int nextX = start.x + dx[i];
			int nextY = start.y + dy[i];

			if (nextX >= 0 && nextY >= 0 && nextX < num && nextY < num) {
				if (boardRGB[nextX][nextY] == check) {
					boardRGB[nextX][nextY] = 0;
					DFSRGB(new Point10026(nextX, nextY), check);

				}
			}

		}

	}

	public static void DFSGB(Point10026 start, int check) {

		for (int i = 0; i < 4; i++) {
			int nextX = start.x + dx[i];
			int nextY = start.y + dy[i];

			if (nextX >= 0 && nextY >= 0 && nextX < num && nextY < num) {
				if (boardGB[nextX][nextY] == check) {
					boardGB[nextX][nextY] = 0;
					DFSGB(new Point10026(nextX, nextY), check);

				}
			}

		}

	}


}
