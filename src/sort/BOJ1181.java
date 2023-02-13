package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/*
 
 단어 정렬
길이가 짧은 것부터
길이가 같으면 사전 순으로
단, 중복된 단어는 하나만 남기고 제거해야 한다.

입력
13
but
i
wont
hesitate
no
more
no
more
it
cannot
wait
im
yours

출력
i
im
it
no
but
more
wait
wont
yours
cannot
hesitate
 
 */


public class BOJ1181 {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		
		String[] strArr=new String[n];
		
		for(int i=0;i<n;i++) {
			strArr[i]=br.readLine();
		}
		
		Arrays.sort(strArr,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if(o1.length()!=o2.length()) {
					return o1.length()-o2.length();
				}
				return o1.compareTo(o2);
			}
		});
		for(int i=0;i<n-1;i++) {
			if(!strArr[i].equals(strArr[i+1]))
				System.out.println(strArr[i]);
		}
		System.out.println(strArr[n-1]);
		br.close();
	}
}
