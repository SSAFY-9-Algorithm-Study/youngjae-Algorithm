package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12896_스크루지_민호 {

	static ArrayList<Integer> graph[];  // 트리의 정보를 저장하는 인접리스트 배열
	static int N; // N 개의 도시
	static int answer; // 최적의 위치에 설치된 소방서에서 소방차가 출동해 다른 도시에 도착할 때까지 이동해야하는 거리들 중 최댓값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 양방향 그래프
			graph[start].add(end);
			graph[end].add(start);
		}
		
		
		int leafNode = getLeaf(1);
		answer = BFS(leafNode);

		System.out.println((answer+1)/2);
		br.close();
	}

	static int getLeaf(int start) {  // 말단노드를 찾는 BFS
		
		Queue<Integer> queue=new LinkedList<Integer>();
		// 트리의 정보를 순차적으로 저장해서 마지막에 들어가는 노드가 말단
		Deque<Integer> deque = new LinkedList<Integer>();  
		queue.add(start);
		deque.add(start);
		int visited[] = new int[N + 1];
		visited[start] = 1;
		int level=0;
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int s=0;s<size;s++) {
				int cur = queue.poll();
				for (int next : graph[cur]) {
					if (visited[next] == 0) {
						visited[next] = 1;
						queue.offer(next);
						deque.offer(next);
					}
				}
			}
			level++;
		}
		
		
		return deque.pollLast();  
		
		
	}
	
	static int BFS(int start) { // 깊이를 찾는 BFS
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(start);
		int visited[] = new int[N + 1];
		visited[start] = 1;
		int level=0;
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int s=0;s<size;s++) {
				int cur = queue.poll();
				for (int next : graph[cur]) {
					if (visited[next] == 0) {
						visited[next] = 1;
						queue.offer(next);
					}
				}
			}
			level++;
		}
		
		
		return level-1;
		
		
	}
	


}
