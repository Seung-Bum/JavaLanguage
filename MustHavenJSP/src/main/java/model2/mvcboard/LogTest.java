package model2.mvcboard;

import org.apache.log4j.Logger;

public class LogTest {

	//메소드의 parameter로 package명.class명을 적용해서 해당 class에서 로그 기록
	static Logger logger = Logger.getLogger("model2.mvcboard.LogTest");
	
	// id를 검증하는 로직의 메소드라 가정 - id가 master가 아니라면 warn 메세지 
	//id가 master라면 관리자에게 "master로그인 했다"라는 메세지
	public static void loginCheck(String id) {
		if(id.equals("master")){  
			logger.trace("trace - master 로그인");	
			logger.debug("debug - master 로그인");	
			logger.info("info - master 로그인");	
			logger.warn("warn - master 로그인");	
			logger.error("error - master 로그인");		
		}else {
			logger.warn("warn - user가 잘못 입력");
		}
	}

	public static void main(String[] args) {
		loginCheck("master");
	}
}