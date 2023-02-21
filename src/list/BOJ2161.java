package list;

import java.io.*;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

/*
 
 카드1
 N장의 카드가 있다. 
 각각의 카드는 차례로 1부터 N까지의 번호가 붙어 있으며, 
 1번 카드가 제일 위에, N번 카드가 제일 아래인 상태로 순서대로 카드가 놓여 있다.
 
 카드가 한 장 남을 때까지 반복하게 된다. 
 우선, 제일 위에 있는 카드를 바닥에 버린다. 
 그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
 
 N이 주어졌을 때, 버린 카드들을 순서대로 출력하고, 마지막에 남게 되는 카드를 출력하는 프로그램
 
입력 
7

출력 
1 3 5 7 4 2 6
 */

public class BOJ2161 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> dq = new LinkedList<Integer>();
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		for (int i = 1; i <= n; i++) {
			dq.offerLast(i);
		}

		while (!dq.isEmpty()) {
			sb.append(String.valueOf(dq.pollFirst()) + " ");
			
			if (!dq.isEmpty())
				dq.offerLast(dq.pollFirst());
		}

		System.out.println(sb.toString());

		br.close();
	}

}
