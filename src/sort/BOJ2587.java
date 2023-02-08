package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * 대표값2
 *
 */

public class BOJ2587 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer arr[] = new Integer[5];
        int sum=0;
        StringTokenizer st;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());;
            arr[i] = Integer.parseInt(st.nextToken());
            sum+=arr[i];
        }
        Arrays.sort(arr, Collections.reverseOrder());

        int center = arr[2];
        int avg = sum / 5;
        System.out.println(avg);

        System.out.println(center);

        br.close();
    }
}
