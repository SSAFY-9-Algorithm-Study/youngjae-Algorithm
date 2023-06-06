package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 미생물격리 {

	static int dx[] = { 0, -1, 1, 0, 0 };
	static int dy[] = { 0, 0, 0, -1, 1 };

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int num;
		int dir;

		public Point(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public int compareTo(Point o) {
			return o.num - this.num;
		}

	}

	static int N;
	static int M;
	static int K;
	static List<Point> list;

	static ArrayList<Point> boardLists[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<Point>();
			boardLists = new ArrayList[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					boardLists[i][j] = new ArrayList<Point>();
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				list.add(new Point(x, y, num, dir));
			}

			solution();

			int answer = 0;

			for (Point p : list) {
				answer += p.num;
			}
			
			System.out.println(answer);

		}

		br.close();

	}

	static void solution() {
		for (int k = 0; k < M; k++) {
			for (Point p : list) {

				int nx = p.x + dx[p.dir];
				int ny = p.y + dy[p.dir];

				if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
					p.num = p.num / 2;
					if (p.dir % 2 == 0) {
						p.dir = p.dir - 1;
					} else {
						p.dir = p.dir + 1;
					}
				}

				boardLists[nx][ny].add(p);

			}

			list= new ArrayList<Point>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (boardLists[i][j].size() > 1) {
						Collections.sort(boardLists[i][j]);
						Point king = boardLists[i][j].get(0);
						for (int l = 1; l < boardLists[i][j].size(); l++) {
							king.num = king.num + boardLists[i][j].get(l).num;
						}
						list.add(king);
						boardLists[i][j]= new ArrayList<Point>();
					} else if (boardLists[i][j].size() == 1) {
						list.add(boardLists[i][j].get(0));
						boardLists[i][j]= new ArrayList<Point>();
					}
				}

			}

		}
	}

}
