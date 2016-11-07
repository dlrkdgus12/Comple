package comple.common.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import comple.common.service.UserService;
import comple.common.util.JSONResultBuilder;
import comple.common.vo.User;

@Controller
public class SampleController {
    Logger log = Logger.getLogger(this.getClass());
    @Autowired
	UserService userService;
     
    @RequestMapping(value="main.do", produces="application/json",  headers="Accept=*/*")
	public @ResponseBody ModelAndView main(String option
			){
    	ModelAndView mo = new ModelAndView("tables");
		option = "main";
		mo.addObject("option", option);

		return mo;
	}
    
    @RequestMapping(value="login.do", produces="application/json",  headers="Accept=*/*")
	public @ResponseBody ModelAndView login(String option
			){
    	ModelAndView mo = new ModelAndView("tables");
		option = "login";
		mo.addObject("option", option);

		return mo;
	}
    /**
	 * 로그인 하기
	 * @param req
	 * @param loginid
	 * @param passwd
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	@SuppressWarnings ("rawtypes")
	@RequestMapping (value = "login.json")
	public @ResponseBody HashMap loginJson(
			HttpServletRequest req,
			String loginid,
			String passwd
			) throws NoSuchAlgorithmException{
		User user = userService.selectUserLogin(loginid, passwd);
		
		if (user != null){
			// 로그인 성공시
			// 세션 생성하기
			HttpSession session = req.getSession(true);
			session.setAttribute("user", loginid);
			
			return JSONResultBuilder.buildResult(1, "로그인 성공");
		} else {
			// 로그인 실패시
			return JSONResultBuilder.buildResult(0, "로그인 실패");
		}
	}
}