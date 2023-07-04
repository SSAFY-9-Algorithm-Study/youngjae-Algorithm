package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BOJ2800_괄호제거 {

	static int permu[];
	static int arr[];
	static Map<Integer, ArrayList<Integer>> map;
	static String input;
	static int visited[];
	static int size;
	static ArrayList<String> list;
	static TreeMap<String, Integer> treeMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input = br.readLine();

		Deque<Integer> dequeLt = new LinkedList<Integer>();
		Deque<Integer> dequeRt = new LinkedList<Integer>();

		Stack<Character> stack = new Stack<Character>();

		size = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				size++;
			}
		}
		
		map = new HashMap<Integer, ArrayList<Integer>>();
		int index=0;
		

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				map.put(index, new ArrayList<Integer>());
				map.get(index).add(i);
				dequeLt.offer(index);
				index++;
				stack.push(input.charAt(i));
			} else if (input.charAt(i) == ')') {
				while (stack.pop() != '(') {
				}
				map.get(dequeLt.pollLast()).add(i);
				
			} else {
				stack.push(input.charAt(i));
			}
		}
		
		visited = new int[size];
		list = new ArrayList<String>();
		
//		for (int i = 0; i < size; i++) {
//			System.out.println(map.get(i));
//		}

		combi(0);
		Collections.sort(list);
		treeMap = new TreeMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			treeMap.put(list.get(i), 1);

		}

		for (String key : treeMap.keySet()) {
			System.out.println(key);
		}

		br.close();
	}

	static void combi(int level) {
		if (level == size) {

			Queue<Character> queue = new LinkedList<Character>();
			String copy = new String(input);
//			System.out.println(Arrays.toString(visited));
			StringBuffer sb = new StringBuffer(input);

			boolean flag = false;

			for (int i = 0; i < visited.length; i++) {
				if (visited[i] == 1) {
					flag = true;
				}
			}

			if (flag == true) {
				for (int i = 0; i < visited.length; i++) {

					if (visited[i] == 1) {
						int lt = map.get(i).get(0);
						int rt = map.get(i).get(1);
						sb.setCharAt(lt, ' ');

						sb.setCharAt(rt, ' ');

					}
				}
				list.add(sb.toString().replaceAll(" ", ""));

			}

		} else {

			visited[level] = 1;
			combi(level + 1);

			visited[level] = 0;
			combi(level + 1);

		}
	}

}
