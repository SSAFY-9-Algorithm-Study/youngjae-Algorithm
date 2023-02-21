package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
생태학 
미국 전역의 나무들이 주어졌을 때, 
각 종이 전체에서 몇 %를 차지하는지 구하는 프로그램을 만들어야 한다.

 */

public class BOJ4358 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Map<String, Integer> map = new TreeMap<>();
		double total = 0;
		String str;
//		while ((str = br.readLine()).length() != 0) {
		while ((str = br.readLine())!= null) {
			map.put(str, map.getOrDefault(str, 0) + 1);
			total++;
		}

		for (String key : map.keySet()) {
			System.out.printf("%s %.4f\n", key, map.get(key) / total * 100);
		}

		br.close();
	}

}
