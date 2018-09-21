import java.util.*;

public class L_63 {
	static boolean[] visited;
	public static void main(String[] args) {

	}
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
	    int m = obstacleGrid.length;
	    int n = obstacleGrid[0].length;
	 	int i;
	    if(obstacleGrid[0][0]==1||obstacleGrid[m-1][n-1]==1) 
	        return 0;
	    int[][] dp = new int[m][n];
    	dp[0][0]=1;

    	for(i=1; i<n; i++) {
    		if(obstacleGrid[0][i] == 1) {
    			dp[0][i] = 0;
    		}else {
    			dp[0][i] = dp[0][i-1];
    		}
    	}

    }
	public static void dfs(int here,int[][] obstacleGrid){
		visited[here] = true;
		
		for(int i=0; i<obstacleGrid.length; i++){
			if(!visited[i] && obstacleGrid[here][i]==0) dfs(i, obstacleGrid);
		}
	}
}