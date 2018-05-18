import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.LinkedList;

class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class B_2178 {
	static int[][] map;
	static boolean[][] visited;
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp= br.readLine().split(" ");
		int t_x = Integer.parseInt(tmp[0]);
		int t_y = Integer.parseInt(tmp[1]);

		map = new int[t_x][t_y];
		visited = new boolean[t_x][t_y];

		for(int i=0; i<t_x;i++) {
			String str = br.readLine();
			char[] ch = str.toCharArray();
			for(int j=0;j<t_y; j++) {
				map[i][j] = ch[j] - '0';
			}
		}
		bfs(t_x,t_y);
		System.out.println(map[t_x -1][t_y -1]);
	}
	public static void bfs(int x, int y) {
		//System.out.println("x : "+x + "y :"+y);
		int answer = 0;
		Queue<Pair> q = new LinkedList<Pair>();

		visited[0][0] = true;
		q.offer(new Pair(0,0));

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
				if(newX>=0 && newY>=0 && newX<=x-1 && newY<=y-1) {
					if(map[newX][newY]!=0 && (map[newX][newY]==1 || map[newX][newY] > map[p.x][p.y]+1)) {
						//익은 토마토로 바꿔주기.
						map[newX][newY] = map[p.x][p.y]+1;
						//System.out.println(map[newX][newY]);
						q.offer(new Pair(newX,newY));
						//System.out.println("x : "+newX+" y: "+newY);
					}
				}			
			}
		}
	}
}

