//정렬된 k개의 리스트가 있다. k개의 리스트 중 적어도 한개의 숫자를 포함하는 구간 간격이 가장 작은 숫자의 범위를 구하시오.

package second_test;

//입력받은 수를 이차원 배열로 저장하고
//각 열의 현재 index를 저장하는 positions[] 을 만들어서
//배열의 1열 을 비교 > 가장 작은값은 다음 인덱스로 이동 하는 형식으로 검색

// 예)    ↓                                    ↓(인덱스 이동)
//     {{ 1, 15, 27, 29},                {{ 1, 15, 27, 29},
//        ↓                                 ↓
//      { 2, 16, 26, 31},           ==>   { 2, 16, 26, 31},           ==> ...
//        ↓                                 ↓
//      { 4, 11, 16, 17, 25, 33}};        { 4, 11, 16, 17, 25, 33}};
 
//
public class second {

   public static void main(String[] args) {

       int k = 7; // 리스트 개수
       int [][] kList = new int [k][]; //리스트를 저장할 이차원배열

       int [] positions = new int [k]; //kList의 각 열을 이동할 위치
       int totalMax = 0; // 현재까지 반복된 경우의 수 중 최대값
       int totalMin = 0; // 현재까지 반복된 경우의 수 중 최소값
       int max = -1;     // 현재 판단 중인 경우의 수 중 최대값 
       int min = -1;     // 현재 판단 중인 경우의 수 중 최소값
       int tmpIndex = 0; // 현재 판단 중인 경우의 수 중 최소값 의 색인

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
           //현재 판단중인 숫자들 중 가장작은 수는 min 값 저장 후 해당 row의 index 를 다음으로 이동
           positions[tmpIndex] = positions[tmpIndex] + 1;

           if(totalMax==0 && totalMin==0 ){
               totalMax = max;
               totalMin = min;
           }else if (max-min < totalMax-totalMin ){
               totalMax = max;
               totalMin = min;
           }
       }
       System.out.println("구간 간격이 가장 작은 숫자의 범위는 ["+totalMin+", "+totalMax+"]");
   }
   /*
    * 가장 작은 수를 가진 배열이 마지막까지 이동했음으로,
    * 다른 인덱스를 더 이동하면 차이만 커질 뿐임으로 이동하지 않고 반복 마무리
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

