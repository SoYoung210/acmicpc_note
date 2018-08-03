import java.util.*;
import java.util.stream.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Pair{
	int x;
	int y;
	int color;
	Pair(int x, int y, int color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
}

// bfs , max 영역업데이트 / 개수는 count로 세기. 
public class K_1 {
	static int[][] map;
	public static void main (String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String[] tmp = br.readLine().split(" ");
			int m = Integer.parseInt(tmp[0]);
			int n = Integer.parseInt(tmp[1]);

			int i,j;

			map = new int[m][n];
			int maxarea = 0;
			int maxcolor = 0;
			List<Pair> colorlist = new LinkedList<>();

			//입력 받기 
			for(i=0; i<m; i++) {
				String tmp3 = br.readLine();
				for(j=0; j<n; j++) {
					int c = tmp3.charAt(j) - '0';
					map[i][j] = c;
					if(c!='0') colorlist.add(new Pair(i,j, c-'0'));
				}
			}

			for(i=0; i< colorlist.size(); i++) {
				boolean change = false;
				Pair tmp2 = colorlist.get(i);
				int tmpcolor = tmp2.color;
				int tmparea = bfs(m,n, tmp2.x, tmp2.y, tmpcolor);

				if(tmparea > maxarea) {
					change = true;
					maxarea = tmparea;
				}
			}
			System.out.println("maxcolor :"+maxcolor);
			System.out.println("maxarea :"+maxarea);
		}catch(Exception e) {

		}
	}
	private static int bfs(int m, int n, int here_x, int here_y, int here_color) {
		Queue<Pair> q = new LinkedList<Pair>();
		boolean[][] visited = new boolean[m][n];

		visited[here_x][here_y] = true;

		int color = map[here_x][here_y];
		int area = 0;
		q.offer(new Pair(here_x,here_y, here_color));

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
				if(newX>=0 && newY>=0 && newX<=m-1 && newY<=n-1) {
					if(visited[newX][newY]==false && map[newX][newY] == color) {
						visited[newX][newY] = true;
						q.offer(new Pair(newX,newY, here_color));
						area++;
					}
				}			
			}
		}
		return area;	
	}
}