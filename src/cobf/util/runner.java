package cobf.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class runner {
	public static void main(String[] args) throws IOException {
		if(args == null || args.length == 0) {
			
			new cObfuscator().run("src/cobf/test/inputFile/main.c", "src/cobf/test/outputFile/Obfmain.c", true);
		}
		else if(args.length < 2){
			if(args[0].equals("-f")) {
				new cObfuscator().run("src/cobf/test/inputFile/main.c", "src/cobf/test/outputFile/Obfmain.c", false);
			}
			else if(args[0].equals("-p")) {
				new cObfuscator().run("src/cobf/test/inputFile/main.c", "src/cobf/test/outputFile/Obfmain.c", true);
			}
			else {
				Path p = Paths.get(args[0]);
				new cObfuscator().run(args[0], p.getParent() + "/Obf" + p.getFileName(), true);
			}
		}
		else if(args.length < 3) {
			Path p = Paths.get(args[0]);
			if(args[1].equals("-f")) {
				new cObfuscator().run(args[0], p.getParent() + "/Obf" + p.getFileName(), false);
			}
			else if(args[1].equals("-p")) {
				new cObfuscator().run(args[0], p.getParent() + "/Obf" + p.getFileName(), true);
			}
			else {
				new cObfuscator().run(args[0], args[1], true);
			}
		}
		else if(args.length < 4) {
			if(args[2].equals("-f")) {
				new cObfuscator().run(args[0], args[1], false);
			}
			else if(args[2].equals("-p")) {
				new cObfuscator().run(args[0], args[1], true);
			}
			else {
				new cObfuscator().run(args[0], args[1], true);
			}
		}
	}

}