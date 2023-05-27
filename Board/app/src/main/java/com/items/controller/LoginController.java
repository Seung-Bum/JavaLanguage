package com.items.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.items.config.WebMvcConfig;
import com.items.domain.SearchWord;
import com.items.service.BoardService;

@Controller
public class LoginController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	
	//@Autowired
	//BoardService boardService;
	
	//@Autowired
	//WebMvcConfig webMvcConfig;
	
	@RequestMapping("/login")
	public String login(Model model) {			
		log.info("로그인 페이지");
		return "login";
	}
	
	@RequestMapping("/login/auth")
	public String login(Model model, String email, String passWord) {			
		
		System.out.println(email + passWord);
		
		log.info("로그인 처리 수행");
		return "login";
	}
}
