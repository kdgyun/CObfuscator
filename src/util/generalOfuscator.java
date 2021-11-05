package util;

public interface generalOfuscator {

	/**
	 * 텍스트 scanning
	 */
	void scanning();
	

	
	/**
	 * string 구문일 경우 문자열 read
	 * 
	 * @return \"일 때 까지 read 
	 */
	String readString();
	
	/**
	 * 변수 명을 짧게 압축
	 */
	void valueZipping();
	
	/**
	 * 변환기 
	 */
	void convert();
	
}
