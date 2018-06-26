import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_1935 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		String m_expression = br.readLine();
		Stack<Double> s = new Stack<>();
		//알파벳에서 숫자로 바꿔서 저장할 큐.
		LinkedList<Integer> num = new LinkedList<>();
		//convert .
		int i;
		int str_index = 0;
		String convert;
		//65-78
		for(i=0;i<count;i++) {
			convert = br.readLine();
			//m_expression = m_expression.replaceAll(Character.toString((char)(65+i)),convert);
			num.add(Integer.parseInt(convert));
		}
		int exp_len = m_expression.length();
		while(str_index != exp_len) {
			//isaplha?
			if(Character.isLetter(m_expression.charAt(str_index))){
				int index = (int)m_expression.charAt(str_index) - 65;

				//System.out.println("index : "+index+" num.indexof : "+num.get(index));
				s.push((double) num.get(index));
			}else {
				switch(m_expression.charAt(str_index)) {
					case '+':
						s.push( s.pop() + s.pop());
						break;
					case '-':
						double f1 = s.pop();
						double f2 = s.pop();
						s.push( f2 - f1 );
						break;
					case '*':
						s.push( s.pop() * s.pop() );
						break;
					case '/':
						double first = s.pop();
						double second = s.pop();
						s.push(second/first);
						break;
				}
				//System.out.println("s. peek2 " + s.peek());
			}
			str_index++;
		}
		System.out.printf("%.2f", s.pop());
	}
}