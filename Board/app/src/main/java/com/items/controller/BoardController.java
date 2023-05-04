package com.items.controller;

import java.util.List;
import java.util.Map;

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
		
		model.addAttribute("boardList", boardService.list()); // boardList��� �̸����� List template�� �ѱ�		
		log.info("�Խù� ��� ��ȸ");
		
		return "mainboard";
	}
}
