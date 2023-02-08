package basicIO;

/**
 *
 * 별 찍기 - 21
 *
 */

import java.io.*;

public class BOJ10996 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        if (n == 0) {
            bw.write("*");
            bw.flush();
            br.close();
            bw.close();
        }

        char top[] = new char[n];
        char bottom[] = new char[n];
        top[0] = '*';
        bottom[0] = ' ';

        for (int i = 1; i < n; i++) {
            top[i] = bottom[i - 1];
            bottom[i] = top[i - 1];
        }

        String strTop = String.valueOf(top);
        String strBottom = String.valueOf(bottom);
        if (n == 1) {
            bw.write("*");
        } else {
            for (int i = 0; i < n; i++) {
                bw.write(strTop + "\n" + strBottom + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
