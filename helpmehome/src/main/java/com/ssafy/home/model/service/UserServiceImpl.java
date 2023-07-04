package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.UserDto;
import com.ssafy.home.model.mapper.UserMapper;

@Service
public class UserServiceImpl  implements UserService{

	UserMapper dao;
	
	@Autowired
	public UserServiceImpl(UserMapper dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean login(UserDto user) throws SQLException {
		return dao.login(user);
	}

	@Override
	public int regist(UserDto signUser) throws SQLException {
		return dao.insert(signUser);
	}

	@Override
	public UserDto getUserInfo(String id) throws SQLException {
		return dao.search(id);
	}

	@Override
	public int update(UserDto updateUser) throws SQLException {
		return dao.update(updateUser);
	}

	@Override
	public int delete(String deleteId) throws SQLException {
		return dao.delete(deleteId);
	}

	@Override
	public UserDto findPw(UserDto findUser) throws SQLException {
		return dao.findPw(findUser);
	}

	@Override
	public List<UserDto> selectAll() throws SQLException {
		return dao.selectAll();
	}


}
