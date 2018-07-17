import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
Arrays.sort(arr);

int a = Arrays.binarySearch(arr, targetValue);
*/
public class B_2798 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		int n = Integer.parseInt(tmp[0]);
		int m = Integer.parseInt(tmp[1]); //21
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] card = new int[n];
		//int[] card = new int[n];
		int i,j,small_index, large_index;
		for(i=0; i<n; i++) {
			card[i] = Integer.parseInt(st.nextToken());
			//card.add(Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(card);
		int small_answer = 0;
		int large_answer = 0;
		int answer = 0;
		for(i=0; i<card.length-2; i++) {
			for(j=0;j<card.length-1; j++) {
				//additional_index = (card[j+1] <= m-card[i]+card[j]) ? (j+1) : (card.length-1);
				small_index = j+1;
				large_index = card.length-1;

				if(small_index < large_index) {
					small_answer = card[i] + card[j] + card[small_index];
					large_answer = card[i] + card[j] + card[large_index];
					answer = (Math.abs(m-answer) > Math.abs(m-small_answer)) ?  small_answer : answer;
					answer = (Math.abs(m-answer) > Math.abs(m-large_answer)) ?  large_answer : answer;
				}
				small_index++;
				large_index--;
			}
		}
		System.out.println(answer);
	}
}