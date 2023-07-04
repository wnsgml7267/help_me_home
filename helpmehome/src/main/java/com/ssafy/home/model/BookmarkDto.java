package com.ssafy.home.model;

public class BookmarkDto {

	private String userId;
	private int bookmarkIdx;
	private String aptCode;
	public BookmarkDto(String userId, int bookmarkIdx, String aptCode) {
		super();
		this.userId = userId;
		this.bookmarkIdx = bookmarkIdx;
		this.aptCode = aptCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBookmarkIdx() {
		return bookmarkIdx;
	}
	public void setBookmarkIdx(int bookmarkIdx) {
		this.bookmarkIdx = bookmarkIdx;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	@Override
	public String toString() {
		return "BookmarkDto [userId=" + userId + ", bookmarkIdx=" + bookmarkIdx + ", aptCode=" + aptCode + "]";
	}
	
	
}
