package com.items.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	
	// ���� ������
	@RequestMapping("/mail")
	public String mailPage(Model model) {			
		log.info("���� ������");
		return "mail";
	}
}
