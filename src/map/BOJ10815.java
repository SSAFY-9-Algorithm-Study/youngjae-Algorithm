package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 숫자 카드
 문제
숫자 카드는 정수 하나가 적혀져 있는 카드이다. 
상근이는 숫자 카드 N개를 가지고 있다. 정수 M개가 주어졌을 때,
이 수가 적혀있는 숫자 카드를 상근이가 가지고 있는지 아닌지를 구하는 프로그램
 
 첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다. 
 둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다. 
 각 수가 적힌 숫자 카드를 상근이가 가지고 있으면 1을, 아니면 0을 공백으로 구분해 출력한다.
 
5
6 3 2 10 -10
8
10 9 -5 2 3 4 5 -10
 
1 0 0 1 1 0 0 1
 */

public class BOJ10815 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int arr[]=new int[n];
		Map<Integer, Integer> userMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> intMap = new LinkedHashMap<Integer, Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			userMap.put(Integer.parseInt(st.nextToken()), 1);
		}

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int x=Integer.parseInt(st.nextToken());
			intMap.put(x, userMap.getOrDefault(x, 0));
			
		}
		for(int i:intMap.values()) {
			System.out.print(i+" ");
		}

		br.close();
		
	}

}
