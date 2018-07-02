import java.io.*;
import java.util.*;


public class BFS {
	static int Edge;
    static int Vertex; //정점의 개수 
    static int[][] vertexMap;  //인접행렬 생성 방향이 2개이다.   
    static boolean[] visited; 
    
    public static void bfs(int i){
        Queue <Integer> q = new <Integer> LinkedList();
        
        q.offer(i);
        visited[i] = true; //방문을 하였다는 것을 적는다. 
        
        while(!q.isEmpty()){
            int temp = q.poll();
            System.out.print(temp+" ");
            
            for(int j = 1; j <= Vertex; j++){
                if(vertexMap[temp][j] == 1 && visited[j] == false){
                    q.offer(j);
                    visited[j] = true;
                    }
                }
            
            }    
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Vertex = scan.nextInt();
        Edge = scan.nextInt();
        vertexMap = new int[Vertex+1][Vertex+1];
        visited = new boolean[Vertex+1];
        
        for(int i = 0; i < Edge; i++){
            int t1, t2;
            t1 = scan.nextInt();
            t2 = scan.nextInt();
            
            vertexMap[t1][t2] = vertexMap[t2][t1] = 1; //인접 행렬을 1로 만든다. 
        }
        
        bfs(1);
        
    }

}
