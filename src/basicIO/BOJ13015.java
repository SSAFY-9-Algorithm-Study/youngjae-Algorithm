package basicIO;
/**
 *
 * 별 찍기 - 23
 *
 */

import java.io.*;
import java.util.Arrays;

public class BOJ13015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        int width = (n * 2) + ((n - 1) * 2) - 1;
        char star[][] = new char[n][width];

        for (int i = 0; i < n; i++) {
            Arrays.fill(star[i],' ');
        }

        for (int i = 0; i < width; i++) {
            if(i < n || i > width-n-1)
                star[0][i] = '*';
        }

        for (int i = 1; i < n; i++) {

            star[i][i] = '*';
            star[i][n-1+i] = '*';
            star[i][width-1-i] = '*';
            star[i][width-i-n] = '*';

        }

        for (int i = 0; i < n * 2; i++) {

            if (i == n) {
                continue;
            }
            if (i > n) {

                System.out.println(String.valueOf(star[n-(i%n)-1]).substring(0,width/2 + i));
            } else {
                System.out.println(String.valueOf(star[i]).substring(0,width-i));
            }
        }

        br.close();


    }
}
