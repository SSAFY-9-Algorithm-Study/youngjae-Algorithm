package set;

import java.io.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class BOJ1822 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		Set<Integer> set1=new TreeSet<Integer>();
		Set<Integer> set2=new TreeSet<Integer>();
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n= Integer.parseInt(st.nextToken()); 
		int m= Integer.parseInt(st.nextToken()); 
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			
			set1.add(Integer.parseInt(st.nextToken()));
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			
			set2.add(Integer.parseInt( st.nextToken()));
		}
		
		set1.removeAll(set2);
		
		StringBuilder sBuilder=new StringBuilder();
		System.out.println(set1.size());
		if(set1.size() != 0) {
			for(Integer x:set1) {
				sBuilder.append(x+" ");
//				System.out.print(x+" ");
			}
			System.out.print(sBuilder.toString().trim());
		}
		
		
		br.close();
	}
}
