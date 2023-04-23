package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ2470_두_용액 {

	static class Point implements Comparable<Point> {
		int lt;
		int rt;
		long sum;

		public Point(int lt, int rt, long sum) {

			this.lt = lt;
			this.rt = rt;
			this.sum = sum;
		}

		@Override
		public int compareTo(Point o) {
			return Double.compare(Math.abs(this.sum), Math.abs(o.sum));
		}

		@Override
		public String toString() {
			return "Point [lt=" + lt + ", rt=" + rt + ", sum=" + sum + "]";
		}

	}

	static int arr[];
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int lt = 0;
		int rt = arr.length - 1;
		PriorityQueue<Point> pQueue = new PriorityQueue<Point>();
		while(lt!=rt) {
			long sum=arr[lt]+arr[rt];
			pQueue.add(new Point(lt, rt, sum));
			if(sum<0) {
				lt++;
			}else if(sum>0) {
				rt--;
			}else { // 0인 경우
			
				break;
			}
		}
		Point answer = pQueue.poll();
		System.out.println(arr[answer.lt]+" "+arr[answer.rt]);
		
		br.close();
	}

}
