package com.items.domain;

public class Review {
	
	private int no;
	private String reviewText;
	private int boardNo;
	private int memberNo;
	private Member writer;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public Member getWriter() {
		return writer;
	}
	public void setWriter(Member writer) {
		this.writer = writer;
	}
	
	@Override
	public String toString() {
		return "Review [no=" + no + ", reviewText=" + reviewText + ", boardNo=" + boardNo + ", memberNo=" + memberNo
				+ ", writer=" + writer + "]";
	}
	
}
