import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Maze {
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
	static int[][] map = new int[100][100];
    static boolean[][] visited = new boolean[100][100];
    static int N = 0;
    static int M = 0;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
    static int ans = 0;
 
    public static void main(String[] args) throws Exception{
    	Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
 
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }               
 
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                visited[i][j] = false;
            }
        }
 
        BFS(0,0);
        System.out.println(map[N - 1][M - 1]);
    }
 
    static public void BFS(int x, int y){ 
        Queue<Point> q = new LinkedList<Point>();
        
        q.add(new Point(x, y));
        //큐가 끝날때 까지
        while (!q.isEmpty()) {
            Point d = q.poll();
            
            for (int i = 0; i < 4; i++) {
                //다음 방문할 좌표 nextX, nextY
                int nextX = d.x + dx[i];
                int nextY = d.y + dy[i];
                                
                
                //다음 좌표가 범위를 넘어갈때 건너뛰기
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }
                //이미 방문했던 점이면 건너뛰기
                if (visited[nextX][nextY] == true) {
                	continue;
                }
                
                if(map[nextX][nextY] == 0) {
                    continue;
                }
                //다음에 방문할 좌표를 큐에 넣는다.
                q.add(new Point(nextX, nextY));
                
                //배열안에 다음 방문할 곳은 현재 방문했던 점보다 1칸 더 가야하므로 +1
                map[nextX][nextY] = map[d.x][d.y] + 1;
                
                //다음 좌표는 방문했음으로 표시
                visited[nextX][nextY] = true;
            }
        }
    }
}

