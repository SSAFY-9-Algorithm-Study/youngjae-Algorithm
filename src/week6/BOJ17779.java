package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ17779 {

	static int num;
	static int total;
	static int answer;
	static int PepleBoard[][];
	static int RegionBoard[][];
	static int sum[];

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		total = 0;
		answer = Integer.MAX_VALUE;
		PepleBoard = new int[num + 1][num + 1];
		RegionBoard = new int[num + 1][num + 1];
		sum = new int[6];
		StringTokenizer st;
		for (int i = 1; i <= num; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= num; j++) {
				PepleBoard[i][j] = Integer.parseInt(st.nextToken());
				total += PepleBoard[i][j];
			}
		}
		// 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)

		for (int x = 1; x <= num; x++) {
			for (int y = 1; y <= num; y++) {
				for (int d1 = 1; d1 <= num; d1++) {
					for (int d2 = 1; d2 <= num; d2++) {
						RegionBoard = new int[num + 1][num + 1];
						if ((x + d1 + d2) <= num && 1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= num) {
							sum = new int[6];

							RegionBoard[x][y] = 5; // 제일 위쪽

							for (int k = 0; k < x + 1; k++) {
								RegionBoard[k][y] = 1;
							}

							for (int d = 0; d <= d1; d++) {
								RegionBoard[x + d][y - d] = 5; // 왼
							}

							RegionBoard[x + d1][y - d1] = 5; // 제일 왼쪽

							for (int k = 0; k < y - d1; k++) {
								RegionBoard[x + d1][k] = 3;
							}

							for (int d = 0; d <= d2; d++) {
								RegionBoard[x + d][y + d] = 5; // 오
							}

							RegionBoard[x + d2][y + d2] = 5; // 제일 오른쪽

							for (int k = y + d2 + 1; k <= num; k++) {
								RegionBoard[x + d2][k] = 2;
							}

							for (int d = 0; d <= d2; d++) {
								RegionBoard[x + d1 + d][y - d1 + d] = 5; // 오
							}

							for (int d = 0; d <= d1; d++) {
								RegionBoard[x + d2 + d][y + d2 - d] = 5; // 오
							}

							RegionBoard[x + d1 + d2][y + d2 - d1] = 5; // 제일 아래쪽

							for (int k = x + d1 + d2 + 1; k <= num; k++) {
								RegionBoard[k][y + d2 - d1] = 4; // 아래
							}

							RegionBoard[1][1] = 1;
							RegionBoard[1][num] = 2;
							RegionBoard[num][1] = 3;
							RegionBoard[num][num] = 4;
							DFS(1, 1, 1);
							DFS(1, num, 2);
							DFS(num, 1, 3);
							DFS(num, num, 4);

							for (int a = 1; a <= num; a++) {
								for (int b = 1; b <= num; b++) {
									if (RegionBoard[a][b] == 1) {
										sum[1] += PepleBoard[a][b];
									} else if (RegionBoard[a][b] == 2) {
										sum[2] += PepleBoard[a][b];
									} else if (RegionBoard[a][b] == 3) {
										sum[3] += PepleBoard[a][b];
									} else if (RegionBoard[a][b] == 4) {
										sum[4] += PepleBoard[a][b];
									} else {
										sum[5] += PepleBoard[a][b];
									}
								}
							}

							Arrays.sort(sum);


							answer = Math.min(answer, sum[5] - sum[1]);



						}

					}
				}
			}
		}
		System.out.println(answer);

		br.close();

	}

	static void DFS(int startX, int startY, int check) {

		for (int i = 0; i < 4; i++) {
			int nx = startX + dx[i];
			int ny = startY + dy[i];

			if (0 < nx && nx <= num && 0 < ny && ny <= num) {
				if (RegionBoard[nx][ny] == 0) {
					RegionBoard[nx][ny] = check;
					DFS(nx, ny, check);
				}
			}
		}
	}

}
