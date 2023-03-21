package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17144 {

	static int row;
	static int col;
	static int T;
	static int board[][];
	static int calculate[][]; // 값 계산하는 배열
	static int airCleanerUp = -1; //공기청정기 윗 대가리 위치
	static int airCleanerDown = -1; //공기청정기 아랫 대가리 위치

	// 시계 방향
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	// 반시계 방향
	static int rdx[] = { -1, 0, 1, 0 };
	static int rdy[] = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		board = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if (board[i][j] == -1 && airCleanerUp != -1) {//공기청정기 아랫 대가리 위치
					airCleanerDown = i;
				} else if (board[i][j] == -1 && airCleanerUp == -1) {//공기청정기 윗 대가리 위치
					airCleanerUp = i;
				}
			}
		}

		for (int tc = 0; tc < T; tc++) {
			calcul();//먼지 확산
			rotateReverse(airCleanerUp, 0); // 윗 대가리쪽 정화
			rotate(airCleanerDown, 0); // 아랫 대가리쪽 정화
		}


		int sum = 2; // 공기청정기 값

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sum += board[i][j];
			}
		}
		System.out.println(sum);
		br.close();
	}


	static void rotate(int startX, int startY) {
		int count = 0;
		int x = startX;
		int y = startY;
		while (true) {

			int nx = x + rdx[count%4];
			int ny = y + rdy[count%4];

			if(nx==startX && ny == startY) {// 다음 위치가 처음 위치에 도달하면 종료
				break;
			}
			
			if (airCleanerDown <= nx && nx < row && 0 <= ny && ny < col) { 
				int tmp = board[x][y];
				board[x][y] = board[nx][ny];
				board[nx][ny] = tmp;

				x = nx;
				y = ny;
			} else { 
				// 이동할 수 없다는것은 현재 끝에 도달한것이기 때문에
				//대각선 끝지점에 도착하면 방향을 바꿘준다.
				count++;
			}

		}
		board[airCleanerDown][1] = 0;
		board[airCleanerDown][0] = -1;
	}

	static void rotateReverse(int startX, int startY) {
		int count = 0;
		int x = startX;
		int y = startY;
		while (count < 4) {

			int nx = x + dx[count];
			int ny = y + dy[count];

			if(nx==startX && ny == startY) {// 다음 위치가 처음 위치에 도달하면 종료
				break;
			}
			
			if (0 <= nx && nx <= airCleanerUp && 0 <= ny && ny < col) {
				int tmp = board[x][y];
				board[x][y] = board[nx][ny];
				board[nx][ny] = tmp;

				x = nx;
				y = ny;
			} else {
				// 이동할 수 없다는것은 현재 끝에 도달한것이기 때문에
				//대각선 끝지점에 도착하면 방향을 바꿘준다.
				count++;
			}

		}
		board[airCleanerUp][1] = 0;
		board[airCleanerUp][0] = -1;
	}

	static void calcul() {
		// 변화될 값을 미리 저장
		calculate = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 0 || board[i][j] == -1)
					continue;

				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (0 <= nx && nx < row && 0 <= ny && ny < col && board[nx][ny] != -1) {
						
						calculate[nx][ny] += (board[i][j] / 5); // 주위 먼지 쌓임
						calculate[i][j] -= (board[i][j] / 5);  // 현재위치의 먼지 적어짐
					}
				}
			}
		}

		for (int i = 0; i < row; i++) { // calculate값을 참고해서 원래 배열값 갱신
			for (int j = 0; j < col; j++) {
				board[i][j] += calculate[i][j];
			}
		}

	}

}
