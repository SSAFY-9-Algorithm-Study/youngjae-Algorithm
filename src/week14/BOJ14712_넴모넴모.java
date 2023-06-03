package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14712_넴모넴모 {

	static int dx[] = { 0, 1, 1 };
	static int dy[] = { 1, 1, 0 };

	static int R;
	static int C;
	static int total;// 전체 경우의수, 2^R*C
	static int squareCnt; // 네모가 되는 경우의 수 세는 변수
	static int board[][];
	static int visited[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		visited = new int[R][C];

		total = (int) Math.pow(2, R * C);

//		System.out.println(total);
		DFS(0);
		System.out.println(total - squareCnt);
		br.close();
	}

	static void DFS(int index) {
		if (index == R * C) {

			boolean flag = false;
			for (int i = 0; i < R - 1; i++) {
				for (int j = 0; j < C - 1; j++) {
					if(board[i][j]==1) {
						int cnt = 0;
						for (int k = 0; k < 3; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];

							if (board[nx][ny] == 1) {
								cnt++;
							}

						}
						if(cnt==3) {
//							for (int s = 0; s < R; s++) {
//								System.out.println(Arrays.toString(board[s]));
//							}
//							System.out.println();
							squareCnt++;
							return;
						}
					}
					
				}
			}

			
			return;
		}
		int row = index / C;
		int col = index % C;
		board[row][col] = 1;
		DFS(index + 1);
		board[row][col] = 0;
		DFS(index + 1);
	}

}
