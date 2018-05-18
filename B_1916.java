import java.util.PriorityQueue;
import java.util.Scanner;

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
	@Override
	public int compareTo(Element o){
		if(this.dist<o.dist) {
			return -1;
		}else if(this.dist > o.dist) {
			return 1;
		}else {
			return 0;
		}
       // return distance <= o.distance ? -1 : 1;
    }
}


class B_1916 {
	//인접행렬.
	static int[][] ad;

	static final int inf =(int)2e9;

	public static int dia(int nV,int start, int goal) {
		int[] dist = new int[nV+1];
		for(int i=1; i<=nV; i++) {
			dist[i] = inf;
			//System.out.println("dist : "+dist[nV]);
		}

		PriorityQueue<Element> q = new PriorityQueue<Element> ();
		dist[start] = 0;
		q.offer(new Element(start, dist[start]));

		while(!q.isEmpty()) {
			int cost = q.peek().getDist();
			int here = q.peek().getIndex();
			q.poll();

			if(cost > dist[here])
				continue;

			for(int i=1; i<=nV; i++) {
				//ystem.out.println("dist : "+dist[here] +"  "+ ad[here][i]+ " : "+here);
				if(ad[here][i] != -1 && dist[i] > cost + ad[here][i]) {
					dist[i] = cost + ad[here][i];//
					q.offer(new Element(i, dist[i]));
				}
			}			
		}

		return dist[goal];
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int nV = sc.nextInt();//5
		int nE = sc.nextInt();//8

		//나중에 인접리스트로 바꿔볼까 ㅎ
		ad = new int[nV+1][nV+1];

		for(int j=0; j<=nV; j++) {
			for(int k=0; k<=nV; k++) {
				ad[j][k] = -1;
			}
		}
		for(int i=0; i<nE; i++) {
			int t1 = Integer.parseInt(sc.next());
			int t2 = Integer.parseInt(sc.next());
			int t3 = Integer.parseInt(sc.next());
			/*
			if(ad[t1][t2]==-1){
				ad[t1][t2] = t3;
				continue;
			}
			if(ad[t1][t2] > t3)
				ad[t1][t2] = t3;*/
			if(ad[t1][t2] == -1 || ad[t1][t2] > t3) ad[t1][t2] = t3;
		}

		int start = Integer.parseInt(sc.next());
		int goal = Integer.parseInt(sc.next());
		System.out.println(dia(nV,start,goal));
	}
}