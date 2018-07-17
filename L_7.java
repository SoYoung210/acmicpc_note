import java.util.*;

public class L_7 {
	public static void main (String[] args) {
		System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(-120));
        System.out.println(reverse(20));
        System.out.println(reverse(0));
	}
    public static int reverse(int x) {
        StringBuilder sb;
        boolean minus = false;
        try {
            sb = new StringBuilder(Integer.toString(x));
            sb = sb.reverse();
            if(sb.length()==1 ) return Integer.parseInt(sb.toString());
            if(x<0) {
                minus = true;
                sb.setLength(sb.length() - 1);
            }
            if(x%10 == 0) {
                for(char c : sb.toString().toCharArray()) {
                    if(c != '0') break;
                    sb.deleteCharAt(0);
                }
            }
            if(Long.parseLong(sb.toString()) > Integer.MAX_VALUE || Long.parseLong(sb.toString())*(-1) < Integer.MIN_VALUE) {
                return 0;
            }
        }catch(Exception e) {
            return 0;
        }
        return (minus) ? Integer.parseInt(sb.toString())*(-1) : Integer.parseInt(sb.toString());
        
    }    
}