package com.items.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Board {
	private int no;
	private String title;
	private String content;
	private int viewCount;
	private java.sql.Timestamp createdDate;
	private Member writer;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.sql.Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Member getWriter() {
		return writer;
	}
	public void setWriter(Member writer) {
		this.writer = writer;
	}
}
