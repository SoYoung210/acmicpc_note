import java.util.Stack;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//다 넣고 개수만큼 돌다가 특정 숫자 개수 보다 적개 있으면 증가 x 아니면 증가.
public class B_1475 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> card = new Stack<>();
		//입력 받
		char[] input = br.readLine().toCharArray();
		//card.addAll(Arrays.asList();

		//set의 개수.
		int answer = 1;
		int size = input.length;
		int i;

		//하나 넣고 시작. 
		card.push(input[0]);
		//그래서 1부터 시작.
		for(i=1;i<size;i++) {
			char ch = input[i];
			//한세트인 경우들. card 스택에 ch 가 없거나, 두 숫자가 각각 6이나 9인 경우.
			if( !card.contains(ch) ) {
				card.push(ch);
			}else {
				//stack에 이미 5가 있는데 또 5가 들어온경우, 카드 세트의 개수보다 5의 개수가 적으면 + 할필요가 없고 아니면 셋트를 늘린다.
				int occurrences;
				if(ch!='6' && ch !='9') {
					occurrences = Collections.frequency(card, ch);
					if(occurrences >= answer) {
						answer++;
					}
					card.push(ch);
				}else {
					//occurrences = Collections.frequency(card, ch);
					switch(ch) {
						case '6':
							occurrences = Collections.frequency(card, '6');
							if(occurrences >= answer) {
								occurrences = Collections.frequency(card, '9');
								if(occurrences >= answer) {
									card.push('6');
									answer++;
								}else {
									//9를 뒤집어서 쓸거니까 
									card.push('9');
								}
							}else {
								card.push('6');
							}
							break;
						case '9':
							occurrences = Collections.frequency(card, '9');
							if(occurrences >= answer) {
								occurrences = Collections.frequency(card, '6');
								if(occurrences >= answer) {
									card.push('9');
									answer++;
								}else {
									card.push('6');
								}
							}else {
								card.push('9');
							}
							break;
					}
				}
			}

		}

		System.out.println(answer);
	
	}
}