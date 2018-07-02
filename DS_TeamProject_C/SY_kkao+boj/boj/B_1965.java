import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//LIS 알고리즘.
public class B_1965 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());

		int[] box = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] d = new int[count];
		Arrays.fill(d,1);
		int i,j;
		int max = 1;
		for(i=0;i<count;i++) {
			for(j=0;j<i;j++) {
				if(box[j] < box[i] && d[i] < d[j]+1) {
					d[i] = d[j] + 1;
				}
			}
			max = (max < d[i]) ? d[i] : max;
		}
		System.out.println(max);
	}
}