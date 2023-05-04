package com.items.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


// => Mybatis���� DAO ����ü�� �ڵ����� �����Ѵ�.
// => DAO ����ü�� ����� SQL Mapper ������ ��ġ�� �������̽��� ��Ű�� ��� �� �̸��� ��ġ�ؾ� �Ѵ�.
//    ��) com/eomcs/mylist/dao/BoardDao.xml 
// => �������̽��� �޼��尡 ȣ��� �� ����� SQL ID�� �޼��� �̸��� ��ġ�ؾ� �Ѵ�.
//    ��) <select id="countAll">...</select>
//
// @Repository�� ����Ϸ��� interface ���� �׳� Ŭ������ �ؾ��ϴµ���
//
@Mapper
public interface BoardDao {

  //int countAll();

  List<Map<String, Object>> findAll();

  //int insert(Board board);

  //Board findByNo(int no);

  //int update(Board board);

  //int delete(Board board);

  //int increaseViewCount(int no);
}