package cobf.util.support;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author kdgyun
 * @since 1.0.0
 * @version 1.0.0
 * 
 */

public class Supporter {

	/**
	 * 무작위로 String 배열을 섞는 메소드
	 * @param arr 섞을 String[] 배열
	 */
	final public static void shuffleArray(String[] arr) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = arr.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			String a = arr[index];
			arr[index] = arr[i];
			arr[i] = a;
		}
	}
	
	/**
	 * 무작위 인덱스를 설정하여 반환하는 메소드
	 * 
	 * @param length 무작위 인덱스를 설정 할 개수
	 * @param maxHold 반환 할 배열의 한계 길이
	 * @return 무작위 인덱싱 된 boolean 배열 반환
	 */
	final public static boolean[] getSecureIdx(int length, int maxHold) {
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
	
	/**
	 * 무작위 단일 문자를 얻는 메소드
	 * @return 무작위 영문자 반환
	 */
	final public static String getRandomChar() {
		Random rnd = new Random();
	    int v = rnd.nextInt(52); // or use Random or whatever
	    char base = (v < 26) ? 'A' : 'a';
	    return String.valueOf(((char) (base + v % 26)));
	}
}
