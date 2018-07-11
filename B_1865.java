import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//도로는 양방향성.  --> 고쳐야 할 사항. 
//문제가 좀 잘못된듯. 

class Edge {
	//start, destination, weight
	private int s;
	private int	d;
	private int w;

	Edge(int s,int d, int w) {
		this.s = s;
		this.d = d;
		this.w = w;
	}
	public int start() {
		return this.s;
	}
	public int dest() {
		return this.d;
	}
	public int weight() {
		return this.w;
	}
}

public class B_1865 {

	public static List<Edge> g = new ArrayList<Edge>();
	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {

		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int test = Integer.parseInt(br.readLine());
			String[] answer = new String[test];
			int i;
			
			for(i=0;i<test;i++) {
				System.out.println("===test===");
				String[] nmw = br.readLine().split(" ");
				
				int n = Integer.parseInt(nmw[0]); //3
				int m = Integer.parseInt(nmw[1]); //3
				int w = Integer.parseInt(nmw[2]); //1
				int[] dist = new int[n+1];
				Arrays.fill(dist,INF);
				// 2 3 4

				for (i = 1; i <= m; i++) { 
					String[] set = br.readLine().split(" ");
			
					int s1 = Integer.parseInt(set[0]);
					int e1 = Integer.parseInt(set[1]);
					int t1 = Integer.parseInt(set[2]);

					g.add(new Edge(s1,e1,t1));
					g.add(new Edge(e1,s1,t1));
				} 
				for (i = 1; i <= w; i++) { 
					String[] set2 = br.readLine().split(" ");

					int s2 = Integer.parseInt(set2[0]);
					int e2 = Integer.parseInt(set2[1]);
					int t2 = Integer.parseInt(set2[2]);	
					g.add(new Edge(s2,e2,t2*(-1)));	
				}				
				
				boolean minus = BellmanFord(dist,g,n,(m+w),1);
				System.out.println( (minus) ? "YES" : "NO" );
				g.clear();
			}
			System.out.println("===out===");
		}catch(IOException ex) {
			System.out.println("===error===");
			ex.printStackTrace();
		}
	}
	public static boolean BellmanFord(int[] dist, List<Edge> edge, int V, int E, int StartNode) { 
		boolean hasMinusCycle = false; 

		dist[1] = 0;
		dist[StartNode] = 0; 
		for(int i = 1; i <= V; i++) { 
			for(Edge nowEdge : edge) { 
				int from = nowEdge.start(); 
				int to = nowEdge.dest(); 
				int weight = nowEdge.weight(); 
				if(dist[from] == INF) 
					continue; 
				if(dist[to] > dist[from] + weight) { 
					dist[to] = dist[from] + weight; 
					if(i == V) hasMinusCycle = true; 
				} 
			} 
		} 
		return hasMinusCycle; 
	}
}