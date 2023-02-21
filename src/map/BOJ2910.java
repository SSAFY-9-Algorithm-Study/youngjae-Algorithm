package map;

import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


/*

빈도 정렬
이 메시지는 숫자 N개로 이루어진 수열이고, 
숫자는 모두 C보다 작거나 같다. 
창영이는 이 숫자를 자주 등장하는 빈도순대로 정렬하려고 한다.
수열이 주어졌을 때, 빈도 정렬을 하는 프로그램

첫째 줄에 메시지의 길이 N과 C가 주어진다.
5 2
2 1 2 1 2

수열을 빈도 정렬한 다음 출력한다.
2 2 2 1 1

 */
public class BOJ2910 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(stringTokenizer.nextToken());
		int c = Integer.parseInt(stringTokenizer.nextToken());

		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		Map<Integer, List<Integer>> answerMap = new TreeMap<Integer, List<Integer>>(Collections.reverseOrder());

		int arr[] = new int[n + 1];
		arr[n] = Integer.MAX_VALUE;

		stringTokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int key = Integer.parseInt(stringTokenizer.nextToken());
			map.put(key, map.getOrDefault(key, 0) + 1);
		}

		for (int key : map.keySet()) {
			if (!answerMap.containsKey(map.get(key))) {
				answerMap.put(map.get(key), new ArrayList<Integer>());
			}
			answerMap.get(map.get(key)).add(key);

		}

		for (int key : answerMap.keySet()) {			
			for(int j=0;j<answerMap.get(key).size();j++) {
				for (int i = 0; i < key; i++) {
					System.out.print(answerMap.get(key).get(j) + " ");				
				}		
			}
		}
		br.close();
	}

}
