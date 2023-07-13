package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17298_오큰수 {

	static int N;
	static int arr[];
	static int answer[];
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		answer = new int[N];
		stack = new Stack<Integer>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			answer[i] = -1;
		}
		stack.push(arr[N - 1]);
		int max = 0;
		for (int i = N - 2; i >= 0; i--) {
			int size = stack.size();
			boolean flag = false;
			for (int j = 0; j < size; j++) {
				if (stack.peek() <= arr[i]) {
					stack.pop();
				} else {
					answer[i] = stack.peek();
					flag = true;
					break;
				}
			}

			if (stack.size() > 0)
				answer[i] = stack.peek();
//			if (flag == false) {
//				if (stack.size() > 0)
//					answer[i] = stack.peek();
//			}
			stack.push(arr[i]);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < answer.length; i++) {
			sb.append(Integer.toString(answer[i]));
			sb.append(" ");
		}
//		sb.append(answer[N - 1]);
		System.out.println(sb.toString());
		br.close();

	}

}
