package com.ssafy.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.BookmarkDto;
import com.ssafy.home.model.DongCodeDto;
import com.ssafy.home.model.HomeInfoDto;
import com.ssafy.home.model.HomeResultDto;
import com.ssafy.home.model.SidoDealInfo;
import com.ssafy.home.model.service.AptService;

@RestController
@RequestMapping("/home")
public class AptRestController {

	private AptService aptService;

	@Autowired
	public AptRestController(AptService aptService) {
		super();
		this.aptService = aptService;
	}
	
	//main page : 연도별, 시도별, 아파트 거래정보
	@GetMapping(value = "/apt/sidoDealInfo")
	public ResponseEntity<?> getSidoDealInfo(){
		try {
			List<SidoDealInfo> sidoDealList = aptService.selectSidoDealInfo();
			
			if(sidoDealList != null && !sidoDealList.isEmpty()) {
				return new ResponseEntity<List<SidoDealInfo>>(sidoDealList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	//board 관련 기능 : R, D
	
	//동코드 전체 조회 : 초기 화면에 출력할 동코드 조회 후 리스트 반환하면 js에서 출력 : 시도 이름 출력
	@GetMapping(value = "/apt/dongcode")
	public ResponseEntity<?> getDongCodeList(){
		try {
			List<DongCodeDto> dongCodeLs = aptService.selectDongCode();
			
			if(dongCodeLs != null && !dongCodeLs.isEmpty()) {
				return new ResponseEntity<List<DongCodeDto>>(dongCodeLs, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//집 정보 검색 : 사용자가 선택한 값으로 집 정보 검색 후 리스트 반환
	@GetMapping(value = "/apt/{sido}/{gugun}/{dong}")
	public ResponseEntity<?> searchHome(@PathVariable String sido, @PathVariable String gugun, @PathVariable String dong){
		try {
			HomeInfoDto searchHomeInfo = new HomeInfoDto(sido, gugun, dong); //파라미터값 넣기
			List<HomeResultDto> homeList = aptService.getHomeList(searchHomeInfo);
			
			if(homeList != null && !homeList.isEmpty()) {
				return new ResponseEntity<List<HomeResultDto>>(homeList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//아파트 매매정보 검색 : 입력받은 아파트 코드에 해당하는 아파트의 매매정보 반환(연도별, 면적별 최고 매매가, 최저 매매가. 최근 5년)
	@GetMapping(value = "/apt/dealinfo/{aptCode}")
	public ResponseEntity<?> searchDealInfo(@PathVariable String aptCode){
		try {
			List<HomeResultDto> homeList = aptService.getDealInfo(aptCode);
			
			if(homeList != null && !homeList.isEmpty()) {
				return new ResponseEntity<List<HomeResultDto>>(homeList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//집 키워드 검색 : 기존 리스트에서 키워드 검색 : body에 일단 담아서 보냄.. => get은 body 못씀!!
//	@GetMapping("/apt/{sido}/{gugun}/{dong}/{year}")
//	public ResponseEntity<?> searchKeywordHome(@PathVariable String sido, @PathVariable String gugun, @PathVariable String dong, 
//												@PathVariable int year, @PathVariable int month, @PathVariable String keyword){
//		try {
//			//우선 기존 리스트 검색
//			HomeInfoDto searchHomeInfo = new HomeInfoDto(sido, gugun, dong); //파라미터값 넣기
//			List<HomeResultDto> homeList = aptService.getHomeList(searchHomeInfo);
//			//검색한 리스트 바탕으로 search
//			List<HomeResultDto> newRes = aptService.getSearchList(homeList, keyword);
//			
//			if(newRes != null && !newRes.isEmpty()) {
//				return new ResponseEntity<List<HomeResultDto>>(newRes, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	
	//집 키워드 검색 : 전체 지도에서 키워드 검색
	@GetMapping("/apt/{keyword}")
	public ResponseEntity<?> searchKeywordHome(@PathVariable String keyword){
		System.out.println("controller " + keyword);
		try {
			List<HomeResultDto> homeList = aptService.getKeywordHomeList(keyword);
			if(homeList != null && !homeList.isEmpty()) {
//				System.out.println(homeList);
				return new ResponseEntity<List<HomeResultDto>>(homeList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	//관리자 기능) 집 거래 정보 삭제 : 파라미터 개선 필요
//	@DeleteMapping(value = "/apt/{apartmentName}/{floor}/{area}/{dongName}/{dealAmount}/{roadName}")
//	public ResponseEntity<?> deleteDealInfo(@PathVariable String apartmentName, @PathVariable String floor, @PathVariable String area, 
//											@PathVariable String dongName, @PathVariable String dealAmount, @PathVariable String roadName){
//		try {
//			HomeResultDto removeHomeInfo = new HomeResultDto(apartmentName, floor, area, dongName, dealAmount, roadName);
//			int res = aptService.remove(removeHomeInfo);
//			
//			if(res == 1) {
//				return new ResponseEntity<Void>(HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
	//////interest : C, R
	
	//북마크 기능
	//북마크 정보 등록 : 사용자가 보낸 aptCode를 등록
	@PostMapping("/apt/bookmark")
	public ResponseEntity<?> postBookmark(@RequestBody BookmarkDto bookmark){//id, dongcode, area
		try {
			int cnt = aptService.insertBookmark(bookmark);
			
			if(cnt == 1) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	//북마크 정보에 맞는 아파트 리스트 불러오기
	@GetMapping("/apt/bookmark/{userId}")
	public ResponseEntity<?> getBookmarks(@PathVariable String userId){
		try {
			List<HomeResultDto> aptList = aptService.getBookmarkApt(userId);
			
			if(aptList != null && !aptList.isEmpty()) {
				return new ResponseEntity<List<HomeResultDto>>(aptList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	//북마크 삭제하기 : 사용자 아이디, 아파트
	@DeleteMapping("/apt/bookmark/{userId}/{aptCode}")
	public ResponseEntity<?> deleteBookmarks(@PathVariable String userId, @PathVariable String aptCode){
		try {
			int cnt = aptService.deleteBookmark(userId, aptCode);
			
			if(cnt == 1) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//북마크 전부 삭제하기(해당 아이디)
	@DeleteMapping("/apt/bookmark/{userId}")
	public ResponseEntity<?> deleteAllBookmarks(@PathVariable String userId){
		try {
			int cnt = aptService.deleteAllBookmark(userId);
			
			if(cnt == 1) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
//	
//	//관심 지역 정보 등록 : 사용자가 보낸 지역 정보(보낼때 동코드로 보냄)를 관심 지역으로 등록 post
//	@PostMapping("/apt/interest")
//	public ResponseEntity<?> postInterest(@RequestBody InterestAreaDto areaDto){//id, dongcode, area
//		try {
//			int cnt = aptService.insertInterest(areaDto);
//			
//			if(cnt == 1) {
//				return new ResponseEntity<Void>(HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
//	//관심 지역 정보 불러오기 : 로그인 시 불러오기
//	@GetMapping("/apt/interest/{userId}")
//	public ResponseEntity<?> getInterestList(@PathVariable String userId){
//		try {
//			List<InterestAreaDto> savedInterestLs = aptService.getInterestLs(userId);
//			
//			if(savedInterestLs != null && !savedInterestLs.isEmpty()) {
//				return new ResponseEntity<List<InterestAreaDto>>(savedInterestLs, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	
	//에러처리 코드 분리
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
