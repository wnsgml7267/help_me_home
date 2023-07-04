package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.BookmarkDto;
import com.ssafy.home.model.DongCodeDto;
import com.ssafy.home.model.HomeInfoDto;
import com.ssafy.home.model.HomeResultDto;
import com.ssafy.home.model.InterestAreaDto;
import com.ssafy.home.model.SidoDealInfo;
import com.ssafy.home.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService{

	private AptMapper mapper;
	
	@Autowired
	public AptServiceImpl(AptMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public List<DongCodeDto> selectDongCode() throws SQLException {
		//table dongcode
		return mapper.selectDongCode();
	}

	@Override
	public List<HomeResultDto> getHomeList(HomeInfoDto homeInfoDto) throws SQLException {
		List<HomeResultDto> list = mapper.getHomeList(homeInfoDto);
//		list = sort(list);
		return list;
	}

//	public List<HomeResultDto> sort(List<HomeResultDto> targetList) {
//		// 사이즈가 1보다 크다면
//		if (targetList.size() > 1) {
//			// 왼쪽 오른쪽을 merge 합니다.
//			return merge(
//					// 왼쪽 / 오른쪽으로 배열을 나누고 다시 sort하도록 합니다.
//					sort(targetList.subList(0, targetList.size() / 2)),
//					sort(targetList.subList(targetList.size() / 2, targetList.size())));
//		} else {
//			// 사이즈가 1 이하라면 재귀가 종료됩니다.
//			return targetList;
//		}
//	}
//
//	public List<HomeResultDto> merge(List<HomeResultDto> left, List<HomeResultDto> right) {
//		// 결과가 될 임시 배열입니다.
//		List<HomeResultDto> result = new ArrayList<>();
//
//		int l = 0, r = 0;
//
//		while (l < left.size() && r < right.size()) { // 둘다 원소가 있는 경우
//			String a = left.get(l).getDealAmount().replaceAll(",", "");
//			String b = right.get(r).getDealAmount().replaceAll(",", "");
//
//			if (Integer.parseInt(a) < Integer.parseInt(b)) {
//				result.add(left.get(l++));
//			} else {
//				result.add(right.get(r++));
//			}
//		}
//
//		while (l < left.size()) { // 왼쪽만 남아있는 경우
//			result.add(left.get(l++));
//		}
//
//		while (r < right.size()) { // 오른쪽만 남아있는 경우
//			result.add(right.get(r++));
//		}
//
//		return result;
//	}

//	@Override
//	public List<HomeResultDto> getSearchList(List<HomeResultDto> original, String keyword) throws SQLException {
//		//여기서 KMP 수행
//		List<HomeResultDto> result = new ArrayList<>();
//
//		//원래 배열에 있던 각 아파트 거래정보마다 KMP 알고리즘을 수행하여 keyword가 포함된 아파트만 result에 dto 담기
//		for (HomeResultDto dto : original) {
//			//KMP 알고리즘 수행
//			String aptName = dto.getApartmentName();
//
//			char[] T = aptName.toCharArray();  // 본문
//			char[] P = keyword.toCharArray();  // 패턴
//
//			int tLength = T.length;
//			int pLength = P.length;
//
//			// 1단계: 부분일치 테이블 만들기
//			int[] pi = new int[pLength];
//
//			// i: 접미사 포인터
//			// j: 접두사 포인터
//			for (int i = 1, j = 0; i < pLength; i++) {
//				// 일치하지 않는 경우
//				while (j > 0 && P[i] != P[j]) {
//					// j - 1 위치까지는 일치 했으므로 일치했던 곳의 인덱스 번호를 가져옴
//					j = pi[j - 1];
//				}
//				// 같은 경우
//				if (P[i] == P[j]) {
//					pi[i] = ++j;
//				}
//				else {
//					pi[i] = 0;
//				}
//			}
//			// 2단계 : KMP 알고리즘 수행
//			// i: 본문 포인터
//			// j: 패턴 포인터
//			for (int i = 0, j = 0; i < tLength; i++) {
//				// 본문과 패턴이 불일치하면
//				while (j > 0 && T[i] != P[j]) {
//					// j - 1일 때까지는 일치 했으므로 인덱스 j를 이전 일치했던 상태로 돌림
//					j = pi[j - 1];
//				}
//				// 일치하면
//				if (T[i] == P[j]) {
//
//					// j가 패턴의 마지막 인덱스라면 패턴과 일치하는 단어를 찾은 경우
//					if (j == pLength - 1) {
//						//패턴과 일치하는 단어를 찾으면 dto 추가하고 바로 conitnue;
//						result.add(dto);
//						continue;
//					}
//					else {
//						j++;
//					}
//				}
//			}
//		}
//
//		return result; //생성한 result 배열을 리턴
//	}

//	@Override
//	public int remove(HomeResultDto removeApt) throws SQLException {
//		return mapper.removeApt(removeApt);
//	}

//	@Override
//	public List<HomeResultDto> selectAll() throws SQLException {
//		return mapper.selectAll();
//	}

//	@Override
//	public List<HomeResultDto> getInterList(String selectedDongCode, int year, int month) throws SQLException {
//		return mapper.getInterList(selectedDongCode, year, month);
//	}

//	@Override
//	public int insertInterest(InterestAreaDto newArea) throws SQLException {
//		return mapper.registInterest(newArea);
//	}
//
//	@Override
//	public List<InterestAreaDto> getInterestLs(String userId) throws SQLException {
//		return mapper.getInterestList(userId);
//	}

	@Override
	public List<SidoDealInfo> selectSidoDealInfo() throws SQLException {
		return mapper.getSidoDealInfo();
	}

	@Override
	public List<HomeResultDto> getKeywordHomeList(String keyword) throws SQLException {
		return mapper.getKeywordHomeList(keyword);
	}

	@Override
	public List<HomeResultDto> getDealInfo(String aptCode) throws SQLException {
		return mapper.getDealInfo(aptCode);
	}

	@Override
	public int insertBookmark(BookmarkDto bookmark) throws SQLException {
		return mapper.insertBookmark(bookmark);
	}

	@Override
	public List<HomeResultDto> getBookmarkApt(String userId) throws SQLException {
		return mapper.getBookmarkApt(userId);
	}

	@Override
	public int deleteBookmark(String userId, String aptCode) throws SQLException {
		return mapper.deleteBookmark(userId, aptCode);
	}

	@Override
	public int deleteAllBookmark(String userId) throws SQLException {
		return mapper.deleteAllBookmark(userId);
	}

//	@Override
//	public String getDongCode(String curSido, String curGugun, String curDong) throws SQLException {
//		return dao.getDongCode(curSido, curGugun, curDong);
//	}

}
