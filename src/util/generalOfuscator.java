package util;

import java.nio.file.Path;

public interface generalOfuscator {

	
	/**
	 * Ofuscate 할 메인 run
	 * @param path uploaded file path
	 * @return complete 시 true 반환
	 */
	boolean run(String path);
	
	
	
	/**
	 * text Scanning
	 * 
	 * @param path uploaded file path
	 * @return scanning 및 토큰화 성공시 true
	 */
	boolean scanningUsingJStokenizer(String path);

	
	/**
	 * string 구문일 경우 문자열 read
	 * 
	 * @return \"일 때 까지 read 
	 */
	String readString();
	
	/**
	 * convert set을 변환(zipping)
	 * 
	 * @param from 변환 대상 파일
	 * @param to 결과 파일
	 */
	void valueZipping(Path from, Path to);
	
	/**
	 * 변환기 
	 */
	void getConvertSet();
	
}
