package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class BOJ15686 {

	public static int N;
	public static int M; // 치킨집 중에서 최대 M개
	public static int answer = Integer.MAX_VALUE;
	public static List<Point> houseList;
	public static List<Point> storeList;
	public static List<Integer> distList;
	public static int board[][];
	public static int combi[];
	
	public static int visited[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		combi = new int[M];

		board = new int[N + 1][N + 1];
		houseList = new ArrayList<Point>();
		storeList = new ArrayList<Point>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

				if (board[i][j] == 1) {
					houseList.add(new Point(i, j));
				} else if (board[i][j] == 2) {
					storeList.add(new Point(i, j));
				}

			}
		}
		visited = new int[storeList.size()];

		DFS(0, 0);

		System.out.println(answer);
		br.close();
	}

	public static void DFS(int levle, int start) {
		if (levle == M) {
//			System.out.println(Arrays.toString(combi));
			int distSum=0;
			for (Point h : houseList) {
				int distance = Integer.MAX_VALUE;
				for (int i = 0; i < M; i++) {
					distance = Math.min(distance,
							Math.abs(storeList.get(combi[i]).x - h.x) + Math.abs(storeList.get(combi[i]).y - h.y));
				}
				distSum += distance;
			}
			answer=Math.min(answer, distSum);

		} else {
			for (int i = start; i < storeList.size(); i++) {
				if (visited[i] == 0) {
					visited[i] = 1;
					combi[levle] = i;
					DFS(levle + 1, i + 1);
					visited[i] = 0;
				}
			}
		}
	}

}
