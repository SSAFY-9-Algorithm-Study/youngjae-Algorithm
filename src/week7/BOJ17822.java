package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.BlockingDeque;

public class BOJ17822 {

	static int N; // ������ N�� ����
	static int M; // ���ǿ��� �����ִ� M�� ����
	static int T; // T�� ȸ��

	static int x; // ��ȣ�� x�� ��� ����
	static int d; // d���� (0�� ���� �ð� ����, 1�� ���� �ݽð� ����)
	static int k; // kĭ ȸ��

	static int board[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		board = new int[N + 1][M];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			// ���� ȸ��
			spin();

			// �����ϸ鼭 ���� ���� ��� �����.
			if (!findAdj()) {
				updateBoard();

			}
		}

		int sum = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				sum += board[i][j];
			}
		}

		System.out.println(sum);

		br.close();
	}

	// ���� ȸ��
	static void spin() {

		for (int s = 0; s < k; s++) {
			for (int r = x; r <= N; r = r + x) {
				if (d == 1) { // �ݽð�
					for (int i = 0; i < M - 1; i++) {

						int tmp = board[r][i];
						board[r][i] = board[r][i + 1];
						board[r][i + 1] = tmp;
					}
				} else {// �ð�
					for (int i = M - 1; i >= 1; i--) {
						int tmp = board[r][i];
						board[r][i] = board[r][i - 1];
						board[r][i - 1] = tmp;
					}
				}

			}
		}

	}

	// �����ϸ鼭 ���� ���� ��� �����.
	static boolean findAdj() {
		boolean flag = false;

		//�̸� �����Ѽ����� �̸� üũ�س���, ���߿� ��������Ѵ�.
		boolean check[][] = new boolean[N + 1][M];

		//���ι��� ���� Ȯ��
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0 && board[i][j] == board[i][(j + 1) % M]) {
					flag = true;
					check[i][j] = true;
					check[i][(j + 1) % M] = true;

				}
			}
		}

		//���ι��� ���� Ȯ��
		for (int j = 0; j < M; j++) {
			for (int i = 1; i < N; i++) {
				if (board[i][j] != 0 && board[i][j] == board[i + 1][j]) {
					flag = true;
					check[i][j] = true;
					check[i + 1][j] = true;

				}

			}

		}

		//�����Ѱ� �־��ٸ� check�迭�� ������  0���� �ٲ��ش�.
		if (flag) {
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j]) {
						board[i][j] = 0;
					}
				}
			}
		}

		return flag;
	}

	//��պ��� ū ������ 1�� ����, ���� ������ 1�� ���Ѵ�.
	static void updateBoard() {
		double sum = 0;
		int count = 0;
		double ave;
		
		//0�� �ƴ� �� ī��Ʈ �� �հ��
		for (int i = 1; i <= N; i++) {

			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0) {
					sum += board[i][j];
					count++;
				}
			}

		}
		//��� ���
		ave = sum / count;
		for (int i = 1; i <= N; i++) {

			
			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0 && board[i][j] > ave) { //��պ��� ū ������ 1�� ��
					board[i][j]--;

				} else if (board[i][j] != 0 && board[i][j] < ave) { //��պ��� ���� ������ 1�� ���Ѵ�
					board[i][j]++;
				}
			}

		}

	}

}
