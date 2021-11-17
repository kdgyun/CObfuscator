package util;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;



public class cOfuscator implements generalOfuscator {
	private static final String[] SPECIFIC_STRING_SET = {"include", "define", "undef",
			"if", "ifdef", "else", "elif", "ifndef", "error", "endif", "pragma", "line" };

	private static final String SKIP_TYPE1 = "directive";
	private static final String SKIP_TYPE2 = "whitespace";
	private static final String SKIP_TYPE3 = "line comment";
	private static final String SKIP_TYPE4 = "area comment"; 
	private static final String SKIP_TYPE5 = "area comment continue";
	private static final String CHAR_TYPE = "char";
	private static final String STRING_TYPE = "quote";
	private static final String NUMBER_TYPE = "number"; 
	private static final String OPEN_TYPE1 = "open paren";
	private static final String CLOSE_TYPE1 = "close paren";
	private static final String OPEN_TYPE2 = "open curly";
	private static final String CLOSE_TYPE2 = "close curly";
	private static final String OPEN_TYPE3 = "open square";
	private static final String CLOSE_TYPE3 = "close square";
	private static final String DEFINE_TYPE = "include";
	private static final String INCLUDE_TYPE = "define";
	private static final String OPER_TYPE = "operator";
	private static final String WHITE_TYPE = "whitespace";
	private static final String COMMA_TYPE = ",";
	private static final String SPLITTER = "( afsjevijow42f2f3315fljksf=> )";
	private static HashSet<String> SPECIFIC_STRING_HASHSET = new HashSet<String>();
	static {
		SPECIFIC_STRING_HASHSET.addAll(Arrays.asList(SPECIFIC_STRING_SET));
	}


	
	int offset = 1;	// convert 되는 padding 값
	
	private static class TokenStruct {
		
		String value;
		String type;
		TokenStruct(String value, String type) {
			this.value = value;
			this.type = type;
		}
		
