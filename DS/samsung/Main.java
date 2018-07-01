//구슬 탈출 
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
 
class Main {
 
    static int MAX = 10;
 
    static class Point {
        public int x, y;
 
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
         
        Point()
        {
            this.x=0;
            this.y=0;
        }
    }
     
    static class Queue_Data {
        Point R;
        Point B;
        int count;
         
         
        Queue_Data(Point R, Point B, int count)
        {
            this.R = new Point(R.x, R.y);
            this.B = new Point(B.x, B.y);
            this.count = count;
        }
    }
    public static void bfs(Queue_Data q) {
    	
    }
 
    public static void main(String args[]) throws Exception {
         
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new FileInputStream("input.txt"));
        int n, m;
        n=sc.nextInt();
        m=sc.nextInt();
        
        char map[][]=new char[n][m];
        Point R =new Point();
        Point B=new Point();
         
        for (int i = 0; i < n; i++)
        {
            String tmp = sc.next();
            for (int j = 0; j < m; j++)
            {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'B')
                {
                    map[i][j] = '.';
                    B.x = i; B.y = j;
                }
                if (map[i][j] == 'R')
                {
                    map[i][j] = '.';
                    R.x = i; R.y = j;
                }
            }
        }
         
        int ans = -1;
         
        boolean visit[][][][] = new boolean[MAX][MAX][MAX][MAX]; //(Rx, Ry, Bx, By) 탐색했는지 여부 저장
        for(int i=0;i<MAX;i++)
            for(int j=0;j<MAX;j++)
                for(int k=0;k<MAX;k++)
                    Arrays.fill(visit[i][j][k], false);
        
        visit[R.x][R.y][B.x][B.y] = true;
        
 
        int dx[] = new int[] { -1,1,0,0 }; //0:상 1:하 2:좌 3:우  
        int dy[] = new int[] { 0,0,-1,1 };
         
        Queue<Queue_Data> q = new LinkedList<Queue_Data>(); //Red Point,Blue Point, count
        q.add(new Queue_Data(R,B,0));
        
        //BFS
        while (!q.isEmpty())
        {
            Queue_Data now = q.poll();
            R = new Point(now.R.x, now.R.y);
            B = new Point(now.B.x, now.B.y);
            int count = now.count + 1;
             
            if (count > 10) continue;//10번 넘어가면 안됨
 
            for (int i = 0; i < 4; i++) //4방향으로 확인한다. 
            {
                Point nR = new Point(R.x, R.y);
                Point nB = new Point(B.x, B.y);
 
                while (map[nR.x + dx[i]][nR.y + dy[i]] != '#')
                {
                    nR.x += dx[i];
                    nR.y += dy[i];
                    if (map[nR.x][nR.y] == 'O') break;
                }
                while (map[nB.x + dx[i]][nB.y + dy[i]] != '#') 
                {
                    nB.x += dx[i];
                    nB.y += dy[i];
                    if (map[nB.x][nB.y] == 'O') break;
                }
 
                //파란 공 나옴
                if (map[nB.x][nB.y] == 'O') {
                	System.out.println(-1);
                	System.exit(0);//종료
                }                	
                    
                //빨간 공만 빠져나옴
                if (map[nR.x][nR.y] == 'O')
                {
                    System.out.println(count);
                    System.exit(0);//종료
                }
                 
                //겹친 경우
                if (nR.x == nB.x && nR.y == nB.y)
                {
                	if(dx[i] == -1){ //위로 이동  
                		if(R.x > B.x) nR.x++; //빨간 구슬 파란 구슬보다 아래에 있을 경우   
                		else nB.x++;
                	}
                	else if(dx[i] == 1) { //아래로 이동  
                		if(R.x > B.x) nB.x--; //빨간 구슬이 파란 구슬보다 아래에 있을 경우  
                		else nR.x--;
                	}
                	else if(dy[i] == -1) { //좌로 이동 
                		if(R.y > B.y) nR.y++; //빨간 구슬이 오른쪽에 있을 경우 
                		else nB.y++;
                	}
                	else {//우로 이동  
                		if(R.y > B.y) nB.y--;
                		else nR.y--;
                	}                   
                }
 
                //방문 안한 것만 큐에 push
                if (visit[nR.x][nR.y][nB.x][nB.y] == true) continue;
                 
                q.add(new Queue_Data(nR,nB,count));
                visit[nR.x][nR.y][nB.x][nB.y] = true;
            }
        }
        System.out.println(ans);
         
    }
}