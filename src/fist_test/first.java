//���� �Ի� ���� 1 -> 1���� 10,000���� 8�̶�� ���ڰ� �� ��� �����°�?

//8�� ���ԵǾ� �ִ� ������ ������ ī���� �ϴ� ���� �ƴ϶� 8�̶�� ���ڸ� ��� ī���� �ؾ� �Ѵ�. (�� ������� 8808�� 3, 8888�� 4�� ī���� �ؾ� ��)

package fist_test;

public class first {

	public static void main(String[] args) 
	{
		 int count=0;
		 // 1���� 1�������� �� 5�ڸ��� ���ڰ� ����. �׷��� �� �ڸ����� ������ �ְ� �� ������ 8�� �ɶ����� ī�����Ѵ�.

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