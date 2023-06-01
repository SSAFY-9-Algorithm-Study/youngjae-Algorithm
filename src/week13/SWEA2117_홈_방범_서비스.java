package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA2117_홈_방범_서비스 {

	static class Home {
		int x;
		int y;

		public Home(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static List<Home> list; // 집의 위치를 담는 리스트 
	static int answer; // 집의 수 
	static int N; // 배열 가로,세로 크기  
	static int M; // 한 집에서 낼수 있는 비용 
	static int board[][]; 

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			list = new ArrayList<Home>();
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 1) {
						list.add(new Home(i, j));
					}
				}
			}

			solution();

			System.out.printf("#%d %d\n", tc, answer);
		}

		br.close();
	}

	static void solution() {

		for (int k = 2*N ; k >= 0; k--) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					int count = 0;

					for (Home home : list) {
						if (getLength(home, i, j) < k) { // 거리가 k-1이내면 집갯수 증가 
							count++;
						}
					}

					if ((count * M) >= ((k * k) + ((k - 1) * (k - 1)))) {
//						System.out.println("K="+k);
//						System.out.println("count="+count);
						answer = Math.max(answer, count);

					}
				}

			}
		}

	}

	// 집과 중심거리 구하는 함수
	static int getLength(Home home, int centerX, int centerY) {

		int distX = Math.abs(home.x - centerX);
		int distY = Math.abs(home.y - centerY);
		int dist = distX + distY;
		return dist;
	}

}
