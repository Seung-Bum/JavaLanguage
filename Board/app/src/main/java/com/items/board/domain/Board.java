package com.items.board.domain;

import java.util.Date;

public class Board {
	private int no;
	private String title;
	private String content;
	private int viewCount;
	private Date createdDate;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Member getWriter() {
		return writer;
	}
	public void setWriter(Member writer) {
		this.writer = writer;
	}
}
