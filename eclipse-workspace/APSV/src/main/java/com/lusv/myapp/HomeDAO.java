package com.lusv.myapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HomeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	
	@Inject
    SqlSession mybatis;
	
	
	public void register() {
		logger.info("mybatis>>>>>>>>>>> "+mybatis);
		String nowDate = mybatis.selectOne("HomeMapper.selectNow");
		logger.info("nowDate "+ nowDate);
	}
	
	public void register2() {
		List<ComusermVO> comusermVO= mybatis.selectList("HomeMapper.register");
		logger.info("comusermVO..... {}", comusermVO.get(0).toString());
	}
	
	public int signUp(Map<String, String> reqeustMap) {
		int result = mybatis.insert("HomeMapper.signup", reqeustMap);
		
		return result;
	}
	
	public boolean checkEmail (Map<String, String> reqeustMap) {
		logger.info("requestMap...{}",reqeustMap);
		boolean result ;
		List<ComusermVO> comusermVO= mybatis.selectList("HomeMapper.checkEmail", reqeustMap); 
		
		if(comusermVO != null) { // select 값있음 > 중복 
			result = false;
		}else {
			result = true;
		}
		return result;
	}
	
	public ComusermVO checkLogin (Map<String, String> reqeustMap) {
		logger.info("requestMap...{}",reqeustMap);
		ComusermVO comusermVO = mybatis.selectOne("HomeMapper.checkLogin", reqeustMap);
		
//		if(comusermVO != null) { // select 값있음 > 중복 
//			result = false;
//		}else {
//			result = true;
//		}
		
		return comusermVO;
	}
	
	public int apply (Map<String, String> reqeustMap) {
		logger.info("requestMap...{}",reqeustMap);
		int result = mybatis.insert("HomeMapper.apply", reqeustMap);
		
		return result;
	}
	
	public List<ApplyVO> applyList() {
		List<ApplyVO> list = mybatis.selectList("HomeMapper.applylist");
		return list;
	}
	
	public List<ApplyVO> myApplyList(String userId) {
		List<ApplyVO> list = mybatis.selectList("HomeMapper.myapplylist", userId);
		return list;
	}
	
	public List<ApplyVO> myDelivery(String userId) {
		List<ApplyVO> list = mybatis.selectList("HomeMapper.mydelivery", userId);
		return list;
	}
	
	public ApplyVO applyView(int seq) {
		ApplyVO applyVO = mybatis.selectOne("HomeMapper.applyview", seq);
		return applyVO;
	}
	
	public List<ComusermVO> checkBox(String userId) {
		List<ComusermVO> list = mybatis.selectList("HomeMapper.checkbox", userId);
		return list;
	}
	
	public int delteDelivery(int seq) {
		int result = mybatis.delete("HomeMapper.deletedelivery", seq);
		return result;
	}
	
	public int deliver(Map<String, Object> parameterMap) {
		int result = mybatis.update("HomeMapper.deliver", parameterMap);
		logger.info("deliver result.. " + result);
		return result;
	}
	
	public int deliverySuc(Map<String, Object> parameterMap) {
		int result = mybatis.update("HomeMapper.deliverysuc", parameterMap);
		logger.info("deliverysuc result.. " + result);
		return result;
	} 
	
	public int userInfo(Map<String, String> parameterMap) {
		int result = mybatis.update("HomeMapper.userinfo", parameterMap);
		logger.info("userInfo result.. " + result);
		return result;
	}
	
}