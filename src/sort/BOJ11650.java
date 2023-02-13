package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 
 좌표 정렬하기
 
 문제
2차원 평면 위의 점 N개가 주어진다. 
좌표를 x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램

압력
5
3 4
1 1
1 -1
2 2
3 3

출력
1 -1
1 1
2 2
3 3
3 4
 */

public class BOJ11650 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Point arr[] = new Point[n];
		StringTokenizer st;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i]=new Point(x,y);
		}
		Arrays.sort(arr);
		for (Point p : arr) {
			System.out.println(p.x + " " + p.y);
		}

		br.close();
	}
}

class Point implements Comparable<Point> {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		if (this.x != o.x) {
			return this.x - o.x;
		}

		return this.y - o.y;
	}
}