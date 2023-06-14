package com.items.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.items.config.WebMvcConfig;
import com.items.domain.SearchWord;
import com.items.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	
	LoginController loginController = new LoginController();
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	WebMvcConfig webMvcConfig;
	
	// 게시판 목록
	@GetMapping("/list")
	public String list(Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {	
		
		loginController.loginValidation(session, request, response);		
		
		model.addAttribute("boardList", boardService.list()); // boardList라는 이름으로 List를 template에 넘김	
		log.info("게시물 목록 조회");
		return "mainboard";
	}
	
	// 게시글 상세 (리뷰도 같이 출력)
	@GetMapping("/findByNo") 
	public String findByNo(Model model, int no) {			
		model.addAttribute("boardContent", boardService.findByNo(no)); // boardList라는 이름으로 List를 template에 넘김
		model.addAttribute("reviewList", boardService.ReviewfindByNo(no));
		log.info("get board content");		
		return "content";
	}
	
	// 게시글 검색
	@GetMapping("/search")
	public String boardSearch(Model model, SearchWord form, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		
		loginController.loginValidation(session, request, response);
		
		model.addAttribute("boardList", boardService.SearchList(form.getSearchWord()));
		log.info("게시물 검색");
	    return "mainboard";
	}
	
	// 리뷰 등록
	@PostMapping("/reviewUpload") // textarea name(reviewText) 이름으로 param을 받아야함
	public String review(Model model, String reviewText, String userID, String boardTitle, String loginUser, int boardNo, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) { 
		
		String user_id = (String) session.getAttribute("user_id");
		log.info("현재 리뷰 등록 이용자 : " + user_id);
		
		String[] tampID = user_id.split("@"); // 회원가입시 아이디에 특수문자 오지 못하게 해야함
		
		Map<String, String> map = new HashMap<>();
		map.put("reviewText", reviewText);
		map.put("userID", userID);
		map.put("boardTitle", boardTitle);
		map.put("loginUser", tampID[0]);
		boardService.insertReview(map);	

		model.addAttribute("boardContent", boardService.findByNo(boardNo));		
		log.info("리뷰 등록");
		return "redirect:/board/findByNo?no=" + boardNo;
	}
}
