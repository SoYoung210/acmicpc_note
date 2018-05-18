import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B_11724 {
	static ArrayList <ArrayList<Integer>> g ; //인접 리스트
	static ArrayList <Integer> result = new ArrayList<Integer>(); //다 들어왔는지 체크할..
	static boolean[] visited;
	static int resize = 0;
	public static void main(String[] args) {
		//System.out.println("??????");
		int answer = 1;
		//int resize = 0;
		Scanner sc = new Scanner(System.in);
		String[] tmp = sc.nextLine().split(" ");

		int n,m;
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);

		g = new ArrayList<ArrayList<Integer>> ();

		for(int i = 0; i <n+1; i++){
			g.add(new ArrayList<Integer>());
		}
		
		visited = new boolean[n+1];

		for(int i = 0; i <n+1; i++){
			visited[i] = false;
		}

		for(int i = 0; i <m; i++){
			int s, d;
			String path[] = sc.nextLine().split(" ");
			s = Integer.parseInt(path[0]);
			d = Integer.parseInt(path[1]);
			g.get(s).add(d);
			g.get(d).add(s);
		}

		for(int i=1; i<=n;i++) {
			if(!visited[i]) {
				answer++;
				dfs(i);
			}
	}
		System.out.println(answer-1);
	}
	public static void dfs(int here){
		visited[here] = true;
		resize+=1;
		for(int i : g.get(here)) {
			if(!visited[i]) {
				dfs(i);
			}
		}
	}	
}