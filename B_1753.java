import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;

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

class Node {
	private int index;
	private int weight;

	Node(int index, int weight) {
		this.index = index;
		this.weight = weight;
	}
	public int getIndex() {
		return this.index;
	}
	public int getWeight() {
		return this.weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}	
}
/*
class Pinfo {
	ArrayList<Element> info = new ArrayList<Element>();
	//private int s2;

}*/

public class B_1753 {
	static ArrayList<Node>[] ad;
	static final int INF = (int)2e9;

	public static void dia(int nV, int start) {
		int[] dist = new int[nV+1];
		for(int i=1; i<=nV; i++) {
			dist[i] = INF;
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

			int ads = ad[here].size();
			for(int j=0; j<ads; j++) {
				Node tmp = ad[here].get(j);

				if(tmp.getWeight()!= -1 && dist[tmp.getIndex()] > cost + tmp.getWeight()) {
					dist[tmp.getIndex()] = cost + tmp.getWeight();
					q.offer(new Element(tmp.getIndex(), dist[tmp.getIndex()]));
				}
			}


		}
		for(int i=1; i<dist.length; i++) {
			if(dist[i]!=INF)
				System.out.println(dist[i]);
			else
				System.out.println("INF");
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//int answer = 0;
		int nV = sc.nextInt();//5
		int nE = sc.nextInt();//6
		int start = Integer.parseInt(sc.next());
		//ad = new int[nV+1][nV+1];
		
		ad = new ArrayList[nV+1];
		
		for(int j=0; j<=nV; j++) {
			ad[j] = new ArrayList();
			ad[j].add(new Node(j,-1));
		}
		for(int i=0; i<nE; i++) {
			Node tmp = null;
			int t1 = Integer.parseInt(sc.next());
			int t2 = Integer.parseInt(sc.next());
			int t3 = Integer.parseInt(sc.next());

			int count =0;

			int ads = ad[t1].size();

			for(count=0; count<ads; count++) {
				//tmp = ad[i].get(p);
				tmp = (Node)ad[t1].get(count);
				if(tmp.getIndex() == t2){
					//System.out.println("t2 :"+t2);
					break;
				}
			}
			if(count == ads) {
				ad[t1].add(new Node(t2,t3));
				continue;
			}
			if(tmp.getWeight()==-1 || tmp.getWeight() > t3) {
				tmp.setWeight(t3);
				//System.out.println("t :"+tmp.getWeight());
				ad[t1].remove(tmp);
				//안해도 되는지.. 
				ad[t1].add(tmp);
			}

		}
			
		dia(nV,start);

	}
}