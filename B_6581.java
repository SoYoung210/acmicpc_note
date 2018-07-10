import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_6581 {
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//sb.length() 가능. sb.append("xyz") 형태. stringBuilderObj.setLength(0) 가능.
		StringBuilder sb = new StringBuilder();
		String tmp = br.readLine();
		String word;
		int index;
		while(tmp!=null) {
			String[] token = tmp.split(" ");

			int wordcount = token.length;
			for(index =0; index<wordcount; index++) {
				word = token[index];
				if(word.equals("<hr>")) {

				}else if(word.equals("<br>")) {

				}else {
					
				}
			}
		}
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