import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//LIS 알고리즘.
public class B_1965 {
	static ArrayList<Integer> li;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());

		int i;
		int szOfBox;
		int position;

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] box = new int[count];
		for(i=0; i<count; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		li = new ArrayList<>();

		for(i=0; i<count; i++) {
			szOfBox = box[i];
			position = lowerBound(szOfBox);
			if(position == li.size()) {
				li.add(szOfBox);
			}else {
				li.set(position,szOfBox);
			}
		}
		System.out.println(li.size());
	}
	//2분 탐색 + target 보다 큰수의 인덱스를 바로 리턴.
	static int lowerBound(int target) {
		int start = 0;
		int end = li.size();
		int mid = 0;

		while(end > start) {
			mid = (start+end) /2;
			if(li.get(mid) >= target) {
				end = mid;
				//break;
			}else {
				start = mid+1;
			}
		}
		return end;
	}
}