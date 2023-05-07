package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ14725_개미굴{

	static TreeMap<String, TreeMap> root;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		root = new TreeMap<String, TreeMap>();

		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			TreeMap<String, TreeMap> cur = root;
			for (int s = 0; s < size; s++) {
				String str = st.nextToken().toString();

				if (cur.get(str) == null) {
					cur.put(str, new TreeMap<String, TreeMap>());
				}

				cur = cur.get(str);
			}

		}

		for (String key : root.keySet()) {
			System.out.println(key);
			
			DFS(1, root.get(key));
		}

		br.close();

	}

	static void DFS(int level, TreeMap<String, TreeMap> map) {
		if (map == null) {
			return;
		} else {
			for (String key : map.keySet()) {
				for(int i=0;i<level;i++) {
					System.out.print("--");
				}
				
				System.out.println(key);
				DFS(level+1, map.get(key));
			}

		}
	}

}

