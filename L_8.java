import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class L_8 {
	public static void main (String[] args) {
		//System.out.println(myAtoi(".1"));
		//System.out.println(myAtoi("+"));
		System.out.println(myAtoi("+-2"));
		System.out.println(myAtoi("42"));
		System.out.println(myAtoi("   -42"));
		System.out.println(myAtoi("-91283472332"));

    System.out.println(myAtoi("words and 987"));
	}
    public static int myAtoi(String str) {
      // 우변 값이 너무 영타가 길어서 줄임. 
        final int MAX = Integer.MAX_VALUE;
        final int MIN = Integer.MIN_VALUE;
       String mystr = str;
       mystr = mystr.trim();


       if(mystr.length() == 0) return 0;
       String[] tmp = mystr.split(" ");


       int result = 0;
       boolean plus = true;
       int i = 0;

       int strlen = tmp[0].length();
       try {
        if(tmp[0].charAt(0) == '-') {
          plus = false;
          i++;
        }else if(tmp[0].charAt(0) == '+') {
          plus = true;
          i++;
        }

          while(strlen > i && tmp[0].charAt(i) >= '0' && tmp[0].charAt(i) <= '9') {
            if(result > MAX /10 || ( (result == MAX/10) && (tmp[0].charAt(i) - '0' > MAX%10) ) ) {
              return (plus) ? MAX : MIN;
            }
            result = result*10 + (tmp[0].charAt(i) - '0');
           // System.out.println("result : "+result);
            i++;
          }

          result = (plus) ? result : (-1)*result;
          return result;
       }catch(Exception e) {
          // OverFlow Detection 이 여기서 될것 같았지만 되지 않는다. ㅠㅠ roo roo.... 
          return plus ? MAX : MIN;
       }

    }	

}
///^[0-9]+$/