import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class B_7576 {
	static int[][] tgarden;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int m,n;
		
		int answer;

		m = Integer.parseInt(sc.next());
		n = Integer.parseInt(sc.next());

		LinkedList<Pair> one = new LinkedList<Pair>();

		//토마토 정보 받기. 
		tgarden = new int[n][m];

		//정보로 초기화,
		for(int i=0; i<n;i++) {
			for(int j=0; j<m;j++) {
				tgarden[i][j] = Integer.parseInt(sc.next());
				if(tgarden[i][j]==1) {
					one.add(new Pair(i,j));
				}
			}
		}


		int tmp = one.size();
		for(int k=0; k<tmp;k++) {
			//1만 담겨있는 링크드 리스트에서 값을 꺼내서 bfs돌리기.
			bfs(n,m,one.get(k).x,one.get(k).y);
		}
		answer=0;
		for(int v1=0; v1<n; v1++) {
			for(int v2=0;v2<m;v2++) {
				if(answer < tgarden[v1][v2]) {answer = tgarden[v1][v2];}
				if(tgarden[v1][v2]==0) {
					answer = -1;
					break;
				}
			}
			if(answer == -1) break;
		}
		if(answer ==-1){
			System.out.println(answer);
		}else {
			answer = answer-1;
			System.out.println(answer);			
		}
	}
	public static void bfs(int m,int n,int here_x,int here_y){
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(here_x,here_y));
		//익힌거는 2로 바꿀까. 
		//tgarden[here_x][here_y] =2;
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
					if(tgarden[newX][newY]!=-1 && ( tgarden[newX][newY]==0 || tgarden[newX][newY] > tgarden[p.x][p.y]+1)) {
						//익은 토마토로 바꿔주기.
						tgarden[newX][newY] = tgarden[p.x][p.y]+1;
						
						q.offer(new Pair(newX,newY));
						//System.out.println("x : "+newX+" y: "+newY);
					}
				}

			}
		}
	}
}