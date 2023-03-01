package backtracking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BOJ9663 {

	public static int N;
	public static int answer = 0;
	public static int pm[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		pm = new int[N];
		DFS(0);

		System.out.println(answer);

		br.close();
	}

	public static void DFS(int level) {

		
		if (level == N) {
//			System.out.println(Arrays.toString(pm));
			answer++;
			return;
		} else {
			for (int i = 0; i < N; i++) {

				pm[level] = i;
				if (isPossible(level)) {
					DFS(level + 1);
					
				}

			}
		}


	}

	public static boolean isPossible(int level) {

		for (int i = 0; i < level; i++) {
			if (pm[i] == pm[level]) {
				return false;
			}
			else if (Math.abs(pm[i] - pm[level]) == Math.abs(i - level)) {
				return false;
			}
		}

		return true;
	}

}
