import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Element implements Comparable<Element> {
	private int x;
	private int y;
	private int dist;

	Element(int x,int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}	
	public int getDist() {
		return this.dist;
	}
	@Override
	public int compareTo(Element o){
		if(this.dist<o.dist) {
			return -1;
		}else if(this.dist > o.dist) {
			return 1;
		}else {
			return 0;
		}
    }
}

public class KCF1 {
	static int[][] time_map;
	static int[][][] dist = new int[2520][80][50];
	static final int inf = Integer.MAX_VALUE;	
	static int move = inf;
	static int talk = inf;

	public static int[] solution(int m,int n,int s) {
		PriorityQueue<Element> q = new PriorityQueue<Element> ();
		q.offer(new Element(0,0,0));
		int[] answer = new int[2];
		while(!q.isEmpty()) {
			int cur_x = q.peek().getX();
			int cur_y = q.peek().getY();
			int len = q.peek().getDist();
			int i; // for 문 용.
			if(len > n*m) continue;
			if(dist[len][cur_x][cur_y] > s) continue;

			int newX = 0;
			int newY = 0;
			for(i=0;i<4;i++) {
				switch(i) {
					case 0:
						newX = cur_x;
						newY = cur_y -1;
						break;
					case 1:
						newX = cur_x+1;
						newY = cur_y;
						break;
					case 2:
						newX = cur_x;
						newY = cur_y+1;
						break;
					case 3:
						newX = cur_x-1;
						newY = cur_y;
						break;					
				}
				//범위에 없다면 continue;
				if(!isRange(m,n,newX,newY)) continue;
				if(time_map[newX][newY] == -1) continue;
				if(dist[len+1][newX][newY] > dist[len][cur_x][cur_y] + time_map[newX][newY]) {
					dist[len+1][newX][newY] = dist[len][cur_x][cur_y] + time_map[newX][newY];
					q.offer(new Element(newX,newY,len+1));
				}
			}
		}

		for(int k=0; k< n*m; k++) {
			if(dist[k][m-1][n-1] > s) continue;
			//Priority Queue를 사용하였으므로 찾기만 하면 바로 break;.
			move = k;
			talk = dist[k][m-1][n-1];
			break;
		}
		answer[0] = move;
		answer[1] = talk;
		return answer;
	}	
	private static boolean isRange(int m, int n, int x, int y) {
		return 0<=x && x<m && 0<=y && y<n;
	}
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int s = Integer.parseInt(br.readLine());
		int i,j;
		time_map = new int[m][n];

		for(i=0; i<m;i++) {
			for(j=0; j<n; j++) {
				time_map[i][j] = Integer.parseInt(br.readLine());
			}
		}
		Arrays.fill(dist,inf);
		int[] my_answer = solution(m,n,s);
		System.out.println("dist : "+my_answer[0]);
		System.out.println("talk : "+my_answer[1]);
	} 
}	