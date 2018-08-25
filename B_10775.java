import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class B_10775 {
	static boolean[] gate;
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int g = Integer.parseInt(br.readLine());
			int p = Integer.parseInt(br.readLine());

			int i;
			int airplane;
			int answer = 0;
			gate = new boolean[g+1];
			for(i=0; i<p; i++) {
				airplane = Integer.parseInt(br.readLine());
				if(!gate[airplane]) {
					gate[airplane] = true;
					answer++;
				}else {
					if(searchDock(airplane)) answer++;
					else break;
				}
			}
			System.out.println(answer);
		}catch(Exception e) {

		}
	}
	public static boolean searchDock(int planeNumber) {
		int mid;
		int left = 0;
		int right = planeNumber;
		boolean isDocked = false;
		while(right > 0) {
				gate[right] = true;
				isDocked = true;
				break;
			}
			right = right - 1;
		}
		return isDocked;
	}
}