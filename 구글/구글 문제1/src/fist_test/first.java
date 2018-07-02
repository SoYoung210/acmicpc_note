//구글 입사 문제 1 -> 1부터 10,000까지 8이라는 숫자가 총 몇번 나오는가?

//8이 포함되어 있는 숫자의 갯수를 카운팅 하는 것이 아니라 8이라는 숫자를 모두 카운팅 해야 한다. (※ 예를들어 8808은 3, 8888은 4로 카운팅 해야 함)

package fist_test;

public class first {

	public static void main(String[] args) 
	{
		 int count=0;
		 // 1에서 1만까지는 총 5자리의 숫자가 들어간다. 그래서 각 자리마다 변수를 넣고 각 변수가 8이 될때마다 카운팅한다.

	        for(int a=0; a<1; a++){
	            for(int b=0; b<=9; b++){
	                for(int c=0; c<=9; c++){
	                    for(int d=0; d<=9; d++){
	                        for(int e=0; e<=9; e++){
	                            if(e==8)
	                                count++;
	                            if(d==8)
	                                count++;
	                            if(c==8)
	                                count++;
	                            if(b==8)
	                                count++;
	                            if(a==8)
	                                count++;
	                        }
	                    }
	                }
	            }
	        }

	        System.out.println(count);
	    }
	}
}