import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Score implements Comparable<Score> {
	public String name;
	public int korean;
	public int english;
	public int math;

	Score(String name, int korean, int english, int math) {
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
	}

	public int compareTo(Score s) {
		//이 애의 국어점수가 낮으면 1을 반환 == return 1을 해줘야 뒤로 밀림. 
		if(this.korean < s.korean) {
			return 1;
		}else if(this.korean > s.korean) {
			return -1;
		}
		if(this.korean==s.korean && this.english > s.english) {
			return 1;
		}else if(this.korean==s.korean && this.english < s.english) {
			return -1;
		}
		if(this.korean==s.korean && this.english== s.english
			      && this.math<s.math) {
			return 1;
		}else if(this.korean==s.korean && this.english== s.english
			      && this.math>s.math) {
			return -1;
		}
		if(this.korean == s.korean && this.english == s.english && this.math == s.math) {
			return this.name.compareTo(s.name);
		}
		return 0;
	}
}

public class B_10825 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//보통 뒤에는 잘 쓰지 않는군. ...
		PriorityQueue<Score> q = new PriorityQueue<> ();
		
		//첫째줄부터 n까지 입력받기. 
		int i,j;
		for(i=0;i<n;i++) {
			String[] in_tmp = br.readLine().split(" ");
			//국영수 점수 스트링 -> 정수 치환
			int korean = Integer.parseInt(in_tmp[1]);
			int english = Integer.parseInt(in_tmp[2]);
			int math = Integer.parseInt(in_tmp[3]);

			q.offer(new Score(in_tmp[0],korean,english,math));
		}
		for(j=0; j<n; j++) {
			Score e = q.poll();
			System.out.println(e.name);
		}
	}
}