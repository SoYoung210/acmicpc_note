import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//(()[[]])([]) answer : 28
public class B_2504 {
	static Stack<String> s = new Stack<>();
    private static char[] open = {'(', '['};
    private static char[] close = {')', ']'};

	public static void main(String[] args) {
		
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            char[] chArr = br.readLine().toCharArray();
            int chLeng = chArr.length;
            if (chLeng % 2 != 0) {
                System.out.println("0");
                return;
            }
            int i;

            int answer = 0;

            boolean isOk = true;
            for (i = 0; i < chLeng; i++) {
                //stackOp call
                char c = chArr[i];
                for (int j = 0; j < 2; j++) {
                    if (c == close[j]) {
                        isOk = stackOpChange(j + 2, close[j]);
                        break;
                    }
                    if (c == open[j]) {
                        s.push(String.valueOf(c));
                        break;
                    }
                }
                if (!isOk) {
                    System.out.println(0);
                    return;
                }
            }

            while (!s.isEmpty()) {
                if (isInteger(s.peek())) {
                    answer += Integer.parseInt(s.pop());
                } else {
                    System.out.println("0");
                    return;
                }
            }
            System.out.println(answer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
    private static boolean stackOpChange(int op, char bracket) {
        // 닫힘 괄호만 있을 때 체크
        if (s.isEmpty()) {
            return false;
        }
        int sum = 0;
        String tmp;
        String second;
        boolean isCheck = true;
        try {
	        tmp = s.pop();

	        if (isInteger(tmp)) {
	            sum += Integer.parseInt(tmp);
	            second = s.peek();
	            if (isInteger(second)) {
	                while (isInteger(second)) {
	                    s.pop();
	                    sum += Integer.parseInt(second);
	                    second = s.peek();
	                }
	                s.pop();
	                s.push(Integer.toString(sum * op));
	            } else {
	                isCheck = isPush(
	                        bracket,
	                        String.valueOf(Integer.parseInt(tmp) * op),
	                        s.pop().charAt(0)
	                );
	            }
	        } else {
	            isCheck = isPush(bracket, "", tmp.charAt(0));
	        }
	    }catch(EmptyStackException e) {
	    	return false;
	    }
        return isCheck;
    }

    private static boolean isPush(char bracket, String value, char openC) {
        for (int i = 0; i < 2; i++) {
            if (bracket == close[i]) {
                if (openC == open[i]) {
                    //value에 ""이 들어왔을 경우는 2나 3을 넣어주는 부분. 
                    if (value.length() == 0) {
                        s.push(String.valueOf(i + 2));
                    } else {
                        s.push(value);
                    }
                    break;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
	public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
	    // only got here if we didn't return false
	    return true;
	}
}