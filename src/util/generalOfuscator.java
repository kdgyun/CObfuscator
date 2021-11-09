package util;

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
	boolean scanning(String path);
	

	
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
