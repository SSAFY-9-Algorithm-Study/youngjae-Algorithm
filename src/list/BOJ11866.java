package list;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ11866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Deque<String> stack = new ArrayDeque<>();

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int count=0;
			while(st.hasMoreTokens()) {
				count++;
				stack.offerFirst(st.nextToken());
			}
			System.out.print("Case #"+(i+1)+": ");
			for(int j=0;j<count;j++) {
				System.out.print(stack.pollFirst()+" ");
			}
			System.out.println();
			
		}
		

		br.close();
	}

}
