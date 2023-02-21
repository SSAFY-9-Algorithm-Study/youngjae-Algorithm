package list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.management.Query;

public class BOJ12605 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Queue<Integer> queue = new LinkedList<Integer>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			queue.offer(i+1);
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<k-1;j++) {
				queue.offer(queue.poll());
			}
			System.out.print(queue.poll()+" ");
		}

		br.close();

	}
}
