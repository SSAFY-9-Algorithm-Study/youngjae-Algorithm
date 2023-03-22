package week7;

import java.util.Arrays;

public class ��ô�ȸ {

	public static void main(String[] args) {

		int n = 5;
		int[] info = { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };
//		int n = 1;
//		int[] info = { 1,0,0,0,0,0,0,0,0,0,0 };
//		int n = 9;
//		int[] info = { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 };
//		int n = 10;
//		int[] info = { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 };

		solution(n, info);

	}

	static int combi[];
	static int difference = 0;
	static int[] answer;

	public static int[] solution(int n, int[] info) {
		answer = new int[11];
		combi = new int[n];
		DFS(n, 0, 0, info);

		// ����ġ�� �� �̱���
		if (difference == 0) {
			answer = new int[1];
			answer[0] = -1;
		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}

	static void DFS(int r, int start, int level, int[] info) {
		if (r == level) {
			 System.out.println(Arrays.toString(combi));
			// �ߺ������� {0,0,0} -> {10,10,10}�� ����ǹǷ�
			 // ���ڸ� ���� 1�� �����ϸ鼭 Ŀ���� �ִ�.
			// ���� ���� ������ ���� ������ ���� ��� ����ؼ� �״������� ���� ������ �� ���� ���� ��츦 return �Ѵٴ� ���� ������
			int[] lion = new int[11];
			for (int i = 0; i < combi.length; i++) {
				lion[10-combi[i]]++;
			}

			int apeachScore = 0;
			int lionScore = 0;

			for (int i = 0; i <= 10; i++) {
				if (info[i] > lion[i]) { // ����ġ�� �̱���
					apeachScore += (10 - i);
				} else if (info[i] != 0 && info[i] == lion[i]) {// �����
					apeachScore += (10 - i);
				} else if (info[i] < lion[i]) {// ���̾��� �̱���
					lionScore += (10 - i);
				}
			}

			// ���� ���� ������ ���� ������ ���� ��� ����ؼ� �״������� ���� ������ �� ���� ���� ��츦 return �Ѵٴ� ���� ������
			if ((lionScore - apeachScore) >difference ) {
				difference = lionScore - apeachScore;
				answer = lion.clone();
			}

		} else {
			// �ߺ� ���� ���ϱ�
			for (int i = start; i <= 10; i++) {
				combi[level] = i;
				DFS(r, i, level + 1, info);
			}
		}
	}

}
