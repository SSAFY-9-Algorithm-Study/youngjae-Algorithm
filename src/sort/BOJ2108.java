package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * 통계학
 * 
 * 산술평균 : N개의 수들의 합을 N으로 나눈 값 
 * 중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값 
 * 최빈값 : N개의 수들 중 가장 많이 나타나는 값 
 * 범위 : N개의 수들 중 최댓값과 최솟값의 차이
 * 
 * 출력 첫째 줄에는 산술평균을 출력한다. 소수점 이하 첫째 자리에서 반올림한 값을 출력한다. 둘째 줄에는 중앙값을 출력한다. 셋째 줄에는
 * 최빈값을 출력한다. 여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다. 넷째 줄에는 범위를 출력한다.
 *
 * 
 */

public class BOJ2108 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int arr[] = new int[n+1];
		arr[n]=Integer.MAX_VALUE;
		double sum = 0;
		int ave = 0;
		
		
		
		int center;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		Arrays.sort(arr);
		ave = (int)Math.round(sum / n);	
		center = arr[n / 2];

		int max=Integer.MIN_VALUE;
		
		int count = 1;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (arr[i] == arr[i + 1]) {
				count++;
			} else {
				if (!map.containsKey(count)) {
					map.put(count, new ArrayList<Integer>());
				}
				map.get(count).add(arr[i]);
				max=Math.max(max,count);
				count = 1;
			}
		}
		

//		for (Integer i : map.keySet()) {
//			System.out.println(i + " " + map.get(i));
//		}
		
		System.out.println(ave);
		System.out.println(center);
		
		if(map.get(max).size()>=2) {
			System.out.println(map.get(max).get(1));
		}else {
			System.out.println(map.get(max).get(0));
		}
			
		System.out.println(arr[n-1]-arr[0]);

		br.close();

	}
}
