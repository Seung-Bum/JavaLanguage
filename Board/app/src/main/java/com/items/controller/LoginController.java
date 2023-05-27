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
		log.info("�α��� ������");
		return "login";
	}
	
	@RequestMapping("/login/auth")
	public String login(Model model, String email, String passWord) {
		
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("passWord", passWord);
		loginService.loginDataAuth(map);
		
		log.info("�α��� ó�� ����");
		return "login";
	}
	
	
	/* ���Ǽ��� �ҽ�
	@RequestMapping(value = "/session.do", method = RequestMethod.POST)
	public void sessionRequest(Model model, HttpSession session,
	HttpServletRequest request, HttpServletResponse response) {

    String user_id = "test123" ;
    String user_name = "Ʃ��" ;

    // ���ǰ� ����
    session.setAttribute("user_id", user_id);
    session.setAttribute("user_name", user_name);

    // ���� �����ð� ����(�ʴ���)
    // 60 * 30 = 30��
    session.setMaxInactiveInterval(30*60);

    // ���� �ð��� ���Ѵ�� ����
    session.setMaxInactiveInterval(-1);

    // ���ǿ� ����� �� ��������
    session.getAttribute("user_id");
    session.getAttribute("user_name");

    // ���ǰ� ����
    session.removeAttribute("user_id");

    // ���� ��ü ����, ��ȿȭ
    session.invalidate();
	*/
}
}
