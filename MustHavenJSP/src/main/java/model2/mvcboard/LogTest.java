package model2.mvcboard;

import org.apache.log4j.Logger;

public class LogTest {

	//�޼ҵ��� parameter�� package��.class���� �����ؼ� �ش� class���� �α� ���
	static Logger logger = Logger.getLogger("model2.mvcboard.LogTest");
	
	// id�� �����ϴ� ������ �޼ҵ�� ���� - id�� master�� �ƴ϶�� warn �޼��� 
	//id�� master��� �����ڿ��� "master�α��� �ߴ�"��� �޼���
	public static void loginCheck(String id) {
		if(id.equals("master")){  
			logger.trace("trace - master �α���");	
			logger.debug("debug - master �α���");	
			logger.info("info - master �α���");	
			logger.warn("warn - master �α���");	
			logger.error("error - master �α���");		
		}else {
			logger.warn("warn - user�� �߸� �Է�");
		}
	}

	public static void main(String[] args) {
		loginCheck("master");
	}
}