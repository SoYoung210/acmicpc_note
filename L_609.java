import java.util.*;
//디렉토리 경로를 포함한 디렉토리 정보 목록과, 
//디렉토리에 내용이 있는 모든 파일 목록이 주어지면, 
//파일 시스템에 있는 모든 중복파일 그룹의 경로를 ...

public class L_609 {
	static Map<String, List<String>> fileInfo;
	public static void main (String[] args) {
		String[] test1 = {"root/a 1.txt(abcd) 2.txt(efgh)", 
						  "root/c 3.txt(abcd)", 
						  "root/c/d 4.txt(efgh)", 
						  "root 4.txt(efgh)"};
		System.out.println(findDuplicate(test1));
	}
    public static List<List<String>> findDuplicate(String[] paths) {
    	List<List<String>> answer = new ArrayList<>();
    	fileInfo = new HashMap<>();
    	int i;
    	int len = paths.length;

    	
    	int contentCount = 0;
    	for(i =0; i<len; i++) {
    		//find ( , for substring

    		String[] parsingInfo = paths[i].split(" ");
    		contentCount = parsingInfo.length;
    		for(int k=1; k<contentCount; k++) {
    			String parsingDirInfo = parsingInfo[0];
    			int contentIndexFirst = parsingInfo[k].indexOf("(");
    			int contentIndexLast = parsingInfo[k].indexOf(")");
    			//(abcd) ... abcd
    			String parsedContent = parsingInfo[k].substring(contentIndexFirst+1,contentIndexLast);
    			String parsingFileInfo = parsingInfo[k].substring(0,contentIndexFirst);

    			String fullPath = parsingDirInfo+"/"+parsingFileInfo;
    			//System.out.println("fullPath "+fullPath);
	     		if(fileInfo.containsKey(parsedContent)) {
	     			fileInfo.get(parsedContent).add(fullPath);
	    		}else {
	    			List<String> temp = new ArrayList<>();
	    			temp.add(fullPath);
	    			//needs dir, filename 
	    			fileInfo.put(parsedContent, temp);
	    		}
    		}
    	}

    	for(String str : fileInfo.keySet()) {
    		//System.out.println(str+" : "+fileInfo.get(str));
    		if(fileInfo.get(str).size()>1) answer.add(fileInfo.get(str));
    	}
    	return answer;
    }	
}
