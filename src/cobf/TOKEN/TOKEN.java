package cobf.TOKEN;

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
