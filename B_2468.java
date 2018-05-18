import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class B_2468 {
	static int[][] map;
	static LinkedList<Integer> rain;
	static boolean[][] tmpvisit;
	static int min;

	public static void main (String[] args) throws Exception {
		min = 0;
		rain = new LinkedList<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int s = Integer.parseInt(br.readLine());

		map = new int[s][s];
		tmpvisit = new boolean[s][s];
		for(int i=0; i<s;i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0;j<s; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(!rain.contains(map[i][j])) {
					rain.add(map[i][j]);

				}
			}
		}

		Collections.sort(rain);

		int rs = rain.size();
		int count = 2;

		for(int k=0; k<rs; k++) {
			count = 2;
			for(int l=0; l<s; l++) {
				for(int m=0; m<s; m++) {
					if(map[l][m] > rain.get(k) && !tmpvisit[l][m]){
						bfs(s,l,m,rain.get(k));
						count++;
					}
				}
			}
			if(min<count){
				min = count;
			}
			for(boolean[] row : tmpvisit) {
				Arrays.fill(row,false);
			}
		}
		if(min-2!=0)
			System.out.println(min-2);
		else
			System.out.println("1");
		//bfs(s);
		
	}
	public static void bfs(int s, int here_x, int here_y, int deep) {
		//System.out.println("x : "+deep);
		int answer = 0;
		Queue<Pair> q = new LinkedList<Pair>();
		boolean[][] visited = new boolean[s][s];

		visited[here_x][here_y] = true;
		q.offer(new Pair(here_x,here_y));

		while(!q.isEmpty()) {
			Pair p = q.peek();
			q.poll();

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
				if(newX>=0 && newY>=0 && newX<=s-1 && newY<=s-1) {
					if(map[newX][newY] > deep && visited[newX][newY]==false) {
						//익은 토마토로 바꿔주기.
						visited[newX][newY] = true;
						//map[newX][newY] = map[p.x][p.y]+1;
						//System.out.println(map[newX][newY]);
						q.offer(new Pair(newX,newY));
						tmpvisit[newX][newY] = true;
						//System.out.println("x : "+newX+" y: "+newY);
					}
				}			
			}
		}
	}
}

