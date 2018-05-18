import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.HashMap;

//아 큐에 들어갈게 두개니까 그냥 클래스로 만들까. 
class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class B_2667{

	static int map[][];
	//첫번째줄 입력받을거
	static int n;
	//각 단지 사이즈 어레이리스트. 몇개있을지 모르니까
	//static ArrayList <Integer> result = new ArrayList<Integer>();
	static HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
	//방문여부르르..
	static boolean visited[][];
	//넌 몇번째 단지니..
	static int count = 0;

	public static void bfs(int here_x,int here_y) {
		Queue<Pair> q = new LinkedList<Pair>();
		visited[here_x][here_y] = false; // 발견표시.
		q.offer(new Pair(here_x,here_y));

		//배열값을 count번째 단지를 의미하는 것으로 바꿈.
		map[here_x][here_y] = count;
		//첫번째 집 발견. 
		//result.put(count,1);
		if(result.containsKey(count)) {
			result.put(count,result.get(count)+1);
		}else {
			result.put(count,1);	
		}
		//result.add(count);

		//이제 4방향 탐색을.....할차례야.......
		while(!q.isEmpty()) {
			Pair p = q.peek();
			q.poll();
			//단지에서 아파트 개수. 
			//4 directions search.
			int newX=0;
			int newY=0;
			for(int i=0; i<4;i++) {
				//0==상 1==우 2 == 하 3==좌 (위부터 시계방향.)
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
				if(newX>=0 && newY>=0 && newX<=n-1 && newY<=n-1) {
					if(map[newX][newY]==1 && visited[newX][newY]==false) {
						visited[newX][newY] = true;
						map[newX][newY] = count;

						result.put(count,result.get(count)+1);
						q.offer(new Pair(newX,newY));
					}
				}
			}
		}
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		visited = new boolean[n][n];
		//System.out.println(n);
		for(int i=0; i<n; i++) {
			String aptinfo = sc.next();
			char ch[]= aptinfo.toCharArray();
			for(int j=0; j<n; j++) {
				//System.out.println(ch[j]);
				map[i][j] = ch[j] - '0';
			}
		}
		//???????????????1이면 기존 값이랑 꼬여서...
		count = 2;
		for(int i =0; i<n;i++) {
			for(int j =0; j<n; j++){
				if(map[i][j]==1) {
					bfs(i,j);
					count++;
				}
			}
		}
		//??????????
		System.out.println(count-2);
		ArrayList<Integer> valuesList = new ArrayList<Integer>(result.values());
		Collections.sort(valuesList);
		int listleng = valuesList.size();
		for(int i=0; i<listleng;i++) {
			System.out.println(valuesList.get(i));
		}
	}
}