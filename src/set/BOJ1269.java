package set;

import java.io.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/*
 
 대칭 차집합
자연수를 원소로 갖는 공집합이 아닌 두 집합 A와 B가 있다.
 두 집합의 대칭 차집합의 원소의 개수를 출력하는 프로그램
 
 */

public class BOJ1269 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		Set<Integer> setA=new HashSet<Integer>();
		Set<Integer> setB=new HashSet<Integer>();
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<a;i++) {
			setA.add(Integer.parseInt(st.nextToken()));
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<b;i++) {
			setB.add(Integer.parseInt(st.nextToken()));
		}
		
	
		Set<Integer> union=new HashSet<Integer>(setA);
		Set<Integer> intersection=new HashSet<Integer>(setA);
		
		union.addAll(setB);
		intersection.retainAll(setB);
		union.removeAll(intersection);
		System.out.println(union.size());
		
		br.close();
		
	}
}
