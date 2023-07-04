package com.ssafy.home.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.BookmarkDto;
import com.ssafy.home.model.DongCodeDto;
import com.ssafy.home.model.HomeInfoDto;
import com.ssafy.home.model.HomeResultDto;
import com.ssafy.home.model.InterestAreaDto;
import com.ssafy.home.model.SidoDealInfo;

@Mapper
public interface AptMapper {
	//집 정보
	List<DongCodeDto> selectDongCode() throws SQLException;

	List<HomeResultDto> getHomeList(HomeInfoDto homeInfoDto) throws SQLException;

//	int removeApt(HomeResultDto removeApt) throws SQLException;

	
//	List<HomeResultDto> selectAll() throws SQLException;

	//관심 지역 정보
//	int registInterest(InterestAreaDto newArea) throws SQLException;

//	List<InterestAreaDto> getInterestList(String userId) throws SQLException;

	List<SidoDealInfo> getSidoDealInfo() throws SQLException;

	List<HomeResultDto> getKeywordHomeList(String keyword) throws SQLException;

	List<HomeResultDto> getDealInfo(String aptCode) throws SQLException;

	int insertBookmark(BookmarkDto bookmark) throws SQLException;

	List<HomeResultDto> getBookmarkApt(String userId) throws SQLException;

	int deleteBookmark(String userId, String aptCode) throws SQLException;

	int deleteAllBookmark(String userId) throws SQLException;

//	List<HomeResultDto> getInterList(String selectedDongCode, int year, int month) throws SQLException;
	
}
