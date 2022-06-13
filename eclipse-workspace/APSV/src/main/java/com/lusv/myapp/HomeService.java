package com.lusv.myapp;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("HomeService")
public class HomeService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	
	@Autowired
	HomeDAO homeDAO;
	
	@Autowired
	HomeController homeController;
	
	public void android() {
		logger.info("android2 service");
		homeDAO.register();
		homeDAO.register2();
	}
	
//	회원가입
	public int signUp(Map<String, String> reqeustMap) {
		return homeDAO.signUp(reqeustMap);
	}
	
// 아이디 중복확인
	public boolean checkEmail(Map<String, String> requestMap) {
		return homeDAO.checkEmail(requestMap);
	}
	
//	아이디 로그인
	public ComusermVO checkLogin(Map<String, String> requestMap) {
		return homeDAO.checkLogin(requestMap);
	}
//	신청 하기
	public int apply(Map<String, String> requestMap) {
		return homeDAO.apply(requestMap);
	}
//  전체 직거래
	public List<ApplyVO> applyList(){
		return homeDAO.applyList();
	}
//  게시판 목록
	public List<BoardVO> boardList(){
		return homeDAO.boardList();
	}
//  게시판 목록
	public boolean boardWrite(Map<String, String> requestMap){
		return homeDAO.boardWrite(requestMap);
	}
//  게시판 목록
	public boolean boardEdit(Map<String, Object> requestMap){
		return homeDAO.boardEdit(requestMap);
	}
//  게시판 삭제
	public boolean boardDelete(long id){
		return homeDAO.boardDelete(id);
	}
//  게시판 선택시
	public BoardVO boardDetail(long id){
		return homeDAO.boardDetail(id);
	}
//  게시판 목록
	public boolean accusation(Map<String, String> requestMap){
		return homeDAO.accusation(requestMap);
	}
//	내 직거래 신청 목록
	public List<ApplyVO> myApplyList(String userId){
		return homeDAO.myApplyList(userId);
	}
//	내가 배달중 직거래 
	public List<ApplyVO> myDelivery(String userId){
		return homeDAO.myDelivery(userId);
	}
//	신청 리스트 클릭
	public ApplyVO applyView(int seq){
		return homeDAO.applyView(seq);
	}
//	checkbox 클릭
	public List<ComusermVO> checkBox(String userId){
		return homeDAO.checkBox(userId);
	}
//	배달삭제
	public int delteDelivery(int seq){
		return homeDAO.delteDelivery(seq);
	}
//	배달하기
	public int deliver(Map<String, Object> parameterMap){
		return homeDAO.deliver(parameterMap);
	}
//	배달완료
	public int deliverySuc(Map<String, Object> parameterMap){
		return homeDAO.deliver(parameterMap);
	}
//	정보수정
	public int userInfo(Map<String, String> parameterMap){
		return homeDAO.userInfo(parameterMap);
	}
//	관리자 페이지 오픈
	public List<AccusationVO> singoList(){
		return homeDAO.singoList();
	}
}