import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KA_1 {
	public static void main (String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.parseInt(br.readLine());
			int i;
			int a = 0;
			int b = 0;
			int sum = 0;

			int[] fest1 = {1, 3, 6, 10, 15, 21};
			int[] gold1 = {500, 300, 200, 50, 30, 10,0};
			int[] fest2 = {1, 3, 7, 15, 31};
			int[] gold2 = {512, 256, 128, 64, 32,0};

			int res1 = 0;
			int res2 = 0;

			boolean part1 = true;
			for(i = 0; i<n; i++) {
				String[] tmp = br.readLine().split(" ");
				a = Integer.parseInt(tmp[0]);
				b = Integer.parseInt(tmp[1]);

				if(a == 0) res1 = 6;
				else res1 = lowerBound(fest1,a);
				
				if(b == 0) res2 = 5;
				else res2 = lowerBound(fest2,b);

				sum = gold1[res1] + gold2[res2];

				System.out.println(sum*10000);
			}
		}catch(Exception e) {

		}
	}
	static int lowerBound(int[] arr,int target) {
		int start = 0;
		int end = arr.length;
		int mid = 0;

		while(end > start) {
			mid = (start+end) /2;
			if(arr[mid] >= target) {
				end = mid;
				//break;
			}else {
				start = mid+1;
			}
		}
		return end;		
	}
}