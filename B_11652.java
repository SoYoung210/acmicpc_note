import java.math.BigInteger;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Card implements Comparable<Card> {
	String num;
	int count;

	Card(String num, int count) {
		this.num = num;
		this.count = count;
	}
	public int compareTo(Card c) {
		if(count > c.count) {
			return -1;
		}else if(count < c.count) {
			return 1;
		}else if(count == c.count) {
			return this.num.compareTo(c.num);
		}else {
			return 0;
		}
	} 
}

public class B_11652 {
	public static HashMap<String,Integer> h = new HashMap<>();
	public static PriorityQueue<Card> q = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int i;
		
		for(i=1;i<=n;i++) {
			String card = br.readLine();
			BigInteger big = new BigInteger(card);
			if(h.containsKey(big.toString())) {
				int count = h.get(big.toString());
				h.put(big.toString(),count+1);
			}else {
				h.put(big.toString(),1);
			}
		}
		Iterator<String> iterator = h.keySet().iterator();
		while(iterator.hasNext()) {
			String key = (String)iterator.next();
			q.offer(new Card(key,h.get(key)));
		}
		System.out.println(q.peek().num);
	}
}
