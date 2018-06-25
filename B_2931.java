import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Edge{
	int x;
	int y;
	Edge(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class B_2931 {
	static char[][] europe;
	static Queue<Edge> z = new LinkedList<>();
	static Queue<Edge> m = new LinkedList<>();
	static boolean[][] visited;
	static Edge answer;
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in_first = br.readLine().split(" ");
		//유럽 맵 생성. 많아봐야 25*25니까 괜찮.
		int r = Integer.parseInt(in_first[0]);
		int c = Integer.parseInt(in_first[1]);
		europe = new char[r][c];
		visited = new boolean[r][c];
		int i,j;
		char[] input;
		for(i=0; i<r; i++) {
			input = br.readLine().toCharArray();
			for(j=0; j<c; j++) {
				europe[i][j] = input[j];
				if(input[j] == 'M') {
					m.add(new Edge(i,j));
					visited[i][j] = true;
				}else if(input[j] == 'Z') {
					z.add(new Edge(i,j));
				}
				visited[i][j] = false;
			}
			//하는김에 visited 배열 초기화..
		}
		pipe(r,c);
	}
	//찾으면 리턴할까..
	public static void pipe(int r, int c ) {
		int newX =0;
		int newY =0;
		boolean right = false;
		boolean left = false;
		boolean up = false;
		boolean down = false;
		while(!m.isEmpty()) {
			Edge e = m.poll();
			right = false;
			left = false;
			up = false;
			down = false;
			visited[e.x][e.y] = true;
			if(europe[e.x][e.y]=='M') {
				if(e.x-1>=0) {
					if(europe[e.x-1][e.y]!='.') m.add(new Edge(e.x-1, e.y));
				}
				if(e.x+1<r) {
					if(europe[e.x+1][e.y]!='.') m.add(new Edge(e.x+1, e.y));
				}
				if(e.y-1>=0) {
					if(europe[e.x][e.y-1]!='.') m.add(new Edge(e.x, e.y-1));
				}
				if(e.y+1<c) {
					if(europe[e.x][e.y+1]!='.') m.add(new Edge(e.x, e.y+1));
				}
				continue;
			}
			if(europe[e.x][e.y] == '|') {
				up = true;
				down = true;
			}else if(europe[e.x][e.y] == '-') {
				right = true; left = true;
			}else if(europe[e.x][e.y] == '+') {
				up = true; down = true; right = true; left = true;
			}else if(europe[e.x][e.y] == '1') {
				right = true; down = true;
			}else if(europe[e.x][e.y] == '2') {
				up = true; right = true;
			}else if(europe[e.x][e.y] == '3') {
				up = true; left = true;
			}else if(europe[e.x][e.y] == '4') {
				down = true; left = true;
			}


			if(down) {
				//System.out.println("down");
				newX = e.x+1;
				newY = e.y;
				System.out.println("x : "+newX+"newY"+newY+"down"+europe[newX][newY]);
				if(!visited[newX][newY]) {
					if(europe[newX][newY] != '.' ) {
						if(europe[newX][newY] != 'M'&& europe[newX][newY]!='Z') {
							m.add(new Edge(newX, newY));
							visited[newX][newY] = true;
						}
					}else {
						answer = new Edge(newX,newY);
						break;
					}
				}	
			}
			if(up) {
				//System.out.println("up");
				newX = e.x-1;
				newY = e.x;
				System.out.println("x : "+newX+"newY"+newY+"up"+europe[newX][newY]);
				if(!visited[newX][newY]) {
					if(europe[newX][newY] != '.' ) {
						if(europe[newX][newY] != 'M'&& europe[newX][newY]!='Z') {
							m.add(new Edge(newX, newY));
							visited[newX][newY] = true;
						}
					}else {
						answer = new Edge(newX,newY);
						break;
					}
				}
			}
			if(left) {
				//System.out.println("left");
				newX = e.x;
				newY = e.y - 1;
				System.out.println("x : "+newX+"newY"+newY+"left"+europe[newX][newY]);
				if(!visited[newX][newY]) {
					if(europe[newX][newY] != '.' ) {
						if(europe[newX][newY] != 'M'&& europe[newX][newY]!='Z') {
							m.add(new Edge(newX, newY));
							visited[newX][newY] = true;
						}
					}else {
						answer = new Edge(newX,newY); break;
					}
				}
			}
			if(right) {
				//System.out.println("right");
				newX = e.x;
				newY = e.y + 1;
				System.out.println("x : "+newX+"newY"+newY+"right"+europe[newX][newY]);
				if(!visited[newX][newY]) {
					if(europe[newX][newY] != '.' ) {
						if(europe[newX][newY] != 'M'&& europe[newX][newY]!='Z') {
							m.add(new Edge(newX, newY));
							visited[newX][newY] = true;
						}
					}else {
						answer = new Edge(newX,newY); break;
					}
				}
			}
		}
		right = false; left = false; up = false; down = false;
		//위쪽 
		char ch;
		if(answer.x-1 >=0) {
			ch = europe[answer.x-1][answer.y];
			if(ch == '|' || ch == '+' || ch == '1' || ch=='2') {
				up = true;
			}
		}
		//아래 
		if(answer.x+1<r) {
			ch = europe[answer.x+1][answer.y];
			if(ch == '|' || ch == '+' || ch == '2' || ch=='3') {
				down = true;
			}
		}
		//오른 
		if(answer.y+1<c) {
			ch = europe[answer.x][answer.y+1];
			if(ch == '-' || ch == '+' || ch == '3' || ch=='4') {
				right = true;
			}
		}
		//왼
		if(answer.y-1>=0) {
			ch = europe[answer.x][answer.y+1];
			if(ch == '-' || ch == '+' || ch == '1' || ch=='2') {
				left = true;
			}			
		}
		// | 
		if(up==true && down==true && right == false && left == false) {
			System.out.println(answer.x+" "+answer.y+" "+"|");
		}else if(up==false && down == false && right == true && left == true ) {
			System.out.println(answer.x+" "+answer.y+" "+"-");
		}else if(up&&down&&right&&left) {
			System.out.println(answer.x+" "+answer.y+" "+"+");
		}else if(up==false && left == false && right==true && down == true) {
			System.out.println(answer.x+" "+answer.y+" "+"1");
		}else if(up==true && left == false && right==true && down == false) {
			System.out.println(answer.x+" "+answer.y+" "+"2");
		}else if(up==true && left == true && right==false && down == false) {
			System.out.println(answer.x+" "+answer.y+" "+"3");
		}else if(up==false && left == true && right==false && down == true) {
			System.out.println(answer.x+" "+answer.y+" "+"4");
		}
	}
}