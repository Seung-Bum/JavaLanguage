package com.items.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
	//private static final Logger log = LogManager.getLogger(BoardController.class);
	
	//@Autowired
	//BoardService boardService;
	
	@RequestMapping("/board/list")
	public Object list() {
	  //log.info("게실물 목록 조회");
	  
	  return 
	}
}
