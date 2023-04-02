package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ShardingKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ19236_청소년상어 {

	static class Fish {
		int x;
		int y;
		int direction;
		int number;
		boolean isShark = false;

		public Fish(int x, int y, int direction, int number) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.number = number;
		}

		@Override
		public String toString() {
			return "Fish [x=" + x + ", y=" + y + ", direction=" + direction + ", number=" + number + ", isShark="
					+ isShark + "]";
		}

	}

	static Map<Integer, Fish> map = new TreeMap<Integer, Fish>();

	static int N = 4;
	static int numBoard[][] = new int[N][N];
	static int dirBoard[][] = new int[N][N];

	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗ (반시계 방향)
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static int sum = Integer.MIN_VALUE;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Fish shark = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				numBoard[i][j] = Integer.parseInt(st.nextToken());
				dirBoard[i][j] = Integer.parseInt(st.nextToken()) - 1;
				Fish fish = new Fish(i, j, dirBoard[i][j], numBoard[i][j]);

				if (i == 0 && j == 0) {

					fish.isShark = true;
					shark = fish;
					map.put(0, fish);

					// 먹음 처리
					numBoard[i][j] = 0;
					map.put(numBoard[i][j], null);
					continue;
				}

				map.put(numBoard[i][j], fish);
			}
		}

		DFS(shark, shark.number);

		System.out.println(answer);

		br.close();
	}

	static void DFS(Fish shark, int sumEat) {

		// 물고기 이동
		moveFish(shark);

		Map<Integer, Fish> tmpMap = new TreeMap<Integer, Fish>();

		int tmpNumBoard[][] = new int[N][N];

		// 먹기전 상태 임시저
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmpNumBoard[i][j] = numBoard[i][j];
			}
		}
		for (int key : map.keySet()) {
			tmpMap.put(key, map.get(key));
		}

		// 상어 이동가능한 곳 체크
		ArrayList<Fish> moveList = checkMove(shark);

		// 이동할 곳이 없는 경우
		if (moveList.size() == 0) {
			System.out.println("이동할곳이 없다");
			System.out.println(sumEat);
			answer = Math.max(answer, sumEat);
			return;
		}

		System.out.println(sumEat);
		System.out.println(moveList);

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(numBoard[i]));
//		}

		System.out.println();

		// 상어 식사
		for (Fish fish : moveList) {

			// 물고기 먹기
			map.put(numBoard[fish.x][fish.y], null);
			numBoard[fish.x][fish.y] = 0;
			fish.isShark = true;

			System.out.println(moveList);

			System.out.println(fish.number + "먹음");
			System.out.println(fish);
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(numBoard[i]));
			}

			for (int i = 1; i <= N * N; i++) {

				System.out.println(i + ": " + map.get(i));
			}
			System.out.println();
			DFS(fish, sumEat + fish.number);

//			// 먹은 물고기 원상복구
//			numBoard[fish.x][fish.y] = fish.number;
			fish.isShark = false;
//			map.put(numBoard[fish.x][fish.y], fish);

			// 먹은 물고기 원상복구
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					numBoard[i][j] = tmpNumBoard[i][j];
				}
			}
			for (int key : tmpMap.keySet()) {
				map.put(key, tmpMap.get(key));
			}

		}

	}

	static ArrayList<Fish> checkMove(Fish shark) {
		ArrayList<Fish> list = new ArrayList<Fish>();

		int x = shark.x;
		int y = shark.y;
		while (true) {
			int nx = dx[shark.direction] + x;
			int ny = dy[shark.direction] + y;

			// 이동범위 확인, 끝에 도달하면 끝
			if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
				break;
			}

			// 물고기가 있으면 먹기가능
			if (map.get(numBoard[nx][ny]) != null && numBoard[nx][ny] != 0 && !map.get(numBoard[nx][ny]).isShark ) {
				list.add(map.get(numBoard[nx][ny]));
			}

			// 상어 위치 이동
			x = nx;
			y = ny;
		}

		return list;
	}

	static void moveFish(Fish shark) {

		for (int i = 1; i <= N * N; i++) {
			Fish fish = map.get(i);
			if (fish == null)
				continue;
			if (fish.isShark == true)
				continue;

			for (int d = 0; d < 8; d++) {
				// 45도씩 반시계 방향으로 회전
				int nx = fish.x + dx[(fish.direction + d) % 8];
				int ny = fish.y + dy[(fish.direction + d) % 8];

				// 범위 확인,이동 불가능하면 다음 방향
				if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
					continue;
				}
				// 상어있는지 확인 , 상어 있으면 다음 방향
				if (shark.x == nx && shark.y == ny) {
					continue;
				}

				// 물고기 이동
				System.out.println(numBoard[nx][ny] + " < > " + fish.number);

				if (map.get(numBoard[nx][ny]) == null) {
					map.put(numBoard[fish.x][fish.y],
							new Fish(nx, nx, (fish.direction + d) % 8, numBoard[fish.x][fish.y]));

					int tmp = numBoard[fish.x][fish.y];
					numBoard[fish.x][fish.y] = numBoard[nx][ny];
					numBoard[nx][ny] = tmp;
					break;
				}

				map.put(numBoard[nx][ny],
						new Fish(fish.x, fish.y, map.get(numBoard[nx][ny]).direction, numBoard[nx][ny]));
				map.put(numBoard[fish.x][fish.y], new Fish(nx, ny, (fish.direction + d) % 8, numBoard[fish.x][fish.y]));

				int tmp = numBoard[fish.x][fish.y];
				numBoard[fish.x][fish.y] = numBoard[nx][ny];
				numBoard[nx][ny] = tmp;

				break;
			}

		}
	}

}
