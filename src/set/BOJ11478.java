package set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/*
 서로 다른 부분 문자열의 개수
문자열 S가 주어졌을 때, S의 서로 다른 부분 문자열의 개수를 구하는 프로그램
 ababc의 부분 문자열은 a, b, a, b, c, ab, ba, ab, bc, aba, bab, abc, abab, babc, ababc가 있고, 서로 다른것의 개수는 12개
 */

public class BOJ11478 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char cArr[] = new char[str.length()];
		Set<String> set = new HashSet<String>();

		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				set.add(str.substring(i, j + 1));
			}
		}
		System.out.println(set.size());
		br.close();
	}

}
