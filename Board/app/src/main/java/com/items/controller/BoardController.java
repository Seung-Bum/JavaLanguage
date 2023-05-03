package com.items.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.items.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(Model model) {		
		log.info("게시물 목록 조회");
		//MailUtil mailUtil = new MailUtil();
		//mailUtil.getTemplateToHtml("mainboard", boardService.list());
		//boardService.list();
		model.addAttribute("boardList", boardService.list());
		return "mainboard";
	}
}
