import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
첫째 줄에 F, S, G, U, D가 주어진다. 
(1 ≤ S, G ≤ F ≤ 1000000, 0 ≤ U, D ≤ 1000000) 
건물은 1층부터 시작하고, 가장 높은 층은 F층이다.
*/

public class B_5014 {
	static boolean[] visited;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		int f = Integer.parseInt(tmp[0]);
		int s = Integer.parseInt(tmp[1]);
		int g = Integer.parseInt(tmp[2]);
		int u = Integer.parseInt(tmp[3]);
		int d = Integer.parseInt(tmp[4]);

		visited = new boolean[f+1];

		int i;
		for(i = 0; i <=f; i++) {
			visited[i] = false;
		}

		bfs(f,s,g,u,d);		
	}

	public static void bfs(int f, int s, int g, int u,int d) {
		Queue<Integer> q = new LinkedList<Integer>();
		visited[s] = true;// 발견 표시
		q.offer(s);
		int[] dist = new int[f+1];

		while(!q.isEmpty()) {
			int cur = q.peek();
			q.poll();

			if(cur == g) {
				System.out.println(dist[g]);
				return;
			}
			int goup = cur + u;
			int godown = cur - d;

			if(goup <=f && !visited[goup]) {
				visited[goup] = true;
				dist[goup] = dist[cur] +1;
				q.offer(goup);
			}

			if(godown >=1 && !visited[godown]) {
				visited[godown] = true;
				dist[godown] = dist[cur] +1;
				q.offer(godown);
			}

		}
		if(dist[g] ==0 ){
			System.out.println("use the stairs");
		}else {
			System.out.println(dist[g]);
		}
	}
}