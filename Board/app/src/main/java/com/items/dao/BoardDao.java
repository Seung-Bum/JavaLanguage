package com.items.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.items.domain.Board;


// => Mybatis���� DAO ����ü�� �ڵ����� �����Ѵ�.
// => DAO ����ü�� ����� SQL Mapper ������ ��ġ�� �������̽��� ��Ű�� ��� �� �̸��� ��ġ�ؾ� �Ѵ�.
//    ��) com/eomcs/mylist/dao/BoardDao.xml 
// => �������̽��� �޼��尡 ȣ��� �� ����� SQL ID�� �޼��� �̸��� ��ġ�ؾ� �Ѵ�.
//    ��) <select id="countAll">...</select>
//
@Mapper
public interface BoardDao {

  //int countAll();

  List<Board> findAll();

  //int insert(Board board);

  //Board findByNo(int no);

  //int update(Board board);

  //int delete(Board board);

  //int increaseViewCount(int no);
}