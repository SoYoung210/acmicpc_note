import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//(()[[]])([]) answer : 28
public class B_2504 {
	static Stack<String> s = new Stack<>();
	public static void main(String[] args) {
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			char[] chArr = br.readLine().toCharArray();
			int chLeng = chArr.length;
			if(chLeng == 0 || chLeng == 1) {System.out.println("0"); return;}
			int i;

			int answer = 0;

			for(i=0; i < chLeng; i++) {
				//stackOp call
				if(chArr[i] == ')') {
					stackOp(2,')');
				}else if(chArr[i] == ']') {
					stackOp(3,']');
				}else {
					s.push(Character.toString(chArr[i]));
				}				
			}

			while(!s.isEmpty()) {
				if(isInteger(s.peek())) {
					answer += Integer.parseInt(s.pop());
				}else {
					System.out.println("0"); return;
				}
			}
			System.out.println(answer);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void stackOp(int op,char bracket) {
		int sum = 0;
		String tmp;
		String tmp2;
		String second;

		try {
			tmp = s.pop();
			//System.out.println("tmp : "+tmp);
			if(isInteger(tmp)) {
				sum += Integer.parseInt(tmp);
				try {
					second = s.peek();
					if(isInteger(second)) {
						tmp2 = s.peek();
						while(isInteger(tmp2)) {
							s.pop();
							sum += Integer.parseInt(tmp2);
							tmp2 = s.peek();
						}
						s.pop();
						s.push(Integer.toString(sum*op));
					}else {
						if(bracket == ')') {
							if(s.pop().charAt(0) == '(') {
								s.push(Integer.toString(Integer.parseInt(tmp)*op));
							}else {
								System.out.println("0"); System.exit(0);
							}
						}else {		
							if(s.pop().charAt(0) == '[') {
								s.push(Integer.toString(Integer.parseInt(tmp)*op));
							}else {
								System.out.println("0"); System.exit(0);
							}
						}
						
					}

				}catch(Exception e) {
					//System.out.println("0"); return;
				}
			}else {
				if(bracket == ')') {
					if(tmp.charAt(0) == '(') {
						s.push("2");
					}
					else {
						System.out.println("0"); System.exit(0);
					}
				}else {
					if(tmp.charAt(0) == '[') {
						s.push("3");
					}
					else{
						System.out.println("0"); System.exit(0);
					}
				}
				//s.push( (bracket=='(') ? '2' : '3' );
			}
		}catch(EmptyStackException ex) {
			//System.out.println("0"); return;
		}
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
}