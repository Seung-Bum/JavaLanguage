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

import com.items.config.WebMvcConfig;
import com.items.domain.Review;
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
		model.addAttribute("boardList", boardService.list()); // boardList라는 이름으로 List를 template에 넘김		
		log.info("게시물 목록 조회");		
		return "mainboard";
	}
	
	@GetMapping("/findByNo") // 리뷰도 같이 출력중
	public String findByNo(Model model, int no) {			
		model.addAttribute("boardContent", boardService.findByNo(no)); // boardList라는 이름으로 List를 template에 넘김
		
		for(Object data : boardService.findByReview(no)) {

			System.out.println(data);
		}
		
		
		model.addAttribute("reviewList", boardService.findByReview(no)); // reviewList
		log.info("get board content");		
		return "content";
	}
	
	@GetMapping("/search")
	public String boardSearch(Model model, SearchWord form) {		
		model.addAttribute("boardList", boardService.SearchList(form.getSearchWord()));
		log.info("게시물 검색");
	    return "mainboard";
	}
	
	@PostMapping("/reviewUpload")
	public String review(Model model, String reviewText, String userID, String boardTitle, String loginUser) { // textarea name 이름으로 param을 받아야함
		
		System.out.println(reviewText);
		System.out.println(userID);
		System.out.println(boardTitle);
		System.out.println(loginUser);
		
		Map<String, String> map = new HashMap<>();
		map.put("reviewText", reviewText);
		map.put("userID", userID);
		map.put("boardTitle", boardTitle);
		map.put("loginUser", loginUser);
		boardService.insertReview(map);	
		
		model.addAttribute("formData", map);
		//model.addAttribute("boardContent", boardService.findByNo(no)); // findByNo를 오버로드 하려고함
		
		log.info("리뷰 등록");
		return "test";
	}
}
