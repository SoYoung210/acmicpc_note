import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KA_3 {
	public static void main (String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String[] tmp = br.readLine().split(" ");
			int n = Integer.parseInt(tmp[0]);
			int k = Integer.parseInt(tmp[1]);
			int j = Integer.parseInt(tmp[2]);


			Map<Integer, Integer> answer = new HashMap<>();
			List <ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>> ();
			int[] print_answer = new int[n+1];


			int i;
			for(i=0; i<n+1; i++) {
				g.add(new ArrayList<Integer>());
			}
			g.get(0).add(0);

			String[] song_root = br.readLine().split(" ");

			for(i=0;i<n-1; i++) {
				g.get(Integer.parseInt(song_root[i])).add(i+2);
			} 
			String[] singer_info = br.readLine().split(" ");

			List<Integer> singer = new ArrayList<>();
			int[] arr_singer = new int[n+1];
			singer.add(0);
			int[] singer_score = new int[n+1];

			for(i=1; i<=n; i++) {
				singer.add(Integer.parseInt(singer_info[i-1]));
				arr_singer[i] = Integer.parseInt(singer_info[i-1]);
			}

			for(i=0; i<k; i++) {

				String[] test_algo = br.readLine().split(" ");
				//t 도 저장을 해야겟네 ...
				int time = Integer.parseInt(test_algo[0]);
				int p = Integer.parseInt(test_algo[1]);
				int s = Integer.parseInt(test_algo[2]);

				if(g.get(p).size() != 0)
					singer_score[p] = s/(g.get(p).size()+1);
				else
					singer_score[p] = s;

				Map<Integer, Integer> count_score = new HashMap<>();

				int mean_score = s/(g.get(p).size()+1);
				
				for(int a : g.get(p)) {
					
					singer_score[arr_singer[a]] = s/(g.get(p).size()+1);
					//System.out.println("singer :"+arr_singer[a]+"  singer_score[a] :"+singer_score[a]);
				}
				for(int b=1; b<=n; b++) {
					//System.out.println("arr_singer[b] : "+arr_singer[b]);
					if(count_score.containsKey( arr_singer[b] )) {
						//System.out.println("contains ");
						count_score.put(arr_singer[b],  count_score.get(arr_singer[b]) + singer_score[arr_singer[b]]);
						System.out.println("contains "+count_score.get(arr_singer[b]));
					}else {
						count_score.put(arr_singer[b], singer_score[arr_singer[b]]);
					}
				}

				for(int key : count_score.keySet()) {
					System.out.println("key : "+key);
					int tmp_score = count_score.get(key) / Collections.frequency(singer, key);
					System.out.println("tmp_score : "+tmp_score);
					if(answer.containsKey(key)) {
						answer.put( key, answer.get(key) + tmp_score);
						System.out.println("answer "+answer.get(key));
					}else {
						answer.put( key, tmp_score);
					}
					if(answer.get(key) > j) {
						for(int t=1; t<=n; t++) {
							if(arr_singer[t] == key) {
								print_answer[t] = time;
							}
						}
					}
				}
				count_score.clear();
			}
				for(i=1; i<=n; i++) {
					if(print_answer[i] !=0)
						System.out.println(print_answer[i]);
					else
						System.out.println("-1");
				}			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
