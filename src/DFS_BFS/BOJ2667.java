package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//class Point {
//	int x;
//	int y;
//
//	Point(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//}

public class BOJ2667 {

	public static int board[][];
	public static int dx[] = { -1, 0, 1, 0 };
	public static int dy[] = { 0, 1, 0, -1 };
	public static int n;
	public static int answer = 0;
	public static ArrayList<Integer> answerList=new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		board = new int[n][n];

		for (int i = 0; i < n; i++) {
			char chArr[] = br.readLine().toCharArray();
			for (int j = 0; j < chArr.length; j++) {
				board[i][j] = Integer.parseInt(String.valueOf(chArr[j]));
			}
		}


		
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				if(board[i][j]==1) {
					answer++;
					int count=0;
					count = DFS(new Point(i, j),count);
					if(count==0) {
						count=1;
					}
					answerList.add(count);
//					System.out.println(count);
				}
			}
		}


		Collections.sort(answerList);
		

		System.out.println(answer);
		for(int a:answerList) {
			System.out.println(a);
		}
		br.close();

	}

	public static int DFS(Point start,int count) {

		for (int i = 0; i < 4; i++) {
			int nextX = dx[i] + start.x;
			int nextY = dy[i] + start.y;
			if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && board[nextX][nextY] == 1) {
				board[nextX][nextY] = 0;
//				count++;
				count= DFS(new Point(nextX, nextY),count+1);
				
			}
		}
		return count;

	}


}
