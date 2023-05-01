package com.items.board.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.items.board.service.BoardService;

@RestController
public class BoardController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/board/list")
	public Object list() {
	  log.info("게시물 목록 조회");
	  return boardService.list();
	}
}
