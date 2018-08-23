import java.util.*;

class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class L_200 {
	static boolean[][] visited;

	public static void main (String[] args) {
		/*
		11110
		11010
		11000
		00000
		*/
		char[][] table1 = {{'1','1','1','1','0'},
		                  {'1','1','0','1','0'},
		                  {'1','1','0','0','0'},
		                  {'1','1','1','1','0'},
		                  {'0','0','0','0','0'} 
		              };
		/*
		11000
		11000
		00100
		00011
		*/
		char[][] table2 = {{'1','1','0','0','0'},
		                  {'1','1','0','0','0'},
		                  {'0','0','1','0','0'},
		                  {'0','0','0','1','1'}
		              };

		System.out.println(numIslands(table1));
		System.out.println(numIslands(table2));
	}
    public static int numIslands(char[][] grid) {
        int hang = grid.length;
        if(grid == null  || hang ==0 ) {
        	return 0;
        }
        
        int yul  = grid[0].length;
        int i,j;

        int count = 0;
        visited = new boolean[hang][yul];


        for(i=0; i<hang; i++) {
        	for(j=0; j<yul; j++) {
        		if(grid[i][j] == '1') {
        			bfs(i,j,hang,yul,grid);
        			count++;
        		}
        	}
        }
        return count;
    }

	public static void bfs(int here_x,int here_y,int m, int n, char[][] grid) {
		Queue<Pair> q = new LinkedList<Pair>();
		visited[here_x][here_y] = false; // 발견표시.
		q.offer(new Pair(here_x,here_y));

		grid[here_x][here_y] = '3';

		//이제 4방향 탐색을.....할차례야.......
		while(!q.isEmpty()) {
			Pair p = q.peek();
			q.poll();

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
					if(grid[newX][newY]=='1'&&visited[newX][newY]==false) {
						visited[newX][newY] = true;
						//여기선 그냥 의미 없어서 안쓰는 값인 3이라는 아무 숫자로 바꿈.
						grid[newX][newY] = '3';

						q.offer(new Pair(newX,newY));
					}
				}
			}
		}
	}
}
