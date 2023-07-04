package com.ssafy.home.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.UserDto;

@Mapper
public interface UserMapper {

	//회원정보의 CRUD
	int insert(UserDto signUser) throws SQLException;

	UserDto search(String id) throws SQLException;

	int update(UserDto updateUser) throws SQLException;

	int delete(String deleteId) throws SQLException;

	
	
	//아래는 아직
	boolean login(UserDto user) throws SQLException;

	UserDto findPw(UserDto findUser) throws SQLException;

	List<UserDto> selectAll() throws SQLException;

}
