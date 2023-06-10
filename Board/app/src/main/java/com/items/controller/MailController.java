package com.items.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MailController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	private TemplateEngine templateEngine;
	
	LoginController loginController = new LoginController();
	
	
	
	// 메일 페이지
	@RequestMapping("/mail")
	public String mailPage(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {		
		loginController.loginValidation(model, session, request, response);
		log.info("메일 페이지");
		return "mail";
	}
	
	// google Mail API
	@RequestMapping("/sendmail")
	public void sendMail (String Template, String mailData) {
		
	}
	
}
