package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ1764 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		Map<String,Integer> map=new TreeMap<String, Integer>();
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());

		for(int i=0;i<n;i++) {
			map.put(br.readLine(), 1);
		}
		int count=0;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m;i++) {
			String str=br.readLine();
			if(map.containsKey(str)) {
				count++;
				sb.append(str+"\n");
			}
		}
		System.out.println(count);
		if(count>1)
			System.out.println(sb.toString().trim());
		br.close();
	}
}
