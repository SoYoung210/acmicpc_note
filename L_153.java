import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class L_153 {
	public static void main (String[] args) {
		int[] arr = {3,4,5,1,2};
		int[] arr2= {4,5,6,7,0,1,2};
        int[] arr3= {1,2};
		System.out.println(findMin(arr));
		System.out.println(findMin(arr2));
        System.out.println(findMin(arr3));
	}
    static int findMin(int[] nums) {
        int i;

        int numLen = nums.length;
        if(numLen == 1)
            return nums[0];

        int first = nums[0];
        boolean sorted = false;
        for(i=1; i<numLen; i++) {
        	if(first > nums[i])
        		return nums[i];
        }
        return nums[0];
    }	
}
