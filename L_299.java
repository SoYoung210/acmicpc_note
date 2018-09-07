import java.util.*;

public class L_299 {
	static Map<Character, ArrayList<Integer>> guessMap = new HashMap<>();   
	public static void main(String[] args) {
		//System.out.println(getHint("1807","7810"));
		//System.out.println(getHint("1123","0111"));
		//System.out.println(getHint("11","01"));
		System.out.println(getHint("1122","0001"));
		//Output:"0A2B" , Expected:"0A1B"
		//string immutable , so string -> stringbuilder  
	}
    public static String getHint(String secret, String guess) {
    	if(secret.length()==0 || guess.length() == 0) {
    		return "0A0B";
    	}
    	StringBuilder sb = new StringBuilder(secret);
        int i;
        int guessLen = guess.length();
        int secLen = secret.length();
        int bull = 0;
        int cow = 0;

        for(i=0; i<guessLen; i++) {
        	if(guessMap.containsKey(guess.charAt(i))) {
        		guessMap.get(guess.charAt(i)).add(i);
        	}else {
        		ArrayList<Integer> tmp = new ArrayList<>();
        		tmp.add(i);
        		guessMap.put(guess.charAt(i), tmp);
        	}
        }

        for(i=0; i<secLen; i++) {
        	if(guessMap.containsKey(sb.charAt(i))) {
        		//if ( has key, and same index )s
	        	if(guessMap.get(sb.charAt(i)).contains(i)) {
	        		bull++;
	        		//delete
	        		guessMap.get(sb.charAt(i)).remove((Integer)i);
	        		sb.setCharAt(i, '-');
	        		//secret.replaceFirst(Character.toString(secret.charAt(i)), "-");
	        	}
        	}
        }
        for(i=0; i<secLen; i++) {
        	if(guessMap.containsKey(sb.charAt(i))) {
        		if(guessMap.get(sb.charAt(i)).size()!=0) {
        			cow++;
        			guessMap.get(sb.charAt(i)).remove(guessMap.get(sb.charAt(i)).size()-1);
        		}
        	}
        }
        return bull+"A"+cow+"B";
    }
}