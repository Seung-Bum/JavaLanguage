package com.items.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.items.domain.Member;
import com.items.service.LoginService;

@Controller
public class LoginController {
	
	private static final Logger log = LogManager.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	// 로그인 페이지
	@RequestMapping("/login")
	public String login(Model model) {
		log.info("로그인 페이지");
		return "login";
	}
	
	// 로그인 진행
	@RequestMapping("/login/auth")
	public String login(Model model, String email, String passWord, HttpSession session,
							HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("passWord", passWord);
		Member loginUser = loginService.loginUserAuth(map);
		
		if (loginUser == null) {	
			model.addAttribute("LoginYn", "N"); // login 실패를 LoginYn "N"으로 해서 표시
			log.info("로그인 실패");	
			return "login";
		}
		
	    // 세션값 설정
	    session.setAttribute("user_id", loginUser.getEmail());
	    session.setAttribute("user_name", loginUser.getUserID());
	    
	    // 세션 유지시간 설정(초단위)
	    // 60 * 30 = 30분
	    session.setMaxInactiveInterval(30*60);
		
		// 세션에 저장된 값 가져오기
	    String user_id = (String) session.getAttribute("user_id");
	    String user_name = (String) session.getAttribute("user_name");	

		log.info("로그인 처리 수행");			
		return "redirect:/board/list"; // 로그인 성공시 게시판으로 리다이렉트 되고 해당 유저 세션유지
	}

}
