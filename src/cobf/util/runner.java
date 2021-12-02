/*
 *  Copyright 2021 kdgyun All rights reserved.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cobf.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.LinkedHashSet;

/**
 * 
 * @author kdgyun
 * @since 1.0.0
 * @version 1.0.5
 * 
 */


public class runner {

	final static LinkedHashSet<String> OptionList = new LinkedHashSet<String>();
	static {
		OptionList.add("--F");
		OptionList.add("--f");
		OptionList.add("--p");
		OptionList.add("--P");
	}

	public static void main(String[] args) throws IOException {
		Path path = Paths.get(new runner().getClass().getClassLoader().getResource("cobf").getPath());

		boolean isPrint = false;
		String complete = null;
		if (args == null || args.length == 0) {
			complete = new cObfuscator().run(path + "/test/inputFile/main.c", path + "/test/outputFile/Obfmain.c", true);
			System.out.println(complete);
		} else {
			if (args.length > 3) {
				throw new IllegalArgumentException("IllegalArguments");
			}

			String p1 = null;
			String p2 = null;
			String option = null;
			for (String s : args) {
				final String arg = s;
				if (arg.length() > 2 && arg.charAt(0) == '-' && arg.charAt(1) == '-') {

					if (OptionList.contains(arg)) {

						if (option == null) {
							switch (arg) {
							case "--p":
								isPrint = true;
								break;
							case "--P":
								isPrint = true;
								break;
							}
							option = arg;
						} else {
							throw new IllegalArgumentException("Not a valid argument : too many argument of option ["
									+ option + "], [" + arg + "]");
						}

					} else {
						throw new IllegalArgumentException("Not a valid argument of option : wrong type [" + arg + "]");
					}
				}

				else {
					int la = arg.lastIndexOf(".");
					if (la > 0) {
						String f = arg.substring(la + 1);

						if (f.equals("cpp") || f.equals("c") || f.equals("cc")) {
							if (p1 == null) {
								if (!new File(path + arg).exists()) {
									throw new FileNotFoundException();
								}
								p1 = arg;
							} else if (p2 == null) {
								p2 = arg;
							} else {
								throw new IllegalArgumentException(
										"Not a valid argument : too many argument of file [" + arg + "]");
							}
						} else {
							throw new IllegalArgumentException(
									"Not a valid argument : Unsupported extension [" + arg + "]");
						}
					}
				}

			}

			
			if (args.length == 1) {

				if (p1 == null) {
					if (isPrint) {
						complete = new cObfuscator().run(path + "/test/inputFile/main.c",
								path + "/test/outputFile/Obfmain.c", true);
						System.out.println(complete);

					} else {
						complete = new cObfuscator().run(path + "/test/inputFile/main.c",
								path + "/test/outputFile/Obfmain.c", false);
					}
				} else {
					Path p = Paths.get(p1);
					complete = new cObfuscator().run(p1, p.getParent() + "/Obf" + p.getFileName(), true);
					System.out.println(complete);
				}

			} else if (args.length == 2) {
				if (option == null) {
					complete = new cObfuscator().run(p1, p2, false);
				} else {

					if (isPrint) {
						Path p = Paths.get(p1);
						complete = new cObfuscator().run(p1, p.getParent() + "/Obf" + p.getFileName(), true);
						System.out.println(complete);
					} else {
						Path p = Paths.get(p1);
						complete = new cObfuscator().run(p1, p.getParent() + "/Obf" + p.getFileName(), false);
					}
				}
			} else if (args.length == 3) {
				if (isPrint) {
					complete = new cObfuscator().run(p1, p2, true);
					System.out.println(complete);
				} else {
					complete = new cObfuscator().run(p1, p2, false);

				}
			} else {
				throw new IllegalArgumentException("Not a valid argument : too may arguments");
			}
		}
		if (complete != null) {
			System.out.println("\n\nComplete and Finishing");
		} else {
			System.out.println("\n\nFinishing an Incomplete");
		}
	}

}
