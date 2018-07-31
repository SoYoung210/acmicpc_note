import java.util.*;
import java.util.stream.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//pq 쓸까 ........ 
public class B_2912 {
	public static void main (String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String[] tmp = br.readLine().split(" ");
			int n = Integer.parseInt(tmp[0]);
			int c = Integer.parseInt(tmp[1]);

			int[] dwarf = new int[n+1];
			int i;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(i=1; i<=n; i++) {
				dwarf[i] = Integer.parseInt(st.nextToken());
			}
			int m = Integer.parseInt(br.readLine());

			//int startDwarf;
			//int endDwarf;
			int indexDwarf;
			//모자 종류 배열
			int[] dwarf_hat = new int[c+1];
			boolean isPretty ;
			for(i=0; i<m; i++) {
				isPretty = false;
				tmp = br.readLine().split(" ");
				//배열은 0부터 여기 숫자는 1부터 시작.
				int startDwarf = Integer.parseInt(tmp[0]);
				int endDwarf = Integer.parseInt(tmp[1]);
				int half = (endDwarf - startDwarf +1)/2;

				for(int j=startDwarf; j<=endDwarf;j++) {
					indexDwarf = dwarf[j];
					dwarf_hat[indexDwarf]++;

					if(dwarf_hat[indexDwarf] > (endDwarf - startDwarf +1)/2 ) {
						System.out.println("yes "+indexDwarf);
						isPretty = true;
						break;
					}		
				}

				if(!isPretty) System.out.println("no");

				Arrays.fill(dwarf_hat,0);
			}
		}catch(Exception e) {

		}
	}

}
