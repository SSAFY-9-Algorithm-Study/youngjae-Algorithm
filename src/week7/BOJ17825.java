package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17825 {

	static class Node {
		int data;
		Node red;
		Node blue;

		public Node(int data, Node red, Node blue) {
			this.data = data;
			this.red = red;
			this.blue = blue;
		}

	}

	static class Horse {
		int sum;
		Node position;

		public Horse(int sum, Node position) {
			this.sum = sum;
			this.position = position;
		}

	}

	static int[] moveArr;
	static int[] visited;
	static int[] permutation;
	static int[] answerpermutation;
	static Horse[] HorseArr;
	static Node tree[];
	static int answer;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		moveArr = new int[10];
		permutation = new int[10];
		answerpermutation = new int[10];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++) {
			moveArr[i] = Integer.parseInt(st.nextToken());
		}

		tree = new Node[42];
		for (int i = 0; i < 42; i++) {
			tree[i] = new Node(0, null, null);
		}

		for (int i = 1; i <= 20; i++) {
			tree[i].data = i * 2;
			tree[i].red = tree[i + 1];
		}

		tree[5].blue = tree[22];
		tree[22].data = 13;
		tree[22].red = tree[23];
		tree[23].data = 16;
		tree[23].red = tree[24];
		tree[24].data = 19;
		tree[24].red = tree[25];

		tree[10].blue = tree[26];
		tree[26].data = 22;
		tree[26].red = tree[27];
		tree[27].data = 24;
		tree[27].red = tree[25];

		tree[15].blue = tree[28];
		tree[28].data = 28;
		tree[28].red = tree[29];
		tree[29].data = 27;
		tree[29].red = tree[30];
		tree[30].data = 26;
		tree[30].red = tree[25];

		tree[25].data = 25;
		tree[25].red = tree[31];
		tree[31].data = 30;
		tree[31].red = tree[32];
		tree[32].data = 35;
		tree[32].red = tree[20];
		tree[20].data = 40;

		tree[0].red = tree[1];
		tree[20].red = tree[21];
		tree[21] = null;

		HorseArr = new Horse[4];

		DFS(0);
		System.out.println(answer);

		br.close();

	}

	static void DFS(int level) {
		if (level == 10) {

			HorseArr[0] = new Horse(0, tree[0]);
			HorseArr[1] = new Horse(0, tree[0]);
			HorseArr[2] = new Horse(0, tree[0]);
			HorseArr[3] = new Horse(0, tree[0]);
			flag = false;
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				//말이 종착지에 도착한경우
				if (HorseArr[permutation[i]].position == null) {
					continue;
				}
				//현재의 말을 copy한 tmp말 생성
				// 말 이동하기 전에 미리 이동가는한지 확인하는 용도
				Horse tmpHorse = new Horse(HorseArr[permutation[i]].sum, HorseArr[permutation[i]].position);

				for (int m = 0; m < moveArr[i]; m++) {
					if (tmpHorse.position != null) {
						// 시작지점에 파란부분이 있으면 우선적으로 지나감
						if (tmpHorse.position.blue != null && m == 0) {
							tmpHorse.position = tmpHorse.position.blue;
						} else {
							
							tmpHorse.position = tmpHorse.position.red;
						}
					}

				}

				//현재의 말이 종착지에 도착
				if (tmpHorse.position == null) {
					HorseArr[permutation[i]] = new Horse(tmpHorse.sum, tmpHorse.position);
					continue;
				} else {
					// 현재의 말과 다른 말들이 겹치는지 체크
					for (int h = 0; h < 4; h++) {
						// 다른 말이 종착지에 있지않고, 현재의 말과 겹쳐지면  
						// 이동하는 곳에 말이있으면 이동하지 않기위해서 tmp를 만들었는데, 말이 이동못하면 그냥 사용할수없는 경우임
						if (HorseArr[h].position != null && tmpHorse.position != null) {
							if (HorseArr[h].position == tmpHorse.position) {
								flag = true;
							}
						}

					}
				}
				
				// 겹치지 않으면 실제 현재의 말을 이동
				if (flag == false) {
					HorseArr[permutation[i]] = new Horse(tmpHorse.sum, tmpHorse.position);
					if (HorseArr[permutation[i]].position != null)
						HorseArr[permutation[i]].sum += HorseArr[permutation[i]].position.data;
				}
			}
			
			// 한번이라고 말이 겹쳐진적이 있으면 점수 계산
			if (!flag) {
				answer = Math.max(answer, HorseArr[0].sum + HorseArr[1].sum + HorseArr[2].sum + HorseArr[3].sum);
			}

		} else {
			for (int i = 0; i < 4; i++) {
				permutation[level] = i;
				DFS(level + 1);
			}
		}
	}

}