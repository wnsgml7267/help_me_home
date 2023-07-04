package com.ssafy.home.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : 도서 상세정보에 작성하는 도서평에 대한 글정보")
public class Comment {

	@ApiModelProperty(value = "글번호")
	private int commentNo;
	@ApiModelProperty(value = "작성자이름")
	private String userName;
	@ApiModelProperty(value = "도서평")
	private String comment;
	@ApiModelProperty(value = "작성시각")
	private String commentTime;
	@ApiModelProperty(value = "글번호", example="1")
	private int articleno;

	public Comment(int commentNo, String userName, String comment, String commentTime, int articleno) {
		super();
		this.commentNo = commentNo;
		this.userName = userName;
		this.comment = comment;
		this.commentTime = commentTime;
		this.articleno = articleno;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [commentNo=");
		builder.append(commentNo);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", commentTime=");
		builder.append(commentTime);
		builder.append(", articleno=");
		builder.append(articleno);
		builder.append("]");
		return builder.toString();
	}

}
