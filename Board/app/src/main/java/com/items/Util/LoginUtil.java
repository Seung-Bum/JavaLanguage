package com.items.Util;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.items.controller.LoginController;
import com.items.controller.MailController;

@Component
public class LoginUtil {
	
	private static final Logger log = LogManager.getLogger(LoginUtil.class);
	
	// 로그인 검증
	public String loginValidation (HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {	
		
		String user_id = (String) session.getAttribute("user_id");	    
		String user_name = (String) session.getAttribute("user_name");
		String result = "";
	    
		if (user_id != null) {
			log.info(user_id + " : 서버 이용중");
			result = user_id;
		} else {
			log.info("비정상 접속, 로그인페이지로 이동");
			result = "login";
		}
		
		return result;
	}
}
