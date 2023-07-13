package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1633_최고의팀만들기 {

    static class Stone {
        int white;
        int black;

        public Stone(int white, int black, int index) {
            this.white = white;
            this.black = black;
        }
    }

    static List<Stone> list;
    static int size;
    static int DP[][][];  //index번쨰에 백팀이 w명, 청팀이 b명 존재할 때의 최합

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        StringTokenizer st;
        String input;
        int index = 0;
        while ((input = br.readLine()) != null) {
            if (input.equals("")) {
                break;
            }
            st = new StringTokenizer(input);
            int white = Integer.parseInt(st.nextToken());
            int black = Integer.parseInt(st.nextToken());

            list.add(new Stone(white, black, index));
            index++;
        }
        size = list.size();
        DP = new int[size][16][16];

        int answer = DFS(0, 0, 0);
        System.out.println(answer);

        br.close();

    }

    static int DFS(int index, int wCnt, int bCnt) {

        if (index == size)
            return 0;

        if (wCnt == 15 && bCnt == 15)
            return 0; // DP 초기화

        if(DP[index][wCnt][bCnt]!=0)
            return DP[index][wCnt][bCnt];

        // 뽑지 않는 경우
        DP[index][wCnt][bCnt] = Math.max(DP[index][wCnt][bCnt], DFS(index + 1, wCnt, bCnt));

        // 백으로 뽑는 경우
        if (wCnt < 15) {
            DP[index][wCnt][bCnt] = Math.max(DP[index][wCnt][bCnt], DFS(index + 1, wCnt + 1, bCnt) + list.get(index).white);
        }

        // 흑으로 뽑는 경우
        if (bCnt < 15) {
            DP[index][wCnt][bCnt] = Math.max(DP[index][wCnt][bCnt], DFS(index + 1, wCnt, bCnt + 1) + list.get(index).black);
        }

//        System.out.println(index+" "+wCnt+" "+bCnt+" "+DP[index][wCnt][bCnt]);
        return DP[index][wCnt][bCnt];
    }
}
