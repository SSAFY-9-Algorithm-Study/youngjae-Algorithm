package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1005_ACM_Craft {

	static int N;// 건물의 개수 N
	static int K;// 건물간의 건설순서 규칙의 총 개수 K
	static int building[]; //각각의 건설 시간 
	static int indegree[]; //  진입간선의 수 
	static ArrayList<Integer> array[];
	static int DP[]; // 현 지점까지 도착하는데 건설비용이 가장긴 시간 저장 
	static int W;//건물 W를 건설완료 하는데 드는 최소 시간을 출력
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			building = new int[N + 1];
			indegree = new int[N + 1];
			DP = new int[N + 1];
			array = new ArrayList[N + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				building[i] = Integer.parseInt(st.nextToken());
				array[i]=new ArrayList<Integer>();
			}
			
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				array[start].add(end);
				indegree[end]++;
			}
			W =  Integer.parseInt(br.readLine());
			
			
			topologicalSort();
			
			System.out.println(DP[W]);
		}

		br.close();
	}
	
	static void topologicalSort() {
		Queue<Integer> queue= new LinkedList<Integer>();
		
		for(int i=1;i<=N;i++) {  //진입노드 갯수가 0인 노드 찾기
			if(indegree[i]==0) {
				queue.offer(i);
				DP[i]=building[i]; //현재 빌딩을 짓는시간을 DP에 저장 
			}
		}
		
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s=0;s<size;s++) {
				int cur = queue.poll();
				
				for(Integer next:array[cur]) {
					// 현재 정점이 건설완료했기 때문에 현재 정점 에서 다음정점까지 가는 진입수를 뺀다.
					indegree[next]--; 
					// 현재까지 건물을 짓는비용+다음빌딩을 짓는 비용과, 다음빌딩을 짓는비용을 비교후 저장 
					DP[next]=Math.max(DP[next], DP[cur] + building[next]);
					
					if(indegree[next]==0) {  //진입노드 갯수가 0이면
						queue.offer(next);
					}
					
				}
				
			}
		}
		
	}
	
	
}
