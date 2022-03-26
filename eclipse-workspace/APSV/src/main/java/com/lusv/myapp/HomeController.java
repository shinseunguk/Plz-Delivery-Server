package com.lusv.myapp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Handles requests for the application home page.d
 */
@Controller
@SessionAttributes("member")
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	HomeService homeService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) throws UnknownHostException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		InetAddress local = InetAddress.getLocalHost();
		String ip = local.getHostAddress();
		
		logger.info("#############"+ip);


		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
	public void favicon(HttpServletRequest request, HttpServletResponse reponse) {
		try {
			reponse.sendRedirect("/resources/favicon.ico");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/address")
	public String home(){
		logger.info("API 호출");
		
		return "address";
	}
	
	@RequestMapping(value = "/address2")
	@ResponseBody
	public String home2(HttpServletRequest request){
		logger.info("API 호출22222222");
		
		String address = request.getParameter("address");
		
		logger.info("address values: " + address);
		
		return address;
	}
	
	@RequestMapping(value = "/android")
	@ResponseBody
	public String android() {
		
		logger.info("android");
	
		homeService.android();
		
		return "dddddddddddddddd";
	}
	
	@RequestMapping(value = "/signup")
	@ResponseBody
	public int signUp(HttpServletRequest request) {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("id", request.getParameter("id"));
		requestMap.put("password", request.getParameter("password"));
		requestMap.put("name", request.getParameter("name"));
		requestMap.put("tel", request.getParameter("tel"));
		requestMap.put("birth", request.getParameter("birth"));
		requestMap.put("gender", request.getParameter("gender"));
		requestMap.put("address1", request.getParameter("address1"));
		requestMap.put("address2", request.getParameter("address2"));
		
		int result = homeService.signUp(requestMap);
		
		if(result == 1) {
			logger.info(" 회원가입 완료 ");
		}else {
			logger.info(" 회원가입 실패 ");
		}
		return result ;
	}
	
	@RequestMapping(value = "/checkEmail")
	@ResponseBody
	public String checkemail(HttpServletRequest request) {
		String id = request.getParameter("id");
		logger.info("checkemail.. "+ request.getParameter("id"));
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("id", id);
		boolean result = homeService.checkEmail(requestMap);
		
		String result2 = String.valueOf(result);
		return result2 ;
	}
	
	@RequestMapping(value = "/apply")
	@ResponseBody
	public int apply(HttpServletRequest request, HttpSession session) {
		logger.info("apply.. ");
		String startAddress1 = request.getParameter("startAddress1");
		String startAddress2 = request.getParameter("startAddress2");
		String endingAddress1 = request.getParameter("endingAddress1");
		String endingAddress2 = request.getParameter("endingAddress2");
		String applyName = request.getParameter("applyName");
		String applyTel = request.getParameter("applyTel");
		String applyItem = request.getParameter("applyItem");
		String applyPrice = request.getParameter("applyPrice");
		String arriveName = request.getParameter("arriveName");
		String arriveTel = request.getParameter("arriveTel");
		String startingDate = request.getParameter("startingDate");
		String endingDate = request.getParameter("endingDate");
		
		
		logger.info("log######### "+ startAddress1);
		logger.info("log######### "+ startAddress2);
		logger.info("log######### "+ endingAddress1);
		logger.info("log######### "+ endingAddress2);
		logger.info("log######### "+ applyName);
		logger.info("log######### "+ applyTel);
		logger.info("log######### "+ applyItem);
		logger.info("log######### "+ applyPrice);
		logger.info("log######### "+ arriveName);
		logger.info("log######### "+ arriveTel);
		logger.info("log######### "+ startingDate);
		logger.info("log######### "+ endingDate);
		
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("startAddress1", startAddress1);
		requestMap.put("startAddress2", startAddress2);
		requestMap.put("endingAddress1", endingAddress1);
		requestMap.put("endingAddress2", endingAddress2);
		requestMap.put("applyName", applyName);
		requestMap.put("applyTel", applyTel);
		requestMap.put("applyItem", applyItem);
		requestMap.put("applyPrice", applyPrice);
		requestMap.put("arriveName", arriveName);
		requestMap.put("arriveTel", arriveTel);
		requestMap.put("startingDate", startingDate);
		requestMap.put("endingDate", endingDate);
		requestMap.put("applyId", (String) session.getAttribute("member"));
		int result = homeService.apply(requestMap);
		
		return result; 
	}
	
	@RequestMapping(value = "/applylist")
	@ResponseBody
	public List<ApplyVO> applyList(HttpServletRequest request, HttpSession session) {
//		logger.info("applyList.. ");
		List<ApplyVO> list = homeService.applyList();
		
		return list; 
	}
	
	@RequestMapping(value = "/myapplylist")
	@ResponseBody
	public List<ApplyVO> myApplyList(HttpServletRequest request, HttpSession session) {
		logger.info("myApplyList.. ");
		List<ApplyVO> list = homeService.myApplyList((String) session.getAttribute("member"));
		
		return list; 
	}
	
	@RequestMapping(value = "/mydelivery")
	@ResponseBody
	public List<ApplyVO> myDelivery(HttpServletRequest request, HttpSession session) {
		logger.info("myDelivery.. ");
		List<ApplyVO> list = homeService.myDelivery((String) session.getAttribute("member"));
		
		return list; 
	}
	
	@RequestMapping(value = "/applyview")
	@ResponseBody
	public ApplyVO applyView(HttpServletRequest request, HttpSession session) {
		logger.info("applyView.. ");
		int seq = Integer.parseInt(request.getParameter("seq"));
		logger.info("seq####"+seq);		
		ApplyVO applyVO = homeService.applyView(seq);
		return applyVO; 
	}
	
	@RequestMapping(value = "/deletedelivery")
	@ResponseBody
	public int deletedelivery(HttpServletRequest request, HttpSession session) {
		int result;
		int seq = Integer.parseInt(request.getParameter("seq"));
		logger.info("deletedelivery..."+seq);
		
		result = homeService.delteDelivery(seq);
		logger.info("deletedelivery result... " + result);
		
		return result; 
	}
	
	@RequestMapping(value = "/deliver")
	@ResponseBody
	public int deliver(HttpServletRequest request, HttpSession session) {
		int result;
		int seq = Integer.parseInt(request.getParameter("seq"));
		logger.info("deliver..."+seq);
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("seq", seq);
		parameterMap.put("userId", (String) session.getAttribute("member"));
		logger.info("deliver...{}", parameterMap.toString());
		
		result = homeService.deliver(parameterMap);
		logger.info("deliver result... " + result);
		
		return result; 
	}
	
	@RequestMapping(value = "/deliverysuc")
	@ResponseBody
	public int deliverySuc(HttpServletRequest request, HttpSession session) {
		int result;
		int seq = Integer.parseInt(request.getParameter("seq"));
		logger.info("deliver..."+seq);
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("seq", seq);
		parameterMap.put("userId", (String) session.getAttribute("member"));
		logger.info("deliver...{}", parameterMap.toString());
		
		result = homeService.deliverySuc(parameterMap);
		logger.info("deliver result... " + result);
		
		return result; 
	}
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public String login(HttpSession session, HttpServletRequest request, @RequestHeader Map<String, String> data, HttpServletResponse response) {
		logger.info("header ####"+ data);
		
		String result = null;
//		logger.info("checkLogin.. "+ request.getParameter("id")+" "+request.getParameter("pw"));
	
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("id", id);
		requestMap.put("pw", pw);
		
		//성공시 false 실패시 true 반환
		ComusermVO comusermVO = homeService.checkLogin(requestMap);
		
		if(comusermVO != null) { // select 값있음 > 중복 
			result = "false";
		}else {
			result = "true";
		}
		
		if(comusermVO != null) { 
//			session.invalidate();
			session.setAttribute("member", comusermVO.getId());
		}
		
		logger.info("comusermVO.getId()...{id="+ comusermVO.getId() +"}");
		
//		logger.info("getSession /login... " + userId);
		return result; 
	}
	
	@RequestMapping(value = "/checkbox")
	@ResponseBody
	public List<ComusermVO> checkBox(HttpServletRequest request, @RequestHeader Map<String, String> data, HttpSession session, HttpServletResponse response){
		Cookie[] myCookies = request.getCookies();

		logger.info("header "+ data);
//		logger.info("sessionGet,,,,1"+ userId);
		logger.info("(String) session.getAttribute(\"member\") -> "+(String) session.getAttribute("member"));
		 
		List<ComusermVO> list = homeService.checkBox((String) session.getAttribute("member"));
		return list; 
	}
	
	@RequestMapping(value = "/userinfo")
	@ResponseBody
	public int userInfo(HttpServletRequest request, HttpSession session){
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("name", name);
		requestMap.put("tel", tel);
		requestMap.put("address1", address1);
		requestMap.put("address2", address2);
		requestMap.put("userId", (String) session.getAttribute("member"));
		
		int result = homeService.userInfo(requestMap);
		
		return result;
	}
	
	@RequestMapping(value = "/logout")
	@ResponseBody
	public boolean logout(HttpSession session) {
		logger.info("logout.. ");
		session.removeAttribute("member");
		//session.invalidate();
		
		return true;
	}
}
