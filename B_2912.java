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

			boolean half_check = false;
			int half_num = 0;
			int retIndex = 0;
			for(i=0; i<m; i++) {
				tmp = br.readLine().split(" ");
				//배열은 0부터 여기 숫자는 1부터 시작.
				int startDwarf = Integer.parseInt(tmp[0]);
				int endDwarf = Integer.parseInt(tmp[1]);

				for(int j=startDwarf; j<=endDwarf;j++) {
					indexDwarf = dwarf[j];
					dwarf_hat[indexDwarf]++;					
				}

				//Arrays.binarySearch와 성능 비교해보기. 뭘비교해 압살이지 ;
				//짝 홀 
				if( (endDwarf - startDwarf +1)%2 ==0 ){
					half_num = ((endDwarf - startDwarf +1)/2);
				}else {
					half_num = ((endDwarf - startDwarf +1)/2) + 1;
				}
				System.out.println("half_num "+half_num);


				//java8을 쓰기는 힘들듯 ;
				/*
				half_check = IntStream.of(dwarf_hat).anyMatch(x -> x >= (endDwarf - startDwarf +1));
				if(half_check) {
					//자바 8을 쓰려는 비효율의 극치
					Arrays.sort(dwarf_hat);
					System.out.println("yes "+dwarf_hat[dwarf_hat.length-1]);
				}else {
					System.out.println("no");
				}*/
				Arrays.fill(dwarf_hat,0);
			}
		}catch(Exception e) {

		}
	}

}
