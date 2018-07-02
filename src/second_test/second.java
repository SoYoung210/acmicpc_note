//���ĵ� k���� ����Ʈ�� �ִ�. k���� ����Ʈ �� ��� �Ѱ��� ���ڸ� �����ϴ� ���� ������ ���� ���� ������ ������ ���Ͻÿ�.

package second_test;

//�Է¹��� ���� ������ �迭�� �����ϰ�
//�� ���� ���� index�� �����ϴ� positions[] �� ����
//�迭�� 1�� �� �� > ���� �������� ���� �ε����� �̵� �ϴ� �������� �˻�

// ��)    ��                                    ��(�ε��� �̵�)
//     {{ 1, 15, 27, 29},                {{ 1, 15, 27, 29},
//        ��                                 ��
//      { 2, 16, 26, 31},           ==>   { 2, 16, 26, 31},           ==> ...
//        ��                                 ��
//      { 4, 11, 16, 17, 25, 33}};        { 4, 11, 16, 17, 25, 33}};
 
//
public class second {

   public static void main(String[] args) {

       int k = 7; // ����Ʈ ����
       int [][] kList = new int [k][]; //����Ʈ�� ������ �������迭

       int [] positions = new int [k]; //kList�� �� ���� �̵��� ��ġ
       int totalMax = 0; // ������� �ݺ��� ����� �� �� �ִ밪
       int totalMin = 0; // ������� �ݺ��� ����� �� �� �ּҰ�
       int max = -1;     // ���� �Ǵ� ���� ����� �� �� �ִ밪 
       int min = -1;     // ���� �Ǵ� ���� ����� �� �� �ּҰ�
       int tmpIndex = 0; // ���� �Ǵ� ���� ����� �� �� �ּҰ� �� ����

       //kList[0] = new int [] {4,10,15,24,26};
       //kList[1] = new int [] {1,9,12,20}; 
       //kList[2] = new int [] {6,18,22,30};

       kList[0] = new int [] {1,15,27,29};
       kList[1] = new int [] {2,16,26,31};
       kList[2] = new int [] {4,11,16,17,25,33};
       kList[3] = new int [] {5,13,18,25,35};
       kList[4] = new int [] {6,8,19,25,37};
       kList[5] = new int [] {20,23,27,39};
       kList[6] = new int [] {3,9,12,21,24,25,41};

       while (!isFinish(kList, positions)) {
           max = -1;
           min = -1;
           for(int i=0 ; i<kList.length ; i++){
               if (max == -1 && min == -1){
                   max = kList[i][positions[i]];
                   min = kList[i][positions[i]];
                   tmpIndex = i;
               }
               else if (kList[i][positions[i]] >= max) 
                   max = kList[i][positions[i]];
               else if (kList[i][positions[i]] <= min){ 
                   min = kList[i][positions[i]];
                   tmpIndex = i;
               }
           }
           //���� �Ǵ����� ���ڵ� �� �������� ���� min �� ���� �� �ش� row�� index �� �������� �̵�
           positions[tmpIndex] = positions[tmpIndex] + 1;

           if(totalMax==0 && totalMin==0 ){
               totalMax = max;
               totalMin = min;
           }else if (max-min < totalMax-totalMin ){
               totalMax = max;
               totalMin = min;
           }
       }
       System.out.println("���� ������ ���� ���� ������ ������ ["+totalMin+", "+totalMax+"]");
   }
   /*
    * ���� ���� ���� ���� �迭�� ���������� �̵���������,
    * �ٸ� �ε����� �� �̵��ϸ� ���̸� Ŀ�� �������� �̵����� �ʰ� �ݺ� ������
    */
   static boolean isFinish(int [][] kList, int []positions) {
       boolean finish = false;
       for( int i=0 ; i<positions.length ; i++){
           if(positions[i] == kList[i].length)
               finish = true;
       }
       return finish;
   }
}

