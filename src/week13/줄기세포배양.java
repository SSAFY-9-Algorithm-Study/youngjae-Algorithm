package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 줄기세포배양 {

	static class Cell implements Comparable<Cell> {

		public Cell(int state, int num, int x, int y, int createdTime) {
			super();
			this.state = state;
			this.num = num;
			this.x = x;
			this.y = y;
			this.createdTime = createdTime;
		}

		int state; // 비활성:0, 활성:1
		int num; // 생명력 수치
		int x; // x 좌표
		int y; // y 좌표  
		int createdTime; // 세포가 생성된 시간

		@Override
		public int compareTo(Cell o) {

			// 두 개 이상의 줄기 세포가 하나의 그리드 셀에 동시 번식하려고 하는 
			// 경우 생명력 수치가 높은 줄기 세포가 해당 그리드 셀을 혼자서 차지하게 된다.
			
			// 수치가 높은 세포가 먼저 점령하고 있으면 낮은 수치의 세포는 점령 못함 
			
			return o.num - this.num; //생명력 수치에 따라 내림차순 정렬 
		}
	}

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static int board[][];
	// 세로 크기 N, 가로 크기 M, 배양 시간 K
	static int N;
	static int M;
	static int K;
	static PriorityQueue<Cell> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			queue = new PriorityQueue<Cell>();
			
			// 결국에는 세포는 위, 아래, 왼,오 각각 K+1보다 멀리 퍼지지 못함 
			board = new int[2 * K + 2 + N][2 * K + 2 + M];  
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					board[K + 1 + i][K + 1 + j] = Integer.parseInt(st.nextToken());
					if (board[K + 1 + i][K + 1 + j] > 0) {
						queue.add(new Cell(0, board[K + 1 + i][K + 1 + j], K + 1 + i, K + 1 + j, 0));
					}
				}
			}

			int answer = BFS();
			System.out.printf("#%d %d\n", tc, answer);

		}

		br.close();
	}

	static int BFS() {
		int level = 1; // 총 배양시간 

		while (level != K + 1) { 

			int size = queue.size();
			ArrayList<Cell> list = new ArrayList<Cell>();  // 세포의 상태를 미리 담는 리스트 
			for (int s = 0; s < size; s++) {

				Cell cur = queue.poll();
				if (cur.state == 0) {  // 비활성화 상태라면 
					if (level - cur.createdTime == cur.num) { // 현재 배양시간 - 세포가 생성된 시간 == 생명력 수치
						cur.state = 1; // 활성 상태 전환
						list.add(cur);
					} else {
						list.add(cur);
					}
				} else { // 활성 상태

					// 현재 배양시간 - 세포가 생성된 시간 <  2 * 생명력 수치
					if (level - cur.createdTime < cur.num + cur.num) {
						list.add(cur);
					}

					for (int i = 0; i < 4; i++) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];

						if (board[nx][ny] == 0) { // 세포가 없으면 바로 퍼짐 
							board[nx][ny] = cur.num;
							list.add(new Cell(0, cur.num, nx, ny, level));

						}

					}

				}
			}

			for (Cell c : list) {
				queue.offer(c);
			}
//			System.out.println(queue.size());
			level++;
		}
		return queue.size();

	}

}
