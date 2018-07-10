import java.util.*;
import java.io.*;

public class B_6581 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//sb.length() 가능. sb.append("xyz") 형태. stringBuilderObj.setLength(0) 가능.
		StringBuilder sb = new StringBuilder();
		String tmp = br.readLine();
		String word;
		int index;
		int eightCount = 0;


		while(tmp!=null) {
			System.out.println("tmp:"+tmp);
			String[] token = tmp.split(" ");

			int wordcount = token.length;
			for(index =0; index<wordcount; index++) {
				word = token[index];
				if(word.equals("<hr>")) {
					if(eightCount !=0) {
						sb.append("\n");
						eightCount = 0;
					}
					sb.append("--------------------------------------------------------------------------------\n");
				}else if(word.equals("<br>")) {
					if(sb.charAt(sb.length()-1)!='\n') {
						sb.append("\n");
						eightCount = 0;
					}
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
				}
				if(eightCount<80 && eightCount!=0) {
					sb.append(" ");
					eightCount++;
				}
			}
			tmp = br.readLine();

		}
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
}
/*
Hallo, dies ist eine 
ziemlich lange Zeile, die in Html
aber nicht umgebrochen wird.
<br>
Zwei <br> <br> produzieren zwei Newlines. 
Es gibt auch noch das tag <hr> was einen Trenner darstellt.
Zwei <hr> <hr> produzieren zwei Horizontal Rulers.
Achtung       mehrere Leerzeichen irritieren

Html genauso wenig wie


mehrere Leerzeilen.
*/