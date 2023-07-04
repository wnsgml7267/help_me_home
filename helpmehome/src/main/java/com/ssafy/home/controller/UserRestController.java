package com.ssafy.home.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.UserDto;
import com.ssafy.home.model.service.AptService;
import com.ssafy.home.model.service.BoardService;
import com.ssafy.home.model.service.UserService;

@RestController
@RequestMapping("/home")
public class UserRestController {

	private UserService userService;
	private AptService aptService;
	private BoardService boardService;

	public UserRestController(UserService userService, AptService aptService, BoardService boardService) {
		super();
		this.userService = userService;
		this.aptService = aptService;
		this.boardService = boardService;
	}

	//User 관련 기능 : CRUD
	
	//회원가입
	@PostMapping("/user")
	public ResponseEntity<?> postUser(@RequestBody UserDto signUser){
		System.out.println(signUser);
		try {
			int cnt = userService.regist(signUser);

			if(cnt != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	//회원정보 조회
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId){
		try {
			System.out.println(userId);
			UserDto userInfo = userService.getUserInfo(userId);
			System.out.println(userInfo);
			if(userInfo != null) {
				return new ResponseEntity<UserDto>(userInfo, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//회원정보 수정
	@PutMapping("/user/{userId}")
	public ResponseEntity<?> putUser(@RequestBody UserDto updateUser){
		try {
			int cnt = userService.update(updateUser);

			if(cnt != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//회원 탈퇴
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId){
		System.out.println(userId);
		//Fk : 북마크, 글
		try {
			aptService.deleteAllBookmark(userId);
			boardService.deleteUserArticle(userId);
			
			int cnt = userService.delete(userId);

			if(cnt != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//에러처리 코드 분리
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
