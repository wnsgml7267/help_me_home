package com.ssafy.home.model.service;

import java.util.List;

import com.ssafy.home.model.Comment;

public interface CommentService {

	List<Comment> list(int articleno);

	boolean create(Comment commentDto);

	boolean modify(Comment commentDto);

	boolean delete(int commentNo);

}
