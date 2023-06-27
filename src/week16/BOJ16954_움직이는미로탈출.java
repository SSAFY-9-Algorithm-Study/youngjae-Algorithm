package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16954_움직이는미로탈출 {

	static class Wall {
		int x;
		int y;

		public Wall(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1, 0 };

	static List<Wall> list;

	static int board[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[8][8];
		list = new ArrayList<Wall>();
		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				if (str.charAt(j) == '#') {
					list.add(new Wall(i, j));
				}
			}
		}

		if (BFS()) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

		br.close();

	}

	static boolean BFS() {
		Queue<Position> q = new LinkedList<Position>();
		q.add(new Position(7, 0));
		int level = 0;

		while (!q.isEmpty()) {

			int size = q.size();
			int visited[][] = new int[8][8];
			List<Wall> nextList = new ArrayList<Wall>();

			for (Wall w : list) {
				if (w.x != 7) {
					nextList.add(new Wall(w.x + 1, w.y));
				}
			}

			for (int s = 0; s < size; s++) {

				Position cur = q.poll();
				for (int i = 0; i < 9; i++) {
					boolean subFlag = true;
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if (0 <= nx && nx < 8 && 0 <= ny && ny < 8 && visited[nx][ny] == 0) {
						visited[nx][ny] = 1;
						for (Wall w : list) {
							if (w.x == nx && w.y == ny) {
								subFlag = false;
								break;
							}
						}
						if (subFlag) {
							for (Wall w : nextList) {
								if (w.x == nx && w.y == ny) {
									subFlag = false;
									break;
								}
							}
						}

						if (subFlag) {
							q.add(new Position(nx, ny));
						}
					}

				}

			}
			level++;
			list = nextList;
//			System.out.println("level: "+level);
			if (level == 9) {
				break;
			}
		}
		
//		System.out.println("level: "+level);

		if (q.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

}
