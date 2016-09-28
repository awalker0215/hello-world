package org.iii.web.login;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@Resource(name = "LoginService")
	LoginService loginService;

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!!!");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			for (GrantedAuthority authority : userDetail.getAuthorities()) {
	            if (authority.getAuthority().equals("ROLE_ADMIN"))
	            {
	    			List alluserinfo = loginService.getallUserinfo();
	    			model.addObject("alluserinfo", alluserinfo);
	            }
	            else
	            {
	            	String username = userDetail.getUsername();
	    			String useremail = loginService.getUserEmail(username);
	    			model.addObject("useremail", useremail);
	            }
	        }
		}

		model.setViewName("hello");
		return model;

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePage(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView();
		
		String username = (String) request.getParameter("check_radio");
		loginService.deleteUser(username);
		
		System.out.println("delete "+username);
		
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!!!");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			for (GrantedAuthority authority : userDetail.getAuthorities()) {
	            if (authority.getAuthority().equals("ROLE_ADMIN"))
	            {
	    			List alluserinfo = loginService.getallUserinfo();
	    			model.addObject("alluserinfo", alluserinfo);
	            }
	        }
		}
		
		model.setViewName("hello");
		
		return model;

	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView commonPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView();

		model.setViewName("insert");
		
		return model;

	}
	
	@RequestMapping(value = "/insertpage", method = RequestMethod.POST)
	public ModelAndView insertPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView();
		
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String email = (String) request.getParameter("email");
		loginService.insertUser(username,password,email,"1");
		System.out.println("insert a user");
		
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!!!");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			for (GrantedAuthority authority : userDetail.getAuthorities()) {
	            if (authority.getAuthority().equals("ROLE_ADMIN"))
	            {
	    			List alluserinfo = loginService.getallUserinfo();
	    			model.addObject("alluserinfo", alluserinfo);
	            }
	        }
		}
		
		model.setViewName("hello");
		
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!!");

		model.setViewName("admin");
		
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}

		model.setViewName("login");
		return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
			
		}
		
		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
	public ModelAndView hellowpage() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("helloworld");
		return model;

	}

	@RequestMapping(value = "/newinsert", method = RequestMethod.POST)
	public ModelAndView inSert(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView();

		model.setViewName("newinsert");
		
		return model;

	}
	
	@RequestMapping(value = "/insertpage2", method = RequestMethod.POST)
	public ModelAndView insertPage2(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView();
		
		String username = (String) request.getParameter("username");
		loginService.insertUser2(username);
		System.out.println("insert a user");
		
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!!!");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			for (GrantedAuthority authority : userDetail.getAuthorities()) {
	            if (authority.getAuthority().equals("ROLE_ADMIN"))
	            {
	    			List alluserinfo2 = loginService.getallUserinfo2();
	    			model.addObject("alluserinfo2", alluserinfo2);
	            }
	        }
		}
		
		model.setViewName("hello2");
		
		return model;

	}
	
	@RequestMapping(value = "/delete2", method = RequestMethod.POST)
	public ModelAndView deletePage2(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView();
		
		String username = (String) request.getParameter("check_radio");
		System.out.println(username);
		loginService.deleteUser2(username);
		
		System.out.println("delete2 "+username);
		
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!!!");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			for (GrantedAuthority authority : userDetail.getAuthorities()) {
	            if (authority.getAuthority().equals("ROLE_ADMIN"))
	            {
	    			List alluserinfo2 = loginService.getallUserinfo2();
	    			model.addObject("alluserinfo2", alluserinfo2);
	            }
	        }
		}
		
		model.setViewName("hello2");
		
		return model;

	}
	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public ModelAndView hello2() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("hello2");
		return model;

	}
}