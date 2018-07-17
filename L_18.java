import java.util.*;

public class L_18 {
	public static void main (String[] args) {
		int[] nums = {1, 0, -1, 0, -2, 2};
		fourSum(nums, 0);
	}
    public static List<List<Integer>> fourSum(int[] nums, int target) {
    	List<List<Integer>> result = new ArrayList<>();
    	//파라미터로 넘어온 값은 copy해서 사용.
        int[] c_nums = nums;

        Arrays.sort(c_nums);
        int i,j,small_index,large_index;

        //worst case: i , j , small_index, large_index가 전부 연속적인 경우. 
        for(i=0; i<c_nums.length - 3; i++ ) {
        	for(j = i+1; j<c_nums.length -2; j++) {
        		small_index = j+1;
        		large_index = c_nums.length -1;

        		int sum = 0;
        		while(small_index < large_index) {
        			sum = c_nums[i] + c_nums[j] + c_nums[small_index] + c_nums[large_index];

        			if(sum == target) {
        				ArrayList<Integer> tmp = new ArrayList<>();
        				tmp.add(c_nums[i]);
        				tmp.add(c_nums[j]);
        				tmp.add(c_nums[small_index]);
        				tmp.add(c_nums[large_index]);

        				if(!result.contains(tmp))
        					result.add(tmp);
        				//tmp = null;
        				small_index++;
        				large_index--;
        			}else if(sum < target) {
        				small_index++;
        			}else if(sum > target) {
        				large_index--;
        			}
        		}
        	}
        }
        return result;
    }	
}