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
package cobf.TOKEN;

/**
 * 
 * @author kdgyun
 * @since 1.0.0
 * @version 1.0.0
 * 
 */

public enum TOKEN {

	INCLUDE("include"), 
	DEFINE("define"), 
	UNDEF("undef"),
	IF("if"),
	IFDEF("ifdef"),
	ELSE("else"),
	ELIF("elif"),
	IFNDEF("ifndef"),
	ERROR("error"),
	ENDIF("endif"),
	PRAGMA("pragma"),
	LINE("line");
	
	private final String TOKEN_NAME;
	
	TOKEN(String TOKEN_NAME) {
		this.TOKEN_NAME = TOKEN_NAME;
	}
	
	public String getToken() {
		return TOKEN_NAME;
	}
}
