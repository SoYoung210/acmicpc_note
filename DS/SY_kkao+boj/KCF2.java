import java.util.*;

public class KCF2 {
	public static int solution(int cacheSize, Stirng[] cities) {
		//소요시간, 즉 정답.
		int time = 0;
		if(cacheSize==0) {
			return cities.length *5;
		}
		String[] cache = new String[cacheSize];
		int index = 0;
		int cache_index = 0;
		int citi_len = cities.length;
		boolean isHit = false;
		//캐쉬부터 채우자.
		for(index=0; index<=cacheSize; index++) {
			time += 5;
			cache[index] = cities[index];
		}
		while(index < citi_len) {
			for(cache_index = 0; cache_index<cacheSize; cache_index++) {
				
			}

			index++;
		}
	}
	public static void main (String[] args) throws Exception {
		
		
	}
}	