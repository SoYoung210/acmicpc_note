import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_9237 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> tree = new PriorityQueue<>(count, Collections.reverseOrder());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int i;
		
		for(i=0;i<count;i++) {
			tree.add(Integer.parseInt(st.nextToken()));
		}
		int max = 0;
		System.out.println("max"+max);
		i = 1;
		for(Integer tmp : tree) {
			System.out.println("tmp"+tmp);
			max = (max < tmp) ? tmp : max;
		}
		System.out.println(max);
	}
}