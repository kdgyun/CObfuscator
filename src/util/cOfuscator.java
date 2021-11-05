package util;

import java.util.HashMap;
import io.Reader;

// 1. 모든 주석 제거


public class cOfuscator implements generalOfuscator {
	
	private static int[] SKIP_CHARSET = {' ', '\n', '\r', '\b', '\t', '+', '-', '>', '.', ',' ,'/', '\\', '#', '@', '~' ,'!', '*', '$', '=', '>', '<', '?', ':'};
	private static int[] STRING_OR_CHARACTOR_SET = {'"', '\''};
	private static int[] LINE_SKIP_CHARSET = {'/', '/' ,'*'};
	private final static int[] SPLITTER_CHARSET = { '(', ')', '[', ']', '{', '}'};
	
	private Reader rd;
	private HashMap<String, Integer> filterMap;
	
	@Override
	public void scanning() {
	
		int b;
		outer:
		while((b = rd.read()) != -1) {
			
		}
	}

	private void readSentence() {
		StringBuilder sb = new StringBuilder();
		int b;
		while((b = splitter(rd.read())) > -1) {
			sb.append((char) b);
		}
		filterMap.put(sb.toString(), filterMap.getOrDefault(sb.toString(), 0) + 1);
		if(b != -1) {
			
		}
	}
	
	final static int splitter(int a) {
		switch(a) {
		case -1 : return -1;
		case ' ': return -2;
		case '\n':return -3;
		case '\r':return -4;
		case '\b':return -5;
		case '(':return -6;
		case ')':return -7;
		case '[':return -8;
		case ']':return -9;
		case '{':return -10;
		case '}':return -11;
		default :return a;
		
		
		}
	}

	@Override
	public String readString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void valueZipping() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convert() {
		// TODO Auto-generated method stub
		
	}

	
}
