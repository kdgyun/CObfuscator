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

import java.nio.file.Path;

/**
 * 
 * @author kdgyun
 * @since 1.0.0
 * @version 1.0.5
 * 
 */

public interface generalObfuscator {

	
	/**
	 * obfuscate run 메소드
	 * 
	 * @param path input File Path
	 * @param resultPath output File Path
	 * @param f true 일 경우 console print, false 일 경우 outputFilePath 로 주어지는 Path로 write
	 * @return 모두 완료되었을 경우 해당 난독화 코드 출력
	 */
	String run(String path, String resultPath, boolean f);
	
	
	
	/**
	 * text Scanning 및 tokenize (nodeJS가 사용 됨)
	 * 
	 * @param path uploaded file path
	 * @return scanning 및 토큰화 성공시 true
	 */
	boolean scanningUsingJStokenizer(String path);

	
	/**
	 * 
	 * @param from uploaded file path (obfuscate 할 file path) 
	 * @param to obfuscator에 의해 난독화 되어 write 될 file Path
	 * @param f true 일 경우 console print, false 일 경우 outputFilePath 로 주어지는 Path로 write
	 */
	String valueZipping(Path from, Path to, boolean f);
	
	/**
	 * 변환기 
	 */
	void getConvertSet();
	
}
