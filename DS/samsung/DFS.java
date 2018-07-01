import java.io.*;
import java.util.*;

public class DFS {
	static int Edge;
    static int Vertex; //정점의 개수 
    static int[][] vertexMap;  //인접행렬 생성 방향이 2개이다.   
    static boolean[] visited; //방문 확인 배열 
    
    public static void dfs(int i){  	
    
        visited[i] = true;   //함수 호출함과 동시에 visited 했음을 표시
        System.out.print(i+ " ");
        
        for(int j = 1; j < Vertex+1; j++){ //다른 정점과 계속 비교 
            if(vertexMap[i][j] == 1 && visited[j] == false){  // j를 방문하지 않았으면 j를 방문한다. base case
                dfs(j); //재귀 함수를 통해서 계속 한다. 
            }
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        Vertex = scan.nextInt();
        Edge = scan.nextInt();
        vertexMap = new int[Vertex+1][Vertex+1]; // 갈 수 있는 방향 
        visited = new boolean[Vertex+1]; // 방문을 했는지 확인 
        
        for(int i = 0; i < Edge; i++){
            int t1 = scan.nextInt();
            int t2 = scan.nextInt();
            
            vertexMap[t1][t2] = vertexMap[t2][t1] = 1;
        }
        
        dfs(1); // 1번부터 탐색 시작       
	}

}
