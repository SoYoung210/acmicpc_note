import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class B_2606 {
	static ArrayList <ArrayList<Integer>> g ; //인접 리스트
	static boolean[] visited;
	static int count = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n, m;
		n = scan.nextInt();
		m = scan.nextInt();

		g = new ArrayList<ArrayList<Integer>> ();

		for(int i = 0; i <n+1; i++){
			g.add(new ArrayList<Integer>());
		}
		visited = new boolean[n+1];
		
		for(int i = 0; i <m; i++){
			int s, d;
			s = scan.nextInt();
			d = scan.nextInt();
			g.get(s).add(d);
			g.get(d).add(s);
		}
		
		for(int i = 0; i <n+1; i++){
			visited[i] = false;
		}	

		dfs(1);
		System.out.println(count-1);
	}
	public static void dfs(int here){
		visited[here] = true;
		count++;
		for(int i : g.get(here)){
			if(!visited[i] /*&& g.get(here).size()>1*/)
				dfs(i);
		}
	}	
}