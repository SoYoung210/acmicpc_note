import java.util.*;
import java.io.*;

public class B_6581 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//sb.length() 가능. sb.append("xyz") 형태. stringBuilderObj.setLength(0) 가능.
		StringBuilder sb = new StringBuilder();
		String tmp = br.readLine();
		if(tmp.length() == 0)
			return;
		String word;
		int index;
		int eightCount = 0;


		while(!tmp.equals("0")) {
			String[] token = tmp.split(" ");

			int wordcount = token.length;
			for(index =0; index<wordcount; index++) {
				word = token[index];
				if(word.equals("<hr>")) {
					if(eightCount !=0) {
						sb.deleteCharAt(sb.length()-1);
						sb.append("\n");
						eightCount = 0;
					}
					sb.append("--------------------------------------------------------------------------------\n");
				}else if(word.equals("<br>")) {
					if(sb.charAt(sb.length()-1)!='\n')
						sb.deleteCharAt(sb.length()-1);
					sb.append("\n");
					eightCount = 0;

				}else if(word.equals("")) {
					continue;
				}else {
					if(eightCount + token[index].length() <=80) {
						eightCount += token[index].length();
					}else {
						sb.append("\n");
						eightCount = token[index].length();
					}
					sb.append(word);
					if(eightCount<80 && eightCount!=0) {
						sb.append(" ");
						eightCount++;
					}					
				}
			}
			tmp = br.readLine();

		}
		String answer = sb.toString();
		bw.write(answer.substring(0,answer.length() - 1));
		bw.close();
		br.close();
	}
}