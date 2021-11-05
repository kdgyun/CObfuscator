package util;


import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReBuilder {

	private static final String REGEX = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";

	public static void reBuild(String filePath) throws IOException {
		Path sourceFilePath = Paths.get(filePath);
		Path newFilePath = Paths.get("Obf_" + filePath);
		String s = Files.readString(sourceFilePath);

		s = s.replaceAll(REGEX, "$1 ");
		Files.writeString(newFilePath, s, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}

	
}
