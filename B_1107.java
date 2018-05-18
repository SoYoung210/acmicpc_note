import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_1107 {
	public static boolean btn[];

	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int target = Integer.parseInt(br.readLine());
		//System.out.println(target);
		int many = Integer.parseInt(br.readLine());
		//System.out.println(many);
		String[] tmp = br.readLine().split(" ");
		btn = new boolean[10];
		
		StringBuffer answer = new StringBuffer();
		Arrays.fill(btn,true);

		for(int b=0; b<many; b++) {
			btn[Integer.parseInt(tmp[0])] = false;
		}
		if(target == 100) {
			System.out.println("0");
			System.exit(0);
		}
		/*
		btn[Integer.parseInt(tmp[0])] = false;
		btn[Integer.parseInt(tmp[1])] = false;
		btn[Integer.parseInt(tmp[2])] = false;*/


		int r = 0;
		int q = target;

		int count = 0;

		while(q!=0) {
			r = q %10;
			q = q /10;
			//System.out.println("q : "+q);
			//System.out.println("r : "+r);
			if(btn[r]) {
				answer.append(r);
				count++;
			}else {
				//System.out.println("else");
				int tmp2 = 1;
				//System.out.println("r- tmp2 :"+(r-tmp2));
				for(;tmp2<6;tmp2++) {
					if(r - tmp2 >=0) {
						if(btn[r-tmp2]) {
							//System.out.println((r-tmp2));
							answer.append((r-tmp2));
							count++;
							break;
						}
					}
					if(r + tmp2 <=9) {
						if(btn[r + tmp2]) {
							//System.out.println((r+tmp2));
							answer.append((r+tmp2));
							count++;
							break;
						}
					}
				}
			}
		}
		answer = answer.reverse();
		String strint = answer.toString();
		int answer2 = Integer.parseInt(strint);

		int dist = Math.abs(answer2-target);
		System.out.println((count+dist));
	}
}