import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//(()[[]])([]) answer : 28
public class B_2504 {
	public static void main(String[] args) {
		Stack<Character> s = new Stack<>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			char[] chArr = br.readLine().toCharArray();
			int chLeng = chArr.length;
			int i;

			int answer = 0;

			int pairIndex;
			System.out.println("chLeng : "+chLeng);
			for(i=0; i < chLeng; i++) {
				//stackOp call
			}

			while(!s.isEmpty()) {
				System.out.println("s.size() : "+s.size());
				if(Character.isDigit(s.peek())) {
					answer += Character.getNumericValue(s.pop());
				}else {
					System.out.println("0"); return;
				}
			}
			System.out.println("answer : "+answer);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void stackOp(int op,char bracket) {
		int sum = 0;
		char tmp;
		char tmp2;
		char second;
		switch(bracket) {
			case ')': {

				break;
			}
			case ']': {

				break;
			}
			default: {

				break;
			}
		}

	}
}