package com.items.domain;

public class Review {
	private String reviewText;
	
	public Review(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	@Override
	public String toString() {
		return "Review [reviewText=" + reviewText + "]";
	}
}
