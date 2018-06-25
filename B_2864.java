import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_2864 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");

		int min,max;

		int i;
		int tmp1_len = tmp[0].length();
		int tmp2_len = tmp[1].length();

		String min1 = tmp[0].replaceAll("6","5");
		String min2 = tmp[1].replaceAll("6","5");

		min = Integer.parseInt(min1) +Integer.parseInt(min2);

		String max1 = tmp[0].replaceAll("5","6");
		String max2 = tmp[1].replaceAll("5","6");	

		max = Integer.parseInt(max1) +Integer.parseInt(max2);

		System.out.println(min+" "+max);
	}
}