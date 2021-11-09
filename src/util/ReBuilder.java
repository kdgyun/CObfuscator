package util;


import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class ReBuilder {

	private static final String REGEX = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";
	private static final String SPACE_REGEX = "//s+";
	private static final String[] SPECIFIC_STRING_SET = {"include", "define", "undef", "if", "ifdef", "else", "elif", "ifndef", "error", "endif", "pragma", "line"};
	private static final String START_SPECIFIC_START = "#";
	
	public static Path reBuild(String filePath) throws IOException {
		Path sourceFilePath = Paths.get(filePath);
		Path newFilePath = Paths.get("Obf_" + filePath);
		String s = Files.readString(sourceFilePath);

		s = s.replaceAll(REGEX, "$1 ");
		Files.writeString(newFilePath, s, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING);
		return newFilePath;
	}

	public static HashMap<String, Integer> ignoredpreProcessorTokens(Path filePath, Path toPath) throws IOException {
		
		Path sourceFilePath = filePath;
		Path newFilePath = toPath;
		HashMap<String, Integer> map = new HashMap<String, Integer>();	// 전처리문 토큰
		
		List<String> lines = Files.readAllLines(sourceFilePath);
		String put;
		StringBuilder sb = new StringBuilder();
		for(String line : lines) {
			line.replaceAll(SPACE_REGEX, " ");
			int idx = line.indexOf(START_SPECIFIC_START);
			if(idx > 0) {
				put = getDefinePreprocessing(line.substring(idx + 1));

				if(put == null) continue;
				map.put(put, map.getOrDefault(put, 0) + 1);
				line = line.substring(0, idx);
			}
			else if(idx == 0) {
				put = getDefinePreprocessing(line.substring(idx + 1));

				if(put == null) continue;
				map.put(put, map.getOrDefault(put, 0) + 1);
				continue;
			}
			sb.append(line + "\n");

		}
		Files.writeString(newFilePath, sb.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING);
		return map;
			
	}
	
	private static String getDefinePreprocessing(String line) {
		StringTokenizer st = new StringTokenizer(line, " |(");
		String sp = st.nextToken();

		for(String s : SPECIFIC_STRING_SET) {
			if(sp.equals(s)) {
				return st.nextToken();
			}
		}
		return null;
	}
	
}
