import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class RecoAlgo implements Comparable<RecoAlgo>{
	private int time;
	private int rootNodeNumber;
	private int score;

	RecoAlgo(int time, int rootNodeNumber, int score) {
		this.time = time;
		this.rootNodeNumber = rootNodeNumber;
		this.score = score;
	}
	public int getTime() {
		return this.time;
	}
	public int getRootNodeNumber() {
		return this.rootNodeNumber;
	}
	public int getScore() {
		return this.score;
	}
	@Override
	public int compareTo(RecoAlgo o){
		if(this.time<o.time) {
			return -1;
		}else if(this.time > o.time) {
			return 1;
		}else {
			return 0;
		}
    }
}


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
			//추천알고리즘 시간순으로 정렬받을 자료구조 .
			PriorityQueue<RecoAlgo> q = new PriorityQueue<RecoAlgo> ();
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
			for(i=1; i<=k; i++) {
				tmp = br.readLine().split(" ");
				long time = Integer.parseInt(tmp[0]);
				long score = Integer.parseInt(tmp[2]);
				long nodeNumber = Integer.parseInt(tmp[1]);

				q.offer(new RecoAlgo(time,nodeNumber,score));				
			}
			while(!q.isEmpty()) {
				
				RecoAlgo r = q.poll();

				long T = r.getTime();
				long P = r.getRootNodeNumber();
				long S = r.getScore();

				boolean aloneFlag = false;
				long divScore;
				long howManySong;
				long middleScore;
				long[] tmpSingerScore = new long[n+1];
				if(songTree.get(P).size() != 0) {
					divScore = S / ( songTree.get(P).size() +1 );
				}else {
					aloneFlag = true;
					divScore = S;
				}

				if(aloneFlag) {
					howManySong = countSinger(singerInfo[P]);
					//divide by zero...?
					//if(howManySong == 0) howManySong = 1;
					middleScore = divScore / howManySong;

					if(!finalScore.containsKey(singerInfo[P])) {
						finalScore.put(singerInfo[P],Long.valueOf(middleScore));
					}else {
						finalScore.put(singerInfo[P], finalScore.get(singerInfo[P])+Long.valueOf(middleScore));
					}
					if(finalScore.get(singerInfo[P]) > j && answerTime[singerInfo[P]] == 0) {
						finalScore.put(singerInfo[P] , -1L);
						answerTime[singerInfo[P]] = T;
					}
					continue;				
				}

				// root node 에 대해 계산되지 않는 로직이라 보완함.. 

				tmpSingerScore[singerInfo[P]] += divScore;
				for(int node : songTree.get(P)) {
					tmpSingerScore[singerInfo[node]] += divScore;
				}

				// root node 에 대해 계산되지 않는 로직이라 보완함.. 2
				howManySong = countSinger(singerInfo[P]);
				middleScore = tmpSingerScore[singerInfo[P]] / howManySong;
				if(!finalScore.containsKey(P)) {
					finalScore.put(singerInfo[P],Long.valueOf(middleScore));
				}else {
					finalScore.put(singerInfo[P], finalScore.get(singerInfo[P])+Long.valueOf(middleScore));
				}

				if(finalScore.get(singerInfo[P]) > j &&  answerTime[singerInfo[P]] == 0) {
					finalScore.put(singerInfo[P] , -1L);
					answerTime[singerInfo[P]] = T;
				}

				for(int node : songTree.get(P)) {
					howManySong = countSinger(singerInfo[node]);

					middleScore = tmpSingerScore[singerInfo[node]]/howManySong;

					if(!finalScore.containsKey(node)) {
						finalScore.put(singerInfo[node],Long.valueOf(middleScore));
					}else {
						finalScore.put(singerInfo[node], finalScore.get(singerInfo[node])+Long.valueOf(middleScore));
					}
					if(finalScore.get(singerInfo[node]) > j &&  answerTime[singerInfo[node]] == 0) {
						//overflow 방지.
						finalScore.put(singerInfo[node] , -1L);
						answerTime[singerInfo[node]] = T;
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
			//System.out.println("-1");
			e.printStackTrace();
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
