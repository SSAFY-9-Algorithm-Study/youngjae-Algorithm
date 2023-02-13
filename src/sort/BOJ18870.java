package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.*;


/*
 
 좌표 압축

문제
수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다. 이 좌표에 좌표 압축을 적용하

입력
5
2 4 -10 4 -9

출력
2 3 0 3 1
 
 */
public class BOJ18870 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int arr[]=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int[] copyArr=new int[n];
		System.arraycopy(arr, 0, copyArr, 0, n);
		Arrays.sort(arr);
		
		Map<Integer, Integer>map=new HashMap<>();
		int count=0;
		for(int i=0;i<n;i++) {		
			if(!map.containsKey(arr[i])) {
				
				map.put(arr[i], count);
				count++;
			}
		}
		
		
		for(int i:copyArr) {
			bw.write(map.get(i) +" ");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
