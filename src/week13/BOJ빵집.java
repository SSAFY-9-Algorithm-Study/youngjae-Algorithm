package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 결국에는 제일 위의 가스관을 제일 위로 올려야 덜 꼬이면서 연결된다. 

public class BOJ빵집 {

	static int dx[] = { -1, 0, 1 };  // 대각 오른쪽 위, 오른쪽, 대각 오른쪽 아래  
	static int dy[] = { 1, 1, 1 };

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int row;
	static int col;

	static int board[][];
	static int arr[];

	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		board = new int[row][col];
		arr = new int[row+1]; // 끝에 도달했는지 체크하는 용도 

		for (int i = 0; i < row; i++) {
			String str = br.readLine();
			for (int j = 0; j < col; j++) {
				if (str.charAt(j) == 'x') {
					board[i][j] = 1;
				}
			}
		}


		answer = 0;
		for (int i = 0; i < row; i++) {
			DFS(new Point(i, 0), i + 1);
		}

		System.out.println(answer);

		br.close();
	}

	//x는 디버깅할때, 몇번 파이프였는지 확인차 넣은것 
	static void DFS(Point start, int x) {

		if (arr[x] == 0) {  // 끝에 도달하지 않았다면, 
			board[start.x][start.y] = x;
			 // 대각 오른쪽 위, 오른쪽, 대각 오른쪽 아래 순서이기 때문에 대각 오른쪽 위부터 탐색 
			for (int i = 0; i < 3; i++) { 
				int nx = start.x + dx[i];
				int ny = start.y + dy[i];

				if (0 <= nx && nx < row && 0 <= ny && ny < col) {
					if (board[nx][ny] == 0) {
					
						if (ny == col - 1) { // 끝에 도착할 경우 
							arr[x] = 1;
							answer++;
							return;
						} else {
							DFS(new Point(nx, ny), x); //오른쪽 3뱡향중 한곳으로 한칸 이동 
						}

					}
				}
			}
		}

	}

}
