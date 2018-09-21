import java.util.*;
import java.util.Map.Entry;
class Element implements Comparable<Element> {
	public int number;
	public int frequent;

	Element(int number, int frequent) {
		this.number = number;
		this.frequent = frequent;
	}

	@Override
	public int compareTo(Element o){
		//dec 
		if(this.frequent<o.frequent) {
			return 1;
		}else if(this.frequent > o.frequent) {
			return -1;
		}else {
			return 0;
		}
       // return distance <= o.distance ? -1 : 1;
    }
}

class L_347 {
	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3};
		int[] num = {1};
		List<Integer> a1 = topKFrequent(nums,2);
		List<Integer> a2 = topKFrequent(num,1);
		for(int j=0; j<a1.size(); j++) {
			System.out.println(a1.get(j));
		}
		for(int j=0; j<a2.size(); j++) {
			System.out.println(a2.get(j));
		}		
	}
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequentNumber = new HashMap<>();
        List<Integer> answer = new LinkedList<>();
        Queue<Element> q = new PriorityQueue<>();
        int numLen = nums.length;

        int i;
        for(i=0; i<numLen; i++) {
        	if(frequentNumber.containsKey(nums[i])) {
        		frequentNumber.put(nums[i] , frequentNumber.get(nums[i])+1);
        	}else {
        		frequentNumber.put(nums[i] , 1);
        	}
        }
        for (Entry<Integer,Integer> entry : frequentNumber.entrySet()) {
		    int key = entry.getKey();    
		    int v = entry.getValue();
		    q.offer(new Element(key,v));
		}

        for(i=0; i<k; i++) {
        	Element e = q.poll();
        	answer.add(e.number);
        }
        return answer;
    }
}