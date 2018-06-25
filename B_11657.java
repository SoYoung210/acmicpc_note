import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다. 각 버스는 A, B, C로 나타낼 수 있는데, 
A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다. 시간 C가 양수가 아닌 경우가 있다.
C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.

1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 둘
째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 

출력
첫째 줄에는 2번 도시까지 가는 가장 빠른 시간, ..., N-1번째 줄에는 N번 도시까지 가는 가장 빠른 시간을 출력한다. 
어떤 도시로 가는 가장 빠른 시간이 없는 경우에는 -1을 출력한다.

만약, 시작점에서 도달 가능한 타임머신으로 되어있는 사이클이 존재해 1번 도시에서 나머지 도시로 가는 가장 빠른 시간이 존재하지 않는 경우에는 -1을 출력한다.

3 4
1 2 4
1 3 3
2 3 -1
3 1 -2
*/
class Edge {
	private int u;
	private int	v;
	private int w;

	Edge(int u,int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}
	public int first() {
		return this.u;
	}
	public int second() {
		return this.v;
	}
	public int weight() {
		return this.w;
	}		
}

public class B_11657 {
	static final int inf = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		ArrayList<Edge> g = new ArrayList<Edge>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in_fist = br.readLine().split(" ");
		int n = Integer.parseInt(in_fist[0]);
		int m= Integer.parseInt(in_fist[1]);

		int[] dist = new int[n+1];
		int i;
		for(i=1;i<=n;i++) 
			dist[i] = inf;

		for(i=1;i<=m;i++) {
			String[] tmp = br.readLine().split(" ");
			int first = Integer.parseInt(tmp[0]);
			int second = Integer.parseInt(tmp[1]);
			int weight = Integer.parseInt(tmp[2]);

			g.add(new Edge(first,second,weight));
		}
		// 시작 dist = 0 이므로 초기화. 
		dist[1] = 0;

		for(i=0; i< n; i++) {
			boolean updated = false;
			//모든 정보를 순회.
			for(Edge e : g) {
				if(dist[e.first()]!=inf && dist[e.second()] > dist[e.first()] + e.weight()) {
					dist[e.second()] = dist[e.first()] + e.weight();
					updated = true;
				}
			}
			if(!updated) break;

			if(i == n-1) {
				System.out.println("-1");
				return;
			}
		}
		for(i=2; i<=n;i++) {
			System.out.println(dist[i]==inf? -1 : dist[i]);
		}
	}
}