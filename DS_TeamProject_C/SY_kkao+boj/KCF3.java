import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//0702 
//Flag가 참일때까지 BFS 반복 (백준 7576의 BFS 4방향 탐색에서 기본 아이디어를 따온다.)
//만약 같은 글자면 전부 소문자로 바꿔주면서..탐색하고... 
//4방향 소문자 만족되면 ..  ?? 
//한번 탐색다하고나서 공백이거나 소문자인 곳 다 지워주기. 
class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class KCF3 {
	public int solution(int m, int n, String[] board) {
		int answer = 0;
		char[][] map = new char[m][n];
		Queue<Pair> q = new LinkedList<>();
		int[] dx = {1,0,1};
		int[] dy = {}
		int i,j;
		//input 바꿔주기.
		for(i=0; i<m; i++) {
			for(j=0; j<n; j++) {
				map[i][j] = board[i].charAt(j);
			}
		}
		q.add(new Pair(0,0));
		boolean changeFalg = true;
		boolean block4;
		while(changeFalg) {
			//4개의 블록일때 왼쪽 위를 기준으로 잡으니까, 크기가 5*5라면 4*4까지만 탐색하면 된다.
			for(i=0;i<m-1;i++) {
				for(j=0;j<n-1;j++) {
					block4 = true;

					for(k=0; k<3; k++) {
						char cur = (map[i][j]);
						if(cur != (map[i+dx[k]][j+dy[k]])) {
							block4 = false;
						}
						
					}
					if(block4) {
						map[ i ][ j ] = " ";
						map[i+1][j+1] = " ";
						map[ i ][j+1] = " ";
						map[i+1][ j ] = " ";
					}
				}
			}
			//공백부분을 체크해서 위에있는 블록 떨어트리기. 5*5 전체를 탐색해야 한다.
			for(i=0;i<m;i++) {
				for(j=0;j<n;j++) {
					
				}
			}		
		}

		return answer;
	}
	boolean isRange(int m, int n, int x, int y) {
		return x>=0 && x<m && y>=0 && y<n;
	}
	public static void main (String[] args) throws Exception {

	}
}