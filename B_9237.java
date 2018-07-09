import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_9237 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		ArrayList<Integer> tree = new ArrayList<>(count);
		StringTokenizer st = new StringTokenizer(br.readLine());

		int i;
		
		for(i=0;i<count;i++) {
			tree.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(tree);
		int max = 0;
		
		i = count;
		for(Integer tmp : tree) {
			max = (max < tmp+i) ? tmp+i : max;
			i--;
		}
		System.out.println(max+1);
	}
}