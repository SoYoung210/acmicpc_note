import java.util.*;


class Element implements Comparable<Element> {
	private int index;
	private int dist;

	Element(int index, int dist) {
		this.index = index;
		this.dist = dist;
	}
	public int getIndex() {
		return this.index;
	}
	public int getDist() {
		return this.dist;
	}
	public int compareTo(Element o){
		if(distance<o.distance) {
			return -1;
		}else if(distance > o.distance) {
			return 1;
		}else {
			return 0;
		}
       // return distance <= o.distance ? -1 : 1;
    }
}

public class Dia {
	static final int inf =100000;
	//nv = 개수, start = 시작지점, dist = 각 정점의 시작점부터의 거리 , ad = 인접행렬. 
	//void -> int[]
	public static void dia(int nV,int start, int[] dist, int[][] ad) {

		for(int i=0; i<=nV; i++) {
			dist[i] = inf;
		}		

		PriorityQueue <Element> q = new <Element> PriorityQueue();
		dist[start] = 0;
		q.offer(new Element(start, dist[start]));

		while(!q.isEmpty()) {
			int cost = q.peek().getDist();
			int here = q.peek().getIndex();
			q.poll();

			if(cost > dist[here])
				continue;
			//방문했다.
			System.out.println(here);

			for(int i=0; i<=nV; i++) {
				if(ad[here][i] != 0 && dist[here] > dist[here] + ad[here][i]) {
					dist[i] = dist[here] + ad[here][i];
					q.offer(new Element(i, dist[i]));
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nV = Scanner.nextInt();
		int nE = Scanner.nextInt();
		
		int[] dist = new int[nV+1];
		int[][] ad = new int[nV+1][nV+1];

		for(int i=0; i<nE; i++) {
			int t1 = sc.nextInt();
			int t2 = sc.nextInt()
			int t3 = sc.nextInt();

			ad[t1][t2] = t3;
		}
		dia(1);
	}

}