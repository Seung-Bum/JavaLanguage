package com.items.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.items.board.dao.BoardDao;
import com.items.board.domain.Board;
import com.items.board.service.BoardService;

@Service
public class DefaultBoardService implements BoardService { // BoardService 안의 모든 메서드 구현해야함
	
	@Autowired(required = false)
	BoardDao boardDao;
	
	@Override
	public List<Board> list() {
		return boardDao.findAll();
	}
}
