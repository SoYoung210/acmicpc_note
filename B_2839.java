import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_2839 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sugar = Integer.parseInt(br.readLine());

		int five = sugar/5;
		int three = 0;

		int temp;
		if(five < 1) {
			int answer = (sugar % 3==0) ? (sugar/3) : -1;
			System.out.println(answer);
			return;
		}

		for(;five>=0;five--) {
			temp = sugar - 5*five;
			if(temp % 3 ==0) {
				System.out.println((five + (temp/3)));
				return;
			}
		}
		System.out.println("-1");
	}
}