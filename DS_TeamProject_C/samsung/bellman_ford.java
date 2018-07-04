//https://www.acmicpc.net/problem/11657
/* 그래프 방향은 상관이 없다.
 * 간선 간에 음의 가중치 값을 갖을 수 있다. 음의 가중치 값을 확인하는 코드를 따로 만들어야 한다. 
 * 검색 시간이 다익스트라 보다 오래 걸린다.
 * 최소 비용을 찾아야 하므로 스스로 정렬이 되는 우선순위큐를 이용한다. 
 */
package bellman_ford;

import java.io.*;
import java.util.*;


class Element implements Comparable<Element> {
	int dest;
	int cost;

	Element(int dest, int cost) {
		this.dest = dest;
		this.cost = cost;
	}

	@Override
	public int compareTo(Element o) {
		if (this.cost < o.cost)
			return -1;
		else if (this.cost == o.cost)
			return 0;
		else
			return 1;
	}
}

public class bellman_ford {
	static int V; // vertex
	static int E; // edges
	static int StartV;
	static int distance[];// 최단 거리를 저장할 변수
	static boolean visited[];
	static int inf = 1000000;

	public static void bellman_ford(ArrayList<Element>[] a) {

		StartV = 1;
		boolean negative_cycle = false;
		distance[StartV] = 0;
		
		PriorityQueue<Element> q = new PriorityQueue<Element>(); // 우선순위 큐
		q.offer(new Element(StartV, 0));
		
		while (!q.isEmpty()) {
			
			Element e = q.poll(); // 큐에 들어있는 간선중 가장 가중치가 낮은 것 찾음.
			if (visited[e.dest] == true) {
				continue;
			}
			
			visited[e.dest] = true;
			
			int i = 0;
			for (Element k : a[e.dest]) {
				//System.out.println(k.dest);
				if (visited[k.dest] == false) {
					distance[k.dest] = Math.min(distance[k.dest], distance[e.dest] + k.cost);
					q.offer(new Element(k.dest, distance[k.dest]));
				}
				if(i == E) {
					negative_cycle = true;
					break;
				}
				i++;
			}
		}
		
		if(negative_cycle == true)
			System.out.println("-1");
		
		for(int i = 2; i <= V; i++) {
			if(distance[i] == inf)
				System.out.println(-1);
			else 
				System.out.println(distance[i]);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		//StartV = sc.nextInt();

		visited = new boolean[V+1];
		distance = new int[V + 1];

		ArrayList<Element>[] a = (ArrayList<Element>[]) new ArrayList[V + 1];

		for (int i = 1; i <= V; i++) {
			visited[i] = false;
			distance[i] = inf;

			a[i] = new ArrayList<Element>();
		}

		for (int i = 0; i < E; i++) {
			int t1 = sc.nextInt();
			int t2 = sc.nextInt();
			int t3 = sc.nextInt();

			a[t1].add(new Element(t2, t3));
		}

		bellman_ford(a);

//		for (int i = 1; i <= V; i++) {
//			if (distance[i] == inf) {
//				System.out.println("INF");
//			} else {
//				System.out.println(distance[i]);
//			}
//		}
	}

}