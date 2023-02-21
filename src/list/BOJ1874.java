package list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ1874 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> stack=new LinkedList<Integer>();
		int n=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		int arr[]=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(br.readLine());
			
		}
		
		int num=1;
		
		//결국 pop을 n번해야 한다.
		for(int i=0;i<n;i++) {
			
			while(num <= arr[i]) {
				stack.offerLast(num);
				num++;
				sb.append("+\n");
			}
			if(arr[i] == stack.peekLast() ) {
				stack.pollLast();
				sb.append("-\n");
			}else {
				System.out.println("NO");
				br.close();
				return ;
			}
			
		}
		System.out.println(sb.toString());
		
		br.close();

	}


}
