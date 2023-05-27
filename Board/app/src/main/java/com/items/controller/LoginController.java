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
import com.items.service.LoginService;

@Controller
public class LoginController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/login")
	public String login(Model model) {			
		log.info("로그인 페이지");
		return "login";
	}
	
	@RequestMapping("/login/auth")
	public String login(Model model, String email, String passWord) {
		
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("passWord", passWord);
		loginService.loginDataAuth(map);
		
		log.info("로그인 처리 수행");
		return "login";
	}
	
	
	/* 세션설정 소스
	@RequestMapping(value = "/session.do", method = RequestMethod.POST)
	public void sessionRequest(Model model, HttpSession session,
	HttpServletRequest request, HttpServletResponse response) {

    String user_id = "test123" ;
    String user_name = "튜나" ;

    // 세션값 설정
    session.setAttribute("user_id", user_id);
    session.setAttribute("user_name", user_name);

    // 세션 유지시간 설정(초단위)
    // 60 * 30 = 30분
    session.setMaxInactiveInterval(30*60);

    // 세션 시간을 무한대로 설정
    session.setMaxInactiveInterval(-1);

    // 세션에 저장된 값 가져오기
    session.getAttribute("user_id");
    session.getAttribute("user_name");

    // 세션값 삭제
    session.removeAttribute("user_id");

    // 세션 전체 제거, 무효화
    session.invalidate();
	*/
}
}
