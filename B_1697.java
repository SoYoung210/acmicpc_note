import java.util.LinkedList;
import java.util.Queue;
//import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_1697 {
	public static final int MAX = 1000000;
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");

		int start = Integer.parseInt(tmp[0]);
		int target = Integer.parseInt(tmp[1]);

		boolean[] visited = new boolean[MAX];
		int[] dist = new int[MAX];
		for(int i=0; i<=target;i++){
			visited[i] = false;
			dist[i] = 0;
		}

		Queue<Integer> q = new LinkedList<Integer>();
		visited[start] = true;
		q.offer(start);

		while(!q.isEmpty()) {
			int cur = q.peek();
			q.poll();
			if(cur+1 < MAX){
				 if(!visited[cur +1]) {
					visited[cur+1] = true;
					dist[cur+1] = dist[cur] + 1;

					q.offer(cur+1);
				}
			}
			if(cur-1 >=0) {
				if(!visited[cur-1]) {
					visited[cur-1] = true;
					dist[cur-1] = dist[cur] +1;

					q.offer(cur-1);
				}
			}
			if(cur*2>=0 && cur*2 < MAX) {
				if(!visited[cur*2]) {
					visited[cur*2] = true;
					dist[cur*2] = dist[cur] + 1;

					q.offer(cur*2);					
				}
			}
		}
		System.out.println(dist[target]);
	}
}