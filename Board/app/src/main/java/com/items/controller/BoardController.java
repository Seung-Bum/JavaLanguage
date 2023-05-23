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
	
	@GetMapping("/findByNo") // ���䵵 ���� ���
	public String findByNo(Model model, int no) {			
		model.addAttribute("boardContent", boardService.findByNo(no)); // boardList��� �̸����� List�� template�� �ѱ�
		model.addAttribute("reviewList", boardService.ReviewfindByNo(no));
		log.info("get board content");		
		return "content";
	}
	
	@GetMapping("/search")
	public String boardSearch(Model model, SearchWord form) {		
		model.addAttribute("boardList", boardService.SearchList(form.getSearchWord()));
		log.info("�Խù� �˻�");
	    return "mainboard";
	}
	
	@PostMapping("/reviewUpload") // textarea name(reviewText) �̸����� param�� �޾ƾ���
	public String review(Model model, String reviewText, String userID, String boardTitle, String loginUser, int boardNo) { 
		
		Map<String, String> map = new HashMap<>();
		map.put("reviewText", reviewText);
		map.put("userID", userID);
		map.put("boardTitle", boardTitle);
		map.put("loginUser", loginUser);
		boardService.insertReview(map);	

		model.addAttribute("boardContent", boardService.findByNo(boardNo));
		
		log.info("���� ���");
		return "redirect:/board/findByNo?no=" + boardNo;
	}
}
