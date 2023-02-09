package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * 소트인사이드
 *
 * 각 자리수를 내림차순으로 정렬
 *
 * 입력
 * N은 1,000,000,000보다 작거나 같은 자연수이다.
 * 999998999
 *
 * 출력
 * 첫째 줄에 자리수를 내림차순으로 정렬한 수
 * 999999998
 *
 */

public class BOJ1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        
        char[] chArr=str.toCharArray();
        Arrays.sort(chArr);
        
        for(int i=chArr.length-1;i>=0;i--) {
        	System.out.print(chArr[i]);
        }
        
        br.close();
    }
}
