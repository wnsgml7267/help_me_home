package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.model.UserDto;

public interface UserService {

	boolean login(UserDto user) throws SQLException;

	int regist(UserDto signUser) throws SQLException;

	UserDto getUserInfo(String id) throws SQLException;

	int update(UserDto updateUser) throws SQLException;

	int delete(String deleteId) throws SQLException;

	UserDto findPw(UserDto findUser) throws SQLException;

	List<UserDto> selectAll() throws SQLException;


}
