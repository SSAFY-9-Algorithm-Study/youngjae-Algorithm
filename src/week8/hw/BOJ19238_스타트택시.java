package week8.hw;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19238_스타트택시 {

	static class Person implements Comparable<Person> {
		int startX;
		int startY;
		int endX;
		int endY;
		int length;

		public Person(int startX, int startY, int endX, int endY) {
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
		}

		@Override
		public int compareTo(Person o) {
			// 승객을 고를 때는 현재 위치에서 최단거리가 가장 짧은 승객을 고른다.
			// 그런 승객이 여러 명이면 그중 행 번호가 가장 작은 승객을,
			// 그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객을 고른다.

			if (this.length == o.length && this.startX == o.startX) {
				return this.startY - o.startY;
			}

			if (this.length == o.length) {
				return this.startX - o.startX;
			}

			return this.length - o.length;
		}

	}

	static class Taxi {
		int x;
		int y;
		int fuel;

		public Taxi(int x, int y, int fuel) {
			this.x = x;
			this.y = y;
			this.fuel = fuel;
		}

	}

	static class Point {

		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static int[][] board;
	static int M; // 승객 수
	static int fuel; // 연료
	static int N; // 배열 크기
	static int visited[][];
	static Taxi taxi;
	static Person[] persons;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		board = new int[N + 1][N + 1];
		visited = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());

		taxi = new Taxi(startX, startY, fuel);
		persons = new Person[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());

			board[startX][startY] = 2; // 사람이 있는 지점 2로 표시

			persons[i] = new Person(startX, startY, endX, endY);

		}

		int answer = 0;
		for (int i = 0; i < M; i++) {
			// 택시가 있는 지점부터 BFS시작
			ArrayList<Person> list = BFSFindPassengers(new Point(taxi.x, taxi.y));

			if (list.isEmpty()) {
				answer = -1;
				break;
			}
			Person person = sortPassenger(list);

			int length1 = person.length;

			int length2 = BFSGoEnd(person);


			taxi.fuel = taxi.fuel - length1 - length2;

			if (length1 < 0) {
				answer = -1;
				break;
			}

			if (length2 < 0) {
				answer = -1;
				break;
			}

			if (taxi.fuel < 0) {

				answer = -1;
				break;
			} else {
				taxi.fuel = taxi.fuel + length2 * 2;
				answer = taxi.fuel;
			}

		}

		System.out.println(answer);

		br.close();
	}

	static ArrayList<Person> BFSFindPassengers(Point start) {

		Queue<Point> queue = new LinkedList<Point>();
		ArrayList<Person> list = new ArrayList<>();
		queue.add(start);
		int level = 0;
		visited = new int[N + 1][N + 1];

		// 택시 위치에 승객이 있는경우
		if (board[start.x][start.y] == 2) {
			Person person = new Person(start.x, start.y, 0, 0);
			person.length = 0;
			list.add(person);
			return list;
		}

		// 승객 찾기
		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if (0 < nx && nx <= N && 0 < ny && ny <= N) {
						if (board[nx][ny] != 1 && visited[nx][ny] == 0) {
							visited[nx][ny] = 1;
							if (board[nx][ny] == 2) {

								Person person = new Person(nx, ny, 0, 0);  //도착지점은 0,0으로 설정
								person.length = level + 1;  //승객까지 가는거리 저장
								list.add(person);
							}
							queue.add(new Point(nx, ny));

						}
					}

				}

			}
			level++;

		}
		return list;
	}

	static Person sortPassenger(ArrayList<Person> list) {
		// 정렬로 최적의 승객 찾기
		Collections.sort(list);
		Person person = list.get(0);

		for (int i = 0; i < M; i++) {
			// 도착지점 설정
			if (persons[i].startX == person.startX && persons[i].startY == person.startY) {
				person.endX = persons[i].endX;
				person.endY = persons[i].endY;
				board[person.startX][person.startY] = 0;
				return person;
			}
		}

		return person;

	}

	static int BFSGoEnd(Person person) {

		visited = new int[N + 1][N + 1];

		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(person.startX, person.startY));
		int level = 0;
		visited = new int[N + 1][N + 1];

		// 승객을 도착지점으로 이동
		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point cur = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if (0 < nx && nx <= N && 0 < ny && ny <= N) {
						if (board[nx][ny] != 1 && visited[nx][ny] == 0) {
							visited[nx][ny] = 1;
							if (nx == person.endX && ny == person.endY) {
								taxi.x = person.endX;
								taxi.y = person.endY;
								return level + 1;  // 도착지점 까지 가는거리 리턴
							}
							queue.add(new Point(nx, ny));

						}
					}

				}

			}
			level++;

		}

		return -1;
	}

}
