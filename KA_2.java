import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//0.81649658092
//0.816496580927726

//0.9428090415820634
//0.94280904158
public class KA_2 {
	public static void main (String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String[] tmp = br.readLine().split(" ");
			int n = Integer.parseInt(tmp[0]);
			int k = Integer.parseInt(tmp[1]);

			int i,j;
			String[] doll_info = br.readLine().split(" ");
			double[] doll = new double[n];

			for(i=0; i<n; i++) {
				doll[i] = Double.parseDouble(doll_info[i]);

			}

			double doll_mean;
			double doll_disp;

			double answer = Double.MAX_VALUE;
			for(i=0; i<=(n-k); i++) {
				//System.out.println("=====");
				for(int a = (n- k - i); a >= 0; a-- ) {
					//a : 2 , i : 0, k :3, doll 
					//System.out.println("---");
					doll_mean = mean(doll,i,k , a);
					doll_disp = dispersion(doll, i, k, doll_mean , a);
					answer = (answer > Math.sqrt(doll_disp)) ? Math.sqrt(doll_disp) : answer;					
				}					
			}

			System.out.println(answer);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	static double mean(double[] arr,int start ,int n, int a) {
		int i;
		double sum = 0.0;
		double avg;
		//start = 0; i < 0+3+2 ; i++ 
		for(i=start; i<(start+n + a); i++) {
			sum += arr[i];
		}
		avg = sum/(n+a);
		return avg;
	}

	static double dispersion(double[] arr,int start ,int n, double avg, int a) {
		int i;
		double sum = 0.0;
		double disp;

		for(i=start; i<(start+n+a); i++) {
			sum += (arr[i] - avg)*(arr[i] - avg);
		}
		disp = sum / (n+a);

		return disp;
	}

}
