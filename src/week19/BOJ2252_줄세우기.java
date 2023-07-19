package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2252_줄세우기 {

    static int inbound[];
    static int visited[];
    static List<Integer> adj_list[];
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj_list = new List[N+1];
        inbound = new int[N + 1];
        visited = new int[N + 1];

        for (int i = 0; i < N+1; i++) {
            adj_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj_list[from].add(to);
            inbound[to]++;
        }

        solution();

        br.close();

    }

    static void solution() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < N + 1; i++) {
            if (inbound[i] == 0) {
                queue.add(i);
                visited[i] = 1;
                System.out.print(i+" ");
            }
        }


        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int cur = queue.poll();

                for (Integer next : adj_list[cur]) {
                    if (inbound[next] != 0) {
                        inbound[next]--;
                        if (inbound[next] == 0) {
                            queue.offer(next);
                            System.out.print(next+" ");
                        }
                    }
                }
            }
        }


    }

}
