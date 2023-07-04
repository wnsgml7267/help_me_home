package com.ssafy.home.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.mapper.CommentMapper;
import com.ssafy.home.model.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentDao;

	@Override
	public List<Comment> list(int articleno) {
		return commentDao.list(articleno);
	}

	@Override
	public boolean create(Comment commentDto) {
		System.out.println(commentDto);
		return commentDao.create(commentDto) == 1;
	}

	@Override
	public boolean modify(Comment commentDto) {
		return commentDao.modify(commentDto) == 1;
	}

	@Override
	public boolean delete(int commentNo) {
		return commentDao.delete(commentNo) == 1;
	}

}
