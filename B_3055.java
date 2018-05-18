import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_3055 {
	static int[][] map;
	
	static LinkedList<Pair> rain;
	public static void main (String[] args) throws Exception {
		int start_x = 0;
		int start_y = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		int x = Integer.parseInt(tmp[0]);
		int y = Integer.parseInt(tmp[1]);

		map = new int[x][y];
		rain = new LinkedList<Pair>();
		for(int i=0; i<x; i++) {
			String str = br.readLine();
			for(int j=0; j<y; j++) {
				char info= str.charAt(j);
				if(info == 'S') {
					start_x = i; start_y = j;
					map[i][j] = 0;
				}else if(info == 'D') {
					map[i][j] = Integer.MAX_VALUE;
				}else if(info =='.') {
					map[i][j] = 0;
				}else if(info == '*') {
					map[i][j] = -1;
					rain.offer(new Pair(i,j));
				}else if(info == 'X') {
					map[i][j] = Integer.MAX_VALUE-1;
				}
			}
		}
		bfs(x,y,start_x,start_y);
		int max = 0;
		for(int k=0; k<x; k++) {
			for(int o =0; o<y; o++) {
				if(map[k][o] < Integer.MAX_VALUE-1) {
					//System.out.println(map[k][o]);
					if(max < map[k][o])
						max = map[k][o];
				}
			}
		}
		System.out.println(max);
	}

	public static void bfs(int x,int y, int dochi_x, int dochi_y) {
		//System.out.println("x : "+deep);
		int answer = 0;
		Queue<Pair> q2 = new LinkedList<Pair>();
		//boolean[][] visited = new boolean[x][y];
		boolean[][] visited2 = new boolean[x][y];


		visited2[dochi_x][dochi_y] = true;
		q2.offer(new Pair(dochi_x,dochi_y));

		while(!rain.isEmpty() || !q2.isEmpty()) {
			if(!rain.isEmpty()) {
				Pair p = rain.peek();
				rain.poll();

				int newX=0;
				int newY=0;			
				for(int i=0; i<4;i++) {
					//0==상 1==우 2 == 하 3==좌 (위부터 시계방향.)
					//인접한 개수가 몇개인지..?
					switch(i){
						case 0:
							newX = p.x;
							newY = p.y -1;
							break;
						case 1:
							newX = p.x+1;
							newY = p.y;
							break;
						case 2:
							newX = p.x;
							newY = p.y+1;
							break;
						case 3:
							newX = p.x-1;
							newY = p.y;
							break;
					}
					//새로운 x,y가 범위내에 있을 경우. 
					if(newX>=0 && newY>=0 && newX<=x-1 && newY<=y-1) {
						if(map[newX][newY] != Integer.MAX_VALUE-1 && map[newX][newY] != Integer.MAX_VALUE
							&& (map[newX][newY]==-1 || map[newX][newY] < map[p.x][p.y] - 1 )) {
							//익은 토마토로 바꿔주기.
							map[newX][newY] = map[p.x][p.y] - 1;
							//System.out.println(map[newX][newY]);
							rain.offer(new Pair(newX,newY));
							//System.out.println("x : "+newX+" y: "+newY);
						}
					}			
				}
			}
			if(!q2.isEmpty()) {
				Pair p2 = q2.peek();
				q2.poll();

				int newX2 = p2.x;
				int newY2=p2.y;

				//여기서 두더지 집인지 검사하기.
				if(map[newX2][newY2] == Integer.MAX_VALUE) {
					return;
				}

				for(int i=0; i<4;i++) {
					//0==상 1==우 2 == 하 3==좌 (위부터 시계방향.)
					//인접한 개수가 몇개인지..?
					switch(i){
						case 0:
							newX2 = p2.x;
							newY2 = p2.y -1;
							break;
						case 1:
							newX2 = p2.x+1;
							newY2 = p2.y;
							break;
						case 2:
							newX2 = p2.x;
							newY2 = p2.y+1;
							break;
						case 3:
							newX2 = p2.x-1;
							newY2 = p2.y;
							break;
					}
					//새로운 x,y가 범위내에 있을 경우. 
					if(newX2>=0 && newY2>=0 && newX2<=x-1 && newY2<=y-1) {
						if(map[newX2][newY2] != Integer.MAX_VALUE-1 && map[newX2][newY2] >=0
							&& visited2[newX2][newY2]==false) {
							//익은 토마토로 바꿔주기.
							visited2[newX2][newY2] = true;
							map[newX2][newY2] = map[p2.x][p2.y] + 1;
							//System.out.println(map[newX][newY]);
							q2.offer(new Pair(newX2,newY2));
							//System.out.println("x : "+newX+" y: "+newY);
						}
					}			
				}				
			}
		}
	}	
}