package week11;

import java.util.Arrays;
import java.util.Comparator;

public class 단속카메라 {

	public static void main(String[] args){
		단속카메라 T = new 단속카메라();
		int routes[][]= {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
		System.out.println(T.solution(routes));
	}
	public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) { // 진출시간이 같은경우 
					return o1[0]-o2[0];
				}
				return o1[1]-o2[1]; //진출시간을 기준으로 정렬
			}
		});
        for(int i=0;i<routes.length;i++) {
        	 System.out.println(Arrays.toString(routes[i]));
        }
        
        int start = routes[0][0];
        int end = routes[0][1];
       
        for(int i=1;i<routes.length;i++) {
        	if(routes[i][0]>end) {
        		answer++;
        		end=routes[i][1];
        	}
        }
        
        return answer;
    }
}
