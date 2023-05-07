package com.items.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.springframework.transaction.annotation.Transactional;
import com.items.dao.BoardDao;
import com.items.service.BoardService;

@Service
public class DefaultBoardService implements BoardService { // BoardService 안의 모든 메서드 구현해야함
	
	@Autowired(required=true)
	BoardDao boardDao;
	
	@Override
	public List<Map<String,Object>> list() {
		List<Map<String, Object>> boardList = boardDao.findAll();
		return boardList;
	}
	
	@Override
	public Object findByNo(int no) {
		boardDao.increaseViewCount(no);
		Object board = boardDao.findByNo(no);	    
		return board;
	}
	
}