		String getterType() {
			return this.type;
		}

		
		@Override
		public int hashCode() {
			String ss = value + type;
		    return ss.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o instanceof TokenStruct) {
	        	TokenStruct aTokenStruct = (TokenStruct)o;
	            if(this.value.equals(aTokenStruct.value) && this.type.equals(aTokenStruct.type)) {
	            	return true;
	            }
	        }
	        return false;
		}
		
	}
				
	private HashMap<TokenStruct, String> filterMap = new HashMap<TokenStruct, String>(); // <value, TYPE>, converto 
	private HashMap<String, int[]> convertMap = new HashMap<String, int[]>(); // converto, <value, TYPE> 
	private static Path taskPath = null;
	
	@Override
	public boolean run(String path) {
		if (!scanningUsingJStokenizer(path)) {
			System.out.println("scanningUsingJStokenizer is failed");
			return false;
		}
		if(!readAllTokensUsingJS(taskPath)) {
			System.out.println("readAllTokensUsingJS is failed");
			return false;
		}
		getConvertSet();
		valueZipping(taskPath, Paths.get(taskPath.getParent().toString() + "/conv_" + taskPath.getFileName()));
		return true;
	}

	
	private static final String[] pathSet(Path filePath1, Path filePath2) {
		Path p = Paths.get(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getParent();
		System.out.println("this Path is : " + p);
		return new String[] {p + "/bin/run.sh",  filePath1.toString() , filePath2.toString()};
	}
	


	public boolean scanningUsingJStokenizer(String path) {
		Path procFilePath = null;

		procFilePath = Paths.get(path);
		
		if (procFilePath == null)
			return false;
		Process proc = null;
		try {
			taskPath = Paths.get(procFilePath.getParent().toString() + "/task_" + procFilePath.getFileName().toString());


			String[] pah = pathSet(procFilePath, taskPath);
			ProcessBuilder pb = new ProcessBuilder(pah);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectError(ProcessBuilder.Redirect.INHERIT);
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);

			proc = pb.start();
			proc.waitFor();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			System.out.println("not working");
			e.printStackTrace();
			proc.destroy();
			return false;
		} finally {
			
		
			proc.destroy();

		}
		return true;
	}

	
	
	/**
	 * 토큰 파일 읽고 map push
	 * 
	 * @param path tokenFile path
	 * @return 성공시 true 
	 */
	private boolean readAllTokensUsingJS(Path path) {
		Path open = path;
		List<String> lines;
		try {
		lines = Files.readAllLines(open);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		String[] type = null;
		for (String line : lines) {
			type = line.split(SPLITTER, 2);
			try {
				String s1 = type[0];
				String s2 = type[1];
			filterMap.put(new TokenStruct(s2, s1), null);
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				System.out.println("string : " + line);
				System.exit(0);
			}
		}
		return true;
	}
	



	@Override
	public String readString() {
		return null;
	}

	@Override
	public void valueZipping(Path from, Path to) {
		Path sourceFilePath = from;
		Path newFilePath = to;
		try {
			Files.readString(sourceFilePath);
			List<String> lines = Files.readAllLines(sourceFilePath);
			StringBuilder replaceTokenBuilder = new StringBuilder();	// define 될 토큰
			StringBuilder textBuilder = new StringBuilder();		// token으로 재정의 될 text
			final String strPre = "_".repeat(offset++);
			final String operPre = "_".repeat(offset++);
			final String open1 = "_".repeat(offset++);
			final String close1 = "_".repeat(offset++);
			final String open2 = "_".repeat(offset++);
			final String close2 = "_".repeat(offset++);
			final String open3 = "_".repeat(offset++);
			final String close3 = "_".repeat(offset++);
			final String semi = "_".repeat(offset++);
			final String comma = "_".repeat(offset++);
			final String define = "# define ";
			HashSet<String> operSet = new HashSet<String>();
			replaceTokenBuilder.append(define).append(strPre).append("(x) #x").append('\n');
			replaceTokenBuilder.append(define).append(operPre).append("(x) x").append('\n');
			replaceTokenBuilder.append(define).append(open1).append(" (").append('\n');
			replaceTokenBuilder.append(define).append(open2).append(" {").append('\n');
			replaceTokenBuilder.append(define).append(open3).append(" [").append('\n');
			replaceTokenBuilder.append(define).append(close1).append(" )").append('\n');
			replaceTokenBuilder.append(define).append(close2).append(" }").append('\n');
			replaceTokenBuilder.append(define).append(close3).append(" ]").append('\n');
			replaceTokenBuilder.append(define).append(semi).append(" ;").append('\n');
			replaceTokenBuilder.append(define).append(comma).append(" ,").append('\n');
			String[] spl;
			for(String line : lines) {
				spl = line.split(SPLITTER, 2);
				String type = spl[0];
				String text = spl[1];
				String replacedString = filterMap.get(new TokenStruct(text, type));
				if(text.equals(WHITE_TYPE)) {
					continue;
				}
				switch(type) {
				
				case SKIP_TYPE1 :
					text = text.substring(1, text.length() - 1).replace("\\\"", "\"");
					
					if(text.contains(INCLUDE_TYPE) || text.contains(DEFINE_TYPE)) {
						replaceTokenBuilder.append(text + "\n");
					}
					else { textBuilder.append(text + "\n"); }
					break;
				case NUMBER_TYPE :
					text = text.replace("\"", "");
					textBuilder.append("0x").append(new BigInteger(text).toString(16)).append(' '); break;
				case CHAR_TYPE :
					text = text.replace("\"", "");
					String cc = text.replaceAll("'", "");
					textBuilder.append("0x").append(new BigInteger(cc).toString(16)).append(' '); break;
				case STRING_TYPE :
					text = text.substring(1, text.length() - 1);
					text = text.replace("\\\\", "\\");
					text = text.replace("\\\"", "\"");
					if(text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"') {
						text = text.substring(1, text.length() - 1);
					}
					if(replacedString == null) break;
					if(convertMap.get(replacedString) == null)	{ // token이 define 되지 않은 상태일 경우
					// # define ____ _____(stringv)
						replaceTokenBuilder.append(define).append(replacedString).append(" " + strPre).append("(" + text + ")\n");
						convertMap.put(replacedString, new int[]{});
					}
					
					// ____(stringv) 
					textBuilder.append(replacedString).append(" ");
					break;
				case OPER_TYPE :
					text = text.replace("\"", "");
					if(text.equals(COMMA_TYPE)) {
						textBuilder.append(comma).append(' '); 
						break;
					}
					if(operSet.add(text)) {	// 새로 추가 될 경우
						replaceTokenBuilder.append(define).append(replacedString).append(" " + operPre + "(" + text + ")\n");
					}
					textBuilder.append(replacedString).append(' ');
					break;
				case OPEN_TYPE1 :
					textBuilder.append(open1).append(' '); break;
				case OPEN_TYPE2 :
					textBuilder.append(open2).append(' '); break;
				case OPEN_TYPE3 :
					textBuilder.append(open3).append(' '); break;
				case CLOSE_TYPE1 :
					
					textBuilder.append(close1).append(' '); break;
				case CLOSE_TYPE2 :
					textBuilder.append(close2).append(' '); break;
				case CLOSE_TYPE3 :
					textBuilder.append(close3).append(' '); break;
				default :
					if(replacedString == null) continue;
					text = text.replace("\"", "");
					if(convertMap.get(replacedString) == null) {
						// # define ______(x0, x1, x2, ... xn) x0##x1##x2##....##xn

						
						// newly code
						Random rnd = new Random();
						int arraySize = 0;
						int minHold = text.length() << 2;
						if(minHold <= 0) minHold = Integer.MAX_VALUE - 1;
						int maxHold = text.length() << 4;
						if(maxHold <= 0) maxHold = Integer.MAX_VALUE - 1;	// overflow
						
						if(minHold == maxHold) { 
							arraySize = maxHold;
						}
						
						while(arraySize < minHold) {
							arraySize = rnd.nextInt(maxHold);
						}
						
						boolean[] flag = getSecureIdx(text.length(), arraySize);
						HashSet<String> rndMap = new HashSet<String>();
						
						// start define ____ (d1,e3,a,d2,c,e)
						replaceTokenBuilder.append(define).append(replacedString).append('(');
						int cnt = 0;
						// put random string
						while(cnt++ < arraySize) {
							int overlab = 0;
							String ch = getRandomChar();
							if(rndMap.contains(ch)) {
								while(rndMap.contains(ch + overlab)) {
									overlab++;
								}
								ch = new StringBuilder(ch).append(overlab).toString();
							}

							rndMap.add(ch);
						}
						
						String[] arr = rndMap.toArray(new String[rndMap.size()]);
						shuffleArray(arr);		// suffling
					
						// appending & indexsing
						replaceTokenBuilder.append(arr[0]);
						for(int i = 1; i < arr.length; i++) {
							replaceTokenBuilder.append(',').append(arr[i]);
						}
						replaceTokenBuilder.append(") ");
						String[] sarr = arr.clone();
						int[] idxArr = new int[arraySize];
						Arrays.fill(idxArr, -1);
						shuffleArray(sarr);		// suffling2
						int firstPointer = 0;
						while(!flag[firstPointer]) firstPointer++;

						replaceTokenBuilder.append(sarr[firstPointer]);
						int idxByText = 0;
						int p = 0;
						while(!arr[p].equals(sarr[firstPointer])) p++;
						idxArr[p] = idxByText;
						idxByText++;
						firstPointer++;
						
						for(int i = firstPointer; i < flag.length; i++) {
							if(flag[i]) {
								replaceTokenBuilder.append("##").append(sarr[i]);
								p = 0;
								while(!arr[p].equals(sarr[i])) p++;
								idxArr[p] = idxByText;
								idxByText++;
							}
						}
						replaceTokenBuilder.append('\n');
						convertMap.put(replacedString, idxArr);

					}
					

					int[] replIdx = convertMap.get(replacedString);
					textBuilder.append(replacedString).append('(');
					if(replIdx[0] == -1) {
						textBuilder.append(getRandomChar());
					}
					else {
						textBuilder.append(text.charAt(replIdx[0]));
					}
					
					for(int i = 1; i < replIdx.length; i++) {
						if(replIdx[i] == -1) {
							textBuilder.append(',').append(getRandomChar());
						}
						else {
							textBuilder.append(',').append(text.charAt(replIdx[i]));
						}
					}
					textBuilder.append(") ");
				}
				

			}
			
			// 그냥 sb하나 더 생성해서 따로따로 write?? 
			
			Files.writeString(newFilePath, replaceTokenBuilder.append('\n').append(textBuilder), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	

	}
	
	private static void shuffleArray(String[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	
	private boolean[] getSecureIdx(int length, int maxHold) {
		Random rnd = new Random();
		int cnt = 0;
		boolean[] b = new boolean[maxHold];
		while(cnt < length) {
			int idx = rnd.nextInt(maxHold);
			if(!b[idx]) {
				b[idx] = true;
				cnt++;
			}
		}
		return b;
	}
	
	private String getRandomChar() {
		Random rnd = new Random();
	    int v = rnd.nextInt(52); // or use Random or whatever
	    char base = (v < 26) ? 'A' : 'a';
	    return String.valueOf(((char) (base + v % 26)));
	}

	@Override
	public void getConvertSet() {
		// iterator 로 변환 필요
		ArrayList<TokenStruct> rmlist = new ArrayList<TokenStruct>();
		Iterator<Entry<TokenStruct, String>> iter = filterMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<TokenStruct, String> m = iter.next();
			TokenStruct k = m.getKey();
			String v = m.getValue();
			if(v != null) {
				continue;
			}
			String type = k.getterType();
			if(type.equals(SKIP_TYPE2) || type.equals(SKIP_TYPE3) || type.equals(SKIP_TYPE4) || type.equals(SKIP_TYPE5)) {
				rmlist.add(k);
			}
			else if(type.equals(SKIP_TYPE1) || type.equals(CHAR_TYPE) || type.equals(NUMBER_TYPE)) {
				continue;
			}
			
			// 키 중복 방지
			if(convertMap.containsKey("_".repeat(offset))) {
				offset++;
			}

			
			convertMap.put("_".repeat(offset), null);
			filterMap.put(k, "_".repeat(offset));
		}
		for(TokenStruct o : rmlist) {
			filterMap.remove(o);
		}

	}

}
