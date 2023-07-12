package week18;

import java.io.*;
import java.util.*;

public class BOJ7662_이중우선순위큐 {

    static int inputCtn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            inputCtn = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> maxPq = new PriorityQueue(Collections.reverseOrder());
            PriorityQueue<Integer> minPq = new PriorityQueue();
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < inputCtn; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (op.equals("I")) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                    maxPq.offer(num);
                    minPq.offer(num);
                } else if (op.equals("D")) {
                    Integer maxOrMin;
                    if (num == 1) {

                        maxOrMin = maxPq.poll();
                        while(!map.containsKey(maxOrMin)){
                            maxOrMin = maxPq.poll();
                            if(maxOrMin==null)
                                break;
                        }
//                        maxOrMin = maxPq.poll();
                    } else {
                        maxOrMin = minPq.poll();
                        while(!map.containsKey(maxOrMin)){
                            maxOrMin = minPq.poll();
                            if(maxOrMin==null)
                                break;
                        }
//                        maxOrMin = minPq.poll();
                    }

                    if (map.get(maxOrMin) != null) {
                        if (map.get(maxOrMin) == 1) {
                            map.remove(maxOrMin);
                        } else {
                            map.put(maxOrMin, map.get(maxOrMin) - 1);
                        }
                    }
                }
            }

            Integer max;
            max = maxPq.poll();
            while(!map.containsKey(max)){
                max = maxPq.poll();
                if(max==null)
                    break;
            }

            Integer min;
            min = minPq.poll();
            while(!map.containsKey(min)){
                min = minPq.poll();
                if(min==null)
                    break;
            }

            if (max != null && min != null) {
                System.out.println(max + " " + min);
            } else if (max != null) {
                System.out.println(max + " " + max);
            } else if (min != null) {
                System.out.println(min + " " + min);
            } else {
                System.out.println("EMPTY");
            }



        }


        br.close();
    }
}
