package week9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ2247_뵬찍기10 {

	static int N;
	static int[][] arr;
	static int num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		arr=new int[N][N];
		
		int count = 0;
		int step = N / 3;
		for (int i = 0; i < 3 * step; i = i + step) {
			for (int j = 0; j < 3 * step; j = j + step) {
				if (count == 4) {
					arr[i][j] = 0;
					DFS(i, j, step / 3, false);
				} else {
					arr[i][j] = 1;
					DFS(i, j, step / 3, true);
				}

				count++;
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}

		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < N; i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]==1) {
					sb.append("*");
				}
				else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
		
		br.close();
	}

	// x,y, 증가량, 이전레벨의 flag
	static void DFS(int x, int y, int step, boolean flag) {
		if (step == 0) {
			return;
		} else {
			int count = 0;
			for (int i = x; i < x + 3 * step; i = i + step) {
				for (int j = y; j < y + 3 * step; j = j + step) {
					if (flag == false || count == 4) {
						arr[i][j] = 0;
						DFS(i, j, step / 3, false);
					} else {
						arr[i][j] = 1;
						DFS(i, j, step / 3, true);
					}

					count++;
				}
			}
		}
	}

}
