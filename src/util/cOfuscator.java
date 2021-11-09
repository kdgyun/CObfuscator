package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import io.Reader;

// 1. 모든 주석 제거

public class cOfuscator implements generalOfuscator {
	private static final String[] SPECIFIC_STRING_SET = { "'.'", "#", "#...", "\"...\"", "include", "define", "undef",
			"if", "ifdef", "else", "elif", "ifndef", "error", "endif", "pragma", "line" };

	private static HashSet<String> SPECIFIC_STRING_HASHSET = new HashSet<String>();
	static {
		SPECIFIC_STRING_HASHSET.addAll(Arrays.asList(SPECIFIC_STRING_SET));
	}

	private static int[] SKIP_CHARSET = { ' ', '\n', '\r', '\b', '\t', '+', '-', '>', '.', ',', '/', '\\', '#', '@',
			'~', '!', '*', '$', '=', '>', '<', '?', ':' };
	private static int[] STRING_OR_CHARACTOR_SET = { '"', '\'' };
	private static int[] LINE_SKIP_CHARSET = { '/', '/', '*' };
	private final static int[] SPLITTER_CHARSET = { '(', ')', '[', ']', '{', '}' };
	private static final String EXEC1 = "tokenizer -l C++ -t c ";
	private static final String EXEC2 = " > Conv_";

	private Reader rd;
	private HashMap<String, Integer> filterMap;
	private static Path taskPath = null;

	@Override
	public boolean run(String path) {
		if (!scanning(path))
			return false;
		if(!readAllTokens(taskPath)) {
			return false;
		}
		return false;
	}

	@Override
	public boolean scanning(String path) {
		Path procFilePath = null;
		try {
			procFilePath = Preprocessing(path);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		if (procFilePath == null)
			return false;
		Runtime runtime = Runtime.getRuntime();
		Process proc = null;
		try {

			// exec run path 교정 필요
			proc = runtime.exec(EXEC1 + procFilePath.toString() + EXEC2 + procFilePath.toString());
			taskPath = Paths.get("Conv_" + procFilePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
				proc.destroy();
				return false;
			}
			proc.destroy();
		}
		return true;
	}

	private Path Preprocessing(String filePath) throws IOException {
		Path removeRemarkFilePath = ReBuilder.reBuild(filePath);
		Path cleanFilePath = Paths.get("Ign_" + filePath);
		filterMap = ReBuilder.ignoredpreProcessorTokens(removeRemarkFilePath, cleanFilePath);
		return cleanFilePath;
	}

	/**
	 * 토큰 파일 읽고 map push
	 * 
	 * @param path tokenFile path
	 * @return 성공시 true 
	 */
	private boolean readAllTokens(Path path) {
		Path open = path;
		List<String> lines;
		try {
			lines = Files.readAllLines(open);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		for (String line : lines) {
			if (!SPECIFIC_STRING_HASHSET.contains(line)) {
				filterMap.put(line, filterMap.getOrDefault(line, 0) + 1);
			}
		}
		return true;
	}

	
	// faster than regex (추후 지원용..)
	private boolean integerMatcher(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	final static int splitter(int a) {
		switch (a) {
		case -1:
			return -1;
		case ' ':
			return -2;
		case '\n':
			return -3;
		case '\r':
			return -4;
		case '\b':
			return -5;
		case '(':
			return -6;
		case ')':
			return -7;
		case '[':
			return -8;
		case ']':
			return -9;
		case '{':
			return -10;
		case '}':
			return -11;
		default:
			return a;

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
