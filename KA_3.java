import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KA_3 {
	static ArrayList<ArrayList<Integer>> songTree;
	static Map<Integer, Long> finalScore;
	static int[] singerInfo;
	public static void main (String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String[] tmp = br.readLine().split(" ");
			int n = Integer.parseInt(tmp[0]);
			int k = Integer.parseInt(tmp[1]);
			int j = Integer.parseInt(tmp[2]);
			//곡 트리 
			songTree = new ArrayList<>();
			//가수별 점수 정리 Map<가수,점수> 형태.
			finalScore = new HashMap<>();

			//정해진 시간 언제넘나 하는 진짜 정답 저장할 배열 진짜진짜 최종...
			int[] answerTime = new int[n+1];
			int i;
			for(i=0; i<=n; i++) {
				songTree.add(new ArrayList<Integer>());
			}
			//dummy
			songTree.get(0).add(0);

			tmp = br.readLine().split(" ");
			//부모 노드 설정.
			for(i=2;i<=n;i++) {
				songTree.get(Integer.parseInt(tmp[i-2])).add(i);
			}
			//해당곡을 부른 가수의 정보 ... 
			singerInfo = new int[n+1];
			tmp = br.readLine().split(" ");
			for(i=1;i<=n;i++) {
				//1번곡은 1번가수가, 2번곡은 1번가수가, 3번곡은 3번가수가.. 이런 정보.
				singerInfo[i] = Integer.parseInt(tmp[i-1]);
			}

			for(i=1;i<=k;i++) {
				tmp = br.readLine().split(" ");
				//tmp[0] = time, tmp[1] = rootNodeNumber(song), tmp[2] = score
				int time = Integer.parseInt(tmp[0]);
				int s = Integer.parseInt(tmp[2]);
				int NodeNumber = Integer.parseInt(tmp[1]);
				boolean aloneFlag = false;
				int divScore;
				int howManySong;
				int middleScore;
				int[] tmpSingerScore = new int[n+1];
				if(songTree.get(NodeNumber).size() != 0) {
					divScore = s / ( songTree.get(NodeNumber).size() +1 );
				}else {
					aloneFlag = true;
					divScore = s;
				}

				if(aloneFlag) {
					howManySong = countSinger(singerInfo[NodeNumber]);


					//divide by zero...?
					//if(howManySong == 0) howManySong = 1;
					middleScore = divScore / howManySong;

					if(!finalScore.containsKey(singerInfo[NodeNumber])) {
						finalScore.put(singerInfo[NodeNumber],Long.valueOf(middleScore));
					}else {
						finalScore.put(singerInfo[NodeNumber], finalScore.get(singerInfo[NodeNumber])+Long.valueOf(middleScore));
					}
					//System.out.println("3. singer :"+singerInfo[NodeNumber]+"score : "+ finalScore.get(singerInfo[NodeNumber]));
					if(finalScore.get(singerInfo[NodeNumber]) > j && answerTime[singerInfo[NodeNumber]] == 0) {
						//System.out.println("2. singer :"+singerInfo[NodeNumber]+"score : "+ finalScore.get(singerInfo[NodeNumber]));
						answerTime[singerInfo[NodeNumber]] = time;
					}
					continue;				
				}

				// root node 에 대해 계산되지 않는 로직이라 보완함.. 

				tmpSingerScore[singerInfo[NodeNumber]] += divScore;
				for(int node : songTree.get(NodeNumber)) {
					tmpSingerScore[singerInfo[node]] += divScore;
				}

				// root node 에 대해 계산되지 않는 로직이라 보완함.. 2
				howManySong = countSinger(singerInfo[NodeNumber]);
				middleScore = tmpSingerScore[singerInfo[NodeNumber]] / howManySong;
				if(!finalScore.containsKey(NodeNumber)) {
						finalScore.put(singerInfo[NodeNumber],Long.valueOf(middleScore));
				}else {
					finalScore.put(singerInfo[NodeNumber], finalScore.get(singerInfo[NodeNumber])+Long.valueOf(middleScore));
				}
				//System.out.println("1. singer :"+singerInfo[NodeNumber]+"score : "+ finalScore.get(singerInfo[NodeNumber]));
				if(finalScore.get(singerInfo[NodeNumber]) > j &&  answerTime[singerInfo[NodeNumber]] == 0) {
					//System.out.println("singer :"+singerInfo[NodeNumber]+"score : "+ finalScore.get(singerInfo[NodeNumber]));
					answerTime[singerInfo[NodeNumber]] = time;
				}

				for(int node : songTree.get(NodeNumber)) {
					howManySong = countSinger(singerInfo[node]);

					middleScore = tmpSingerScore[singerInfo[node]]/howManySong;

					if(!finalScore.containsKey(node)) {
						finalScore.put(singerInfo[node],Long.valueOf(middleScore));
					}else {
						finalScore.put(singerInfo[node], finalScore.get(singerInfo[node])+Long.valueOf(middleScore));
					}
					if(finalScore.get(singerInfo[node]) > j &&  answerTime[singerInfo[node]] == 0) {
						answerTime[singerInfo[node]] = time;
					}
				}

			}

			//정답 출력
			for(i=1; i<=n; i++) {
				if(answerTime[singerInfo[i]]!=0) {
					System.out.println(answerTime[singerInfo[i]]);
				}else {
					System.out.println("-1");
				}
			}

		}catch(Exception e) {
			System.out.println("-1");
			//e.printStackTrace();
		}
	}
	public static int countSinger(int singer) {
		int count = 0;
		for(int s: singerInfo) {
			if(singer == s) count++;
		}
		return count;
	}
}
