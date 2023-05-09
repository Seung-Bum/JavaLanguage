package com.items.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.items.config.WebMvcConfig;
import com.items.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	WebMvcConfig webMvcConfig;
	
	@GetMapping("/list")
	public String list(Model model) {
			
		model.addAttribute("boardList", boardService.list()); // boardList��� �̸����� List�� template�� �ѱ�		
		log.info("�Խù� ��� ��ȸ");
		
		return "mainboard";
	}
	
	@GetMapping("/findByNo")
	public String findByNo(Model model, int no) {
			
		model.addAttribute("boardContent", boardService.findByNo(no)); // boardList��� �̸����� List�� template�� �ѱ�
		log.info("get board content");
		
		return "content";
	}
	
	@PostMapping("/board/search")
	public String boardSearch(Model model, Map<String, Object> form) {
		model.addAttribute("search", form);
		System.out.println(form.values().toString());
	    return "test";
	}
}
