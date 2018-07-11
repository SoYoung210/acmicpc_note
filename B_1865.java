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

	public static ArrayList<Edge> g = new ArrayList<Edge>();
	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {

		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int test = Integer.parseInt(br.readLine());
			String[] answer = new String[test];
			int i,j,k;
			
			for(i=0;i<test;i++) {
				boolean minus = false;

				String[] nmw = br.readLine().split(" ");
				
				int n = Integer.parseInt(nmw[0]); //3
				int m = Integer.parseInt(nmw[1]); //3
				int w = Integer.parseInt(nmw[2]); //1
				int[] dist = new int[n+1];
				Arrays.fill(dist,INF);
				// 2 3 4
				for(j=2;j<=m+1;j++) {
					String[] set = br.readLine().split(" ");
			
					int s1 = Integer.parseInt(set[0]);
					int e1 = Integer.parseInt(set[1]);
					int t1 = Integer.parseInt(set[2]);

					Edge e = new Edge(s1,e1,t1);
					Edge ee = new Edge(e1,s1,t1);
					if(g.contains(e)) {
						int index = g.indexOf(e);
						if(t1 < g.get(index).weight()) {
							g.remove(index);
							g.add(e);
						}else {
							e = null;
						}
					}else {
						g.add(e);
					}
					if(g.contains(ee)) {
						int index = g.indexOf(ee);
						if(t1 < g.get(index).weight()) {
							g.remove(index);
							g.add(ee);
						}else {
							ee = null;
						}
					}else {
						g.add(ee);
					}				
				}
				// 5 
				for(j=m+2;j<=m+w+1;j++) {
					String[] set2 = br.readLine().split(" ");

					int s2 = Integer.parseInt(set2[0]);
					int e2 = Integer.parseInt(set2[1]);
					int t2 = Integer.parseInt(set2[2]);	
					g.add(new Edge(s2,e2,t2*(-1)));			
				}
				dist[1] = 0;
				for(k=1;k<=n;k++) {
					boolean updated = false;
					//모든 정보를 순회.
					for(Edge e : g) {
						if(dist[e.start()]!=INF && dist[e.dest()] > dist[e.start()] + e.weight()) {
							dist[e.dest()] = dist[e.start()] + e.weight();
							updated = true;
						}
					}
					if(!updated) { //System.out.println("NO");
						minus = false;
						break;
					}
					if(k == n-1) {
						minus = true;
					}				
				}
				System.out.println( (minus) ? "YES" : "NO" );
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		/*
		for(int a=0; a<test; a++) {
			System.out.println(answer[a]);
		}*/
	}
}