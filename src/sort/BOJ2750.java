package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 수 정렬하기
 *
 *
 */
public class BOJ2750 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Integer arr[] = new Integer[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(arr);
//        Arrays.sort(arr,Collections.reverseOrder());
        for (int i : arr) {
            System.out.println(i);
        }

        br.close();
    }

}
