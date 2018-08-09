import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KA_4 {

    public static void main(String[] args) {
        new KA_4().solve();
    }

    Map<Integer, List<Integer>> lineX;
    Map<Integer, List<Integer>> lineY;

    Map<Integer, Set<Integer>> map = new HashMap<>();
    Map<Integer, Integer> key = new HashMap<>();

    Map<Integer, Set<Integer>> xMap = new HashMap<>();
    Map<Integer, Set<Integer>> yMap = new HashMap<>();

    CheckPoint[] checkPointList;
    boolean[] isVisit;
    int visitCount = 0;

    class CheckPoint {
        int number;
        int x;
        int y;

        CheckPoint(int n, int x, int y) {
            this.number = n;
            this.x = x;
            this.y = y;
        }
    }

    private void solve() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String dmp[] = br.readLine().split(" ");
            int checks = Integer.parseInt(dmp[0]);
            int question = Integer.parseInt(dmp[1]);

            lineX = new HashMap<>(); // 같은 x 선 상에 있는 수들
            lineY = new HashMap<>(); // 같은 y 선 상에 있는 수들

            checkPointList = new CheckPoint[checks + 1];

            isVisit = new boolean[checks + 1];
            // 체크포인트 초기화
            for (int i = 1; i <= checks; i++) {
                dmp = br.readLine().split(" ");
                int x = Integer.parseInt(dmp[0]);
                int y = Integer.parseInt(dmp[1]);

                CheckPoint c = new CheckPoint(i, x, y);
                checkPointList[i] = c;

                // x 선 상에 있는 것 들 추가
                if (lineX.containsKey(x)) {
                    lineX.get(x).add(i);
                } else {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    lineX.put(x, l);
                }

                if (lineY.containsKey(y)) {
                    lineY.get(y).add(i);
                } else {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    lineY.put(y, l);
                }
            }

            link();

            for (int i = 0; i < question; i++) {
                dmp = br.readLine().split(" ");
                int start = Integer.parseInt(dmp[0]);
                int last = Integer.parseInt(dmp[1]);
                int health = Integer.parseInt(dmp[2]);

                int dir = key.get(start);
                if(health==0) {
                    if(map.get(dir).contains(last)) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                } else {
                    health = health-1;
                    int sDir = key.get(start);
                    int lDir = key.get(last);
                    if(map.get(sDir).contains(last)) {
                        System.out.println("YES");
                    } else {
                        for(int x : xMap.get(sDir)) {
                            for(int xx : xMap.get(lDir)) {
                                if(xx - health <= x && x <= xx+health) {
                                    System.out.println("YES");
                                    return;
                                }
                            }
                        }
                        for(int y: yMap.get(sDir)) {
                            for (int yy: yMap.get(lDir)) {
                                if(yy-health <= y && y <= yy+health) {
                                    System.out.println("YES");
                                    return;
                                }
                            }
                        }
                        System.out.println("NO");
                    }
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    private void link() {
        for (int i = 0; i < checkPointList.length - 1; i++) {
            dfs(i + 1);
        }
    }


    private void dfs(int checkNumber) {

        int currentX = checkPointList[checkNumber].x;
        int currentY = checkPointList[checkNumber].y;

        if (!isVisit[checkNumber]) {
            Set<Integer> l = new HashSet<>();
            l.add(checkNumber);
            map.put(checkNumber, l);
            Set<Integer> s = new HashSet<>();
            s.add(currentX);
            Set<Integer> ss = new HashSet<>();
            ss.add(currentY);
            xMap.put(checkNumber, s);
            yMap.put(checkNumber, ss);
        }

        if (!isVisit[checkNumber]) {
            findX(checkNumber, currentX, checkNumber);
            findY(checkNumber, currentY, checkNumber);
        }
    }

    private void findX(int checkNumber, int currentX, int currentNumber) {
        isVisit[currentNumber] = true;
        List<Integer> xList = lineX.get(currentX);
        for (int i = 0; i < xList.size(); i++) {
            map.get(checkNumber).add(xList.get(i));
            int currentCheckPoint = xList.get(i);
            key.put(currentCheckPoint, checkNumber);
            xMap.get(checkNumber).add(checkPointList[currentCheckPoint].x);
            int currentY = checkPointList[currentCheckPoint].y;
            if (currentCheckPoint != currentNumber) {
                findY(checkNumber, currentY, currentCheckPoint);
            }
        }
    }

    private void findY(int checkNumber, int currentY, int currentNumber) {
        isVisit[currentNumber] = true;
        List<Integer> yList = lineY.get(currentY);
        for (int i = 0; i < yList.size(); i++) {
            map.get(checkNumber).add(yList.get(i));
            int currentCheckPoint = yList.get(i);
            key.put(currentCheckPoint, checkNumber);
            yMap.get(checkNumber).add(checkPointList[currentCheckPoint].y);
            int currentX = checkPointList[currentCheckPoint].x;
            if (currentCheckPoint != currentNumber) {
                findX(checkNumber, currentX, currentCheckPoint);
            }
        }
    }
}