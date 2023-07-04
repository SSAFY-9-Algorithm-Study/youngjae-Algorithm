package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ3107_IPv6 {

	static String input;
	static String inputLt;
	static String inputRt;
	static int arr[];

	static Deque<Integer> dqLt;
	static Deque<Integer> dqRt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input = br.readLine();
		String[] split = input.split("::");
		arr = new int[8];
		dqLt = new LinkedList();
		dqRt = new LinkedList();

		
		if (input.charAt(input.length() - 1) == ':' && input.charAt(input.length() - 2) == ':') {
			String[] strSplit = input.split(":");
			for (int i = 0; i < strSplit.length; i++) {
				dqLt.offer(Integer.parseInt(strSplit[i], 16));
			}
			int size = dqLt.size();
			for (int i = 0; i < size; i++) {
				arr[i] = dqLt.poll();
			}
			printHex();

		} else if (input.charAt(0) == ':' && input.charAt(1) == ':') {
			input = input.substring(2, input.length());
//			System.out.println(input);
			String[] strSplit = input.split(":");

			for (int i = 0; i < strSplit.length; i++) {
				dqRt.add(Integer.parseInt(strSplit[i], 16));
			}

			int size = dqRt.size();
			for (int i = 7; i >= 8 - size; i--) {
				arr[i] = dqRt.pollLast();
			}

			printHex();

		} else {

			if (split.length == 2) {
				String[] strSplitLt = split[0].split(":");
				String[] strSplitRt = split[1].split(":");

				for (int i = 0; i < strSplitLt.length; i++) {
					dqLt.offer(Integer.parseInt(strSplitLt[i], 16));
				}

				for (int i = 0; i < strSplitRt.length; i++) {
					dqRt.add(Integer.parseInt(strSplitRt[i], 16));
				}
				
				int sizeL = dqLt.size();
				for (int i = 0; i < sizeL; i++) {
					arr[i] = dqLt.poll();
				}
				
				int sizeR = dqRt.size();

				for (int i = 7; i >= 8 - sizeR; i--) {
					arr[i] = dqRt.pollLast();
				}
			}else {
				String[] strSplitLt = split[0].split(":");
				for (int i = 0; i < strSplitLt.length; i++) {
					dqLt.offer(Integer.parseInt(strSplitLt[i], 16));
				}
				int sizeL = dqLt.size();
				for (int i = 0; i < sizeL; i++) {
					arr[i] = dqLt.poll();
				}
				
				
			}

			printHex();
		}

		br.close();
	}

	static void printHex() {

		for (int i = 0; i < 7; i++) {

			String hex = Integer.toHexString(arr[i]);
			if (hex.length() == 1) {
				hex = "000" + hex + ":";
			} else if (hex.length() == 2) {
				hex = "00" + hex + ":";
			} else if (hex.length() == 3) {
				hex = "0" + hex + ":";
			} else {
				hex = hex + ":";
			}
			System.out.print(hex);
		}

		String hex = Integer.toHexString(arr[7]);
		if (hex.length() == 1) {
			hex = "000" + hex;
		} else if (hex.length() == 2) {
			hex = "00" + hex;
		} else if (hex.length() == 3) {
			hex = "0" + hex;
		}
		System.out.print(hex);
	}

}
