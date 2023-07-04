package com.ssafy.home.model.mapper;

import java.util.List;

import com.ssafy.home.model.Comment;

public interface CommentMapper {

	List<Comment> list(int articleno);

	int create(Comment commentDto);

	int modify(Comment commentDto);

	int delete(int commentNo);
	
}
