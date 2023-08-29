package week22;

import java.util.*;

public class 미로탈출 {
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int dx[]= {-1,0,1,0};
	static int dy[]= {0,1,0,-1};
	static int n;
	static int m;
	
	static int graph[][];
	static int visited[][];
	public static void main(String[] args) {
		미로탈출 T = new 미로탈출();
		String[] maps = { "LOOXS","OOOOX","OOOOO","OOOOO","EOOOO" };
		
		System.out.println(T.solution(maps));
	}

	public int solution(String[] maps) {
		int answer = 0;

		n = maps.length;
		m = maps[0].length();

		graph=new int[n][m];
		visited=new int[n][m];
		Point start = null;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(maps[i].charAt(j)=='S') {
					graph[i][j]=1;
				}
				else if(maps[i].charAt(j)=='E') {
					
					graph[i][j]=2;
				}
				else if(maps[i].charAt(j)=='L') {
					start=new Point(i,j);
					graph[i][j]=3;
				}
				else if(maps[i].charAt(j)=='X') {
					visited[i][j]=1;
				}
			}
		}
		
		answer=BFS(start);
		
		
		return answer;
	}
	
	public int BFS(Point start) {
		
		Queue<Point> queue=new LinkedList();
		queue.offer(start);
		int leverToStart=0;
		int leverToEnd=0;
		
		int level=0;
		while(!queue.isEmpty()) {
			int size=queue.size();
			for(int s=0;s<size;s++) {
				Point cur= queue.poll();
			
				for(int i=0;i<4;i++) {
					int nx=cur.x+dx[i];
					int ny=cur.y+dy[i];
					
					if(0<=nx && nx<n && 0<=ny && ny<m) {
						if(visited[nx][ny]==0&&graph[nx][ny]==0) {
							visited[nx][ny]=1;
							queue.offer(new Point(nx,ny));
						}else if(visited[nx][ny]==0&&graph[nx][ny]==1) {
							visited[nx][ny]=1;
							queue.offer(new Point(nx,ny));
							leverToStart=level+1;
						}else if(visited[nx][ny]==0&&graph[nx][ny]==2) {
							visited[nx][ny]=1;
							queue.offer(new Point(nx,ny));
							leverToEnd=level+1;
						}
					}
					
				}
			}
			level++;
		}
		
//		System.out.println(leverToEnd);
//		System.out.println(leverToStart);
		if(leverToEnd==0||leverToStart==0) {
			return -1;
		}else {
			return leverToEnd+leverToStart;
		}
		
	}
	
}
