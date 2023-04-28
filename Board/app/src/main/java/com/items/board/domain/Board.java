package com.items.board.domain;

public class Board {
	private int no;
	private String title;
	private String content;
	private int viewCount;
	private java.sql.Date createdDate;
	//Member writer;
	
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
	public java.sql.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.sql.Date createdDate) {
		this.createdDate = createdDate;
	}
}
