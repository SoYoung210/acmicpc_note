import java.util.*;
import java.util.stream.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class L_20 {

	public static void main(String[] args) {
        String s = "{[]}";
        System.out.println(isValid(s));
	}
    public static boolean isValid(String s) {
        Stack<Character> bracket = new Stack<>();
        char[] open = {'(', '{' , '['};
        char[] close = {')', '}', ']'};
        char[] chArr = s.toCharArray();
        int chLeng = chArr.length;
        if (chLeng % 2 != 0) {
            System.out.println("0");
            return false;
        }
        int i;
        char tmp;
        try {
            for(i = 0; i<chLeng; i++) {
                char c = chArr[i];
                if(c == open[0] || c == open[1] || c == open[2])
                    bracket.push(c);
                else {
                    tmp = bracket.pop();

                    if(tmp == open[0]) {
                        if(c != close[0]) return false;
                    }else if(tmp == open[1]) {
                        if(c != close[1]) return false;
                    }else if(tmp == open[2]) {
                        if(c != close[2]) return false;
                    }
                }
            }

            if(bracket.isEmpty()) return true;
            else return false;
        }catch(EmptyStackException e) {
            return false;
        }    
    }    
}