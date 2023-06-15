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
	
	// �α��� ������
	@RequestMapping("/login")
	public String login(Model model) {
		log.info("�α��� ������");
		return "login";
	}
	
	// �α��� ����
	@RequestMapping("/login/auth")
	public String login(Model model, String email, String passWord, HttpSession session,
							HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("passWord", passWord);
		Member loginUser = loginService.loginUserAuth(map);
		
		if (loginUser == null) {	
			model.addAttribute("LoginYn", "N"); // login ���и� LoginYn "N"���� �ؼ� ǥ��
			log.info("�α��� ����");	
			return "login";
		}
		
	    // ���ǰ� ����
	    session.setAttribute("user_id", loginUser.getEmail());
	    session.setAttribute("user_name", loginUser.getUserID());
	    
	    // ���� �����ð� ����(�ʴ���)
	    // 60 * 30 = 30��
	    session.setMaxInactiveInterval(30*60);
		
		// ���ǿ� ����� �� ��������
	    String user_id = (String) session.getAttribute("user_id");
	    String user_name = (String) session.getAttribute("user_name");	

		log.info("�α��� ó�� ����");			
		return "redirect:/board/list"; // �α��� ������ �Խ������� �����̷�Ʈ �ǰ� �ش� ���� ��������
	}

}
