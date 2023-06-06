package week12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class 베스트앨범 {

	static class Album implements Comparable<Album> {
		String genres;
		int plays;
		int index;

		public Album(String genres, int plays, int index) {
			super();
			this.genres = genres;
			this.plays = plays;
			this.index = index;
		}

		@Override
		public String toString() {
			return "Album [genres=" + genres + ", plays=" + plays + ", index=" + index + "]";
		}

		@Override
		public int compareTo(Album o) {
			if (o.plays == this.plays) {
				return this.index - o.index;
			}
			return o.plays - this.plays;
		}

	}

	static LinkedHashMap<String, ArrayList<Album>> map;
	static LinkedHashMap<String, Integer> countMap;
	static HashMap<String, Integer> minidx;

	static ArrayList<Album> list;

	public static void main(String[] args) {
		베스트앨범 T = new 베스트앨범();
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		;
		System.out.println(Arrays.toString(T.solution(genres, plays)));

	}

	public int[] solution(String[] genres, int[] plays) {

		map = new LinkedHashMap<String, ArrayList<Album>>();
		countMap = new LinkedHashMap<String, Integer>();
		minidx = new HashMap<String, Integer>();
		list = new ArrayList<베스트앨범.Album>();

		for (int i = 0; i < genres.length; i++) {
			if (!map.containsKey(genres[i])) {
				map.put(genres[i], new ArrayList<Album>());
				minidx.put(genres[i], i);
			}
			ArrayList<Album> list = map.get(genres[i]);
			list.add(new Album(genres[i], plays[i], i));
			map.put(genres[i], list);

			countMap.put(genres[i], countMap.getOrDefault(genres[i], 0) + plays[i]);

		}

//		System.out.println(map);
		for (String key : map.keySet()) {
			Collections.sort(map.get(key));

		}
//		System.out.println(map);
//		System.out.println(countMap);
//		System.out.println(minidx);

		ArrayList<String> keyset = new ArrayList<>(countMap.keySet());
		keyset.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return countMap.get(o2) - countMap.get(o1);
			}
		});

		for (String key : keyset) {
			int count = 0;
			map.get(key);
			for (Album album : map.get(key)) {
//				System.out.println(album);
				list.add(album);
				count++;
				if (count == 2)
					break;
			}

		}

		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i).index;
		}

		return answer;
	}

}
