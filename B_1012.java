import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.HashMap;


class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class B_1012 {
	static int[][] garden;
	//방문여부르르..
	static boolean visited[][];
	//넌 몇번째 단지니.. == 여기선 벌레 개수.
	static int count;
	static int[] answer;

	public static void bfs(int here_x,int here_y,int m,int n) {
		Queue<Pair> q = new LinkedList<Pair>();
		visited[here_x][here_y] = false; // 발견표시.
		q.offer(new Pair(here_x,here_y));

		//배열값을 count번째 단지를 의미하는 것으로 바꿈.
		//여기선 그냥 의미 없어서 안쓰는 값인 3이라는 아무 숫자로 바꿈.
		garden[here_x][here_y] = 3;
		//벌레 발견.
		//answer[count]++;
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
				if(newX>=0 && newY>=0 && newX<=m-1 && newY<=n-1) {
					if(garden[newX][newY]==1 && visited[newX][newY]==false) {
						visited[newX][newY] = true;
						//여기선 그냥 의미 없어서 안쓰는 값인 3이라는 아무 숫자로 바꿈.
						garden[newX][newY] = 3;

						//result.put(count,result.get(count)+1);
						//answer[count]++;
						q.offer(new Pair(newX,newY));
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		//int[] answer;

		Scanner sc = new Scanner(System.in);
		//test case 개수.
		int t;
		t = sc.nextInt();
		//배추벌레 개수. 테스트당. 
		answer = new int[t];


		for(int i=0; i<t; i++) {
			//sc.next();
			//String[] tmp = sc.nextLine().split(" ");
			int n,m,k;
			n = Integer.parseInt(sc.next());
			m = Integer.parseInt(sc.next());
			k = Integer.parseInt(sc.next());
			//System.out.println("n : "+n+"m : "+m+"k : "+k);
			init(n,m);

			for(int j=0; j<k; j++) {
				//String[] tmp1 = sc.nextLine().split(" ");
				int v1,v2;
				v1 = Integer.parseInt(sc.next());
				v2 = Integer.parseInt(sc.next());
				//System.out.println("v1 : "+v1+"v2 : "+v2);
				garden[v1][v2] = 1;
			}
			for(int a=0; a<n; a++) {
				for(int b=0; b<m; b++) {
					if(garden[a][b]==1) {
						bfs(a,b,n,m);
						count++;
					}
				}
			}
			//count++;
			//System.out.println("===============");
			answer[i] = count;
		}
		for(int p =0; p<t; p++) {
			System.out.println(answer[p]);
		}

	}
	public static void init(int n, int m) {
		count = 0;
		garden = new int[n][m];
		visited = new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				garden[i][j] = 0;
				visited[i][j] = false;
			}
		}
		//count = 0;
	}
}