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
				if(HorseArr[permutation[i]].position == null) {
					continue;
				}
				Horse tmpHorse = new Horse(HorseArr[permutation[i]].sum, HorseArr[permutation[i]].position);

				
				for (int m = 0; m < moveArr[i]; m++) {
					if (tmpHorse.position != null) {
						if (tmpHorse.position.blue != null && m == 0) {
							tmpHorse.position = tmpHorse.position.blue;
						} else {
							tmpHorse.position = tmpHorse.position.red;
						}
					}

				}

				if (tmpHorse.position == null) {
					HorseArr[permutation[i]] = new Horse(tmpHorse.sum, tmpHorse.position);
					continue;
				} else {
					for (int h = 0; h < 4; h++) {

						if (HorseArr[h].position != null && tmpHorse.position != null) {
							if (HorseArr[h].position == tmpHorse.position) {
								flag = true;
							}
						}

					}
				}

				if (flag == false) {
					HorseArr[permutation[i]] = new Horse(tmpHorse.sum, tmpHorse.position);
					if (HorseArr[permutation[i]].position != null)
						HorseArr[permutation[i]].sum += HorseArr[permutation[i]].position.data;
				}
				
				

			}

			if(!flag) {

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