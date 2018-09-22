import java.util.*;

public class L_781 {
	public static void main(String[] args) {

		int[] r4 = {0,0,1,1,1}; //6
		int[] r5 = {2,1,2,2,2,2,2,2,1,1}; // 13    12
		int[] r6 = {0,1,0,2,0,1,0,2,1,1}; //11
		//1 1 1 1 2 2 , 0: 4
		System.out.println(numRabbits(r5));
	}
	public static int numRabbits(int[] answers) {
		int aLen = answers.length;
		int answer = 0;
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
		//uh,,...
        int[] bucket = new int[1000];

        int i;
        //default 1.
        //if value 1 -> 2!!!!!!!!!!!! 
		for (int n : answers) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		for (int key : frequencyMap.keySet()) {
			bucket[key] = frequencyMap.get(key);
			//System.out.println("key :"+key+" bucket[key] "+bucket[key]);
		}
		answer += bucket[0];
		for(i=1;i<1000; i++) {
			//i ==0, bucketh[0] 1 이어야 만족. 

			if(bucket[i] !=0) {
				if(bucket[i] < i + 1) {
					answer += (i+1);
				}else if(bucket[i] > i+1){
					//2,2,2  / 2,2,2 / 2,2,2 (7 - 2 +3)
					//  2 - (0) +                  1
					answer += (i+1) - ( bucket[i] % (i+1) )  + bucket[i];
					//answer += bucket[i] - (i) +(i+1);
				}else if(bucket[i] == i +1){
					answer += (bucket[i]);
				}
			}
		}
		return answer;
    }
}