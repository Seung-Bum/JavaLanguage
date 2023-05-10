package com.items.service;

import java.util.List;
import java.util.Map;

import com.items.domain.SearchWord;

public interface BoardService {
	
	//int add(Board board); // board �߰� 
	
	List<Map<String,Object>> list(); // Board�� ��� List
	
	List<Map<String,Object>> SearchList(String searchWord);
	
	Object findByNo(int no); // index�� �ش��ϴ� Board�� ������	
	
	//int update(Board board); // board�� �޾Ƽ� board�� ������Ʈ�Ѵ�.
	
	//int delete(Board board); // board ����
}
