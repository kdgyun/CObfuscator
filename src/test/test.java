
package test;

import java.util.Comparator;
public class test {
	private final static String[] SPLITTER_CHARSET = { null, null, " ", "\n", "\r", "\b", "(", ")", "[", "]", "{",
			"}" };

	public static void main(String[] args) {

		int[] seq = { ' ', '\n', '\r', '\b', '(', ')', '[', ']', '{', '}', '!', '@', '#', '$', '%', '^', '&', '*', '/',
				'\'', '|' };

		for (int s : seq) {
			System.out.println(String.format("0x%04x", s));
		}
		System.out.println();

		String[] a = { "++", "--", "(", ")", "[", "]", ".", "->", "{", "}", "+", "-", "!", "~", "*", "&", "/", "%",
				"<<", ">>", "<=", ">=", "==", "!=", "&", "^", "|", "&&", "||", "?", ":", "=", "+=", "-=", "*=", "/=",
				"%=", "<<=", ">>=", "&=", "^=", "|=" };
		
		java.util.Arrays.sort(a, comp);
		
		for(String s : a) {
			System.out.print(s + ", ");
		}
	}
	
	public static Comparator<String> comp = new Comparator<String>() { 
		
		@Override
		public int compare(String o1, String o2) {
			return o2.length() - o1.length();
		}
	};
}
