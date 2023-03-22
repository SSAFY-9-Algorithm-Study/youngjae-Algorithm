package week7;

import java.util.Arrays;

public class 양궁대회 {

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

		// 어피치가 다 이긴경우
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
			// 중복조합이 {0,0,0} -> {10,10,10}을 진행되므로
			 // 뒷자리 부터 1씩 증가하면서 커지고 있다.
			// 가장 낮은 점수를 맞힌 개수가 같을 경우 계속해서 그다음으로 낮은 점수를 더 많이 맞힌 경우를 return 한다는 조건 성립됨
			int[] lion = new int[11];
			for (int i = 0; i < combi.length; i++) {
				lion[10-combi[i]]++;
			}

			int apeachScore = 0;
			int lionScore = 0;

			for (int i = 0; i <= 10; i++) {
				if (info[i] > lion[i]) { // 어피치가 이긴경우
					apeachScore += (10 - i);
				} else if (info[i] != 0 && info[i] == lion[i]) {// 비긴경우
					apeachScore += (10 - i);
				} else if (info[i] < lion[i]) {// 라이언이 이긴경우
					lionScore += (10 - i);
				}
			}

			// 가장 낮은 점수를 맞힌 개수가 같을 경우 계속해서 그다음으로 낮은 점수를 더 많이 맞힌 경우를 return 한다는 조건 성립됨
			if ((lionScore - apeachScore) >difference ) {
				difference = lionScore - apeachScore;
				answer = lion.clone();
			}

		} else {
			// 중복 조합 구하기
			for (int i = start; i <= 10; i++) {
				combi[level] = i;
				DFS(r, i, level + 1, info);
			}
		}
	}

}
