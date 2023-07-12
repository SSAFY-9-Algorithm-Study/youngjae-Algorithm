package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1461_도서관 {

    static int N;
    static int M;

    static PriorityQueue<Integer> lpq;
    static PriorityQueue<Integer> rpq;
    static int answer;
    static int max;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        lpq = new PriorityQueue<>(Collections.reverseOrder());
        rpq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (x < 0) {
                lpq.add(-x);
            } else {
                rpq.add(x);
            }
        }


        max = 0;
        answer = 0;
        if (lpq.peek() == null) {
            max = rpq.peek();
            for(int i=0;i<M;i++){
                rpq.poll();
            }
        } else if (rpq.peek() == null) {
            max = lpq.peek();
            for(int i=0;i<M;i++){
                lpq.poll();
            }
        }else if (lpq.peek() > rpq.peek()) {
            max = lpq.peek();
            for(int i=0;i<M;i++){
                lpq.poll();
            }
        } else {
            max = rpq.peek();
            for(int i=0;i<M;i++){
                rpq.poll();
            }
        }

        answer += max;

        while (!(lpq.isEmpty() && rpq.isEmpty())) {
            max = 0;
            if (lpq.peek() == null) {
                max = rpq.peek();
                for(int i=0;i<M;i++){
                    rpq.poll();
                }
            } else if (rpq.peek() == null) {
                max = lpq.peek();
                for(int i=0;i<M;i++){
                    lpq.poll();
                }
            }else if (lpq.peek() > rpq.peek()) {
                max = lpq.peek();
                for(int i=0;i<M;i++){
                    lpq.poll();
                }
            } else {
                max = rpq.peek();
                for(int i=0;i<M;i++){
                    rpq.poll();
                }
            }

            answer += max*2;
        }

        System.out.println(answer);

        br.close();

    }
}
