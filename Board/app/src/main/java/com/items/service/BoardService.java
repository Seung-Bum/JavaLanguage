package com.items.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.items.domain.Board;

public interface BoardService {
	
	//int add(Board board); // board 추가 
	
	List<Map<String,Object>> list(); // Board를 담는 List
	
	Object findByNo(int no); // index에 해당하는 Board를 가져옴	
	
	//int update(Board board); // board를 받아서 board를 업데이트한다.
	
	//int delete(Board board); // board 삭제
}
