package com.zero_jun.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class CustomLoginSucessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resq, Authentication auth)
			throws IOException, ServletException {
		log.warn("login success");
		
		String url = req.getParameter("url");
		req.getHeader("");
		
		if(url == null) {
			//referer 사용법
			String referer = req.getHeader("referer");
			log.warn(referer);
		} else {
			//url 사용법
		}
		/*
		 * Set<String> roles = new HashSet<>();
		 * 
		 * auth.getAuthorities().forEach(a -> roles.add(a.getAuthority()));
		 */
		
		/*
		 * if(roles.contains("ROLE_ADMIN")){ resq.sendRedirect("/sample3/admin");
		 * return; } if(roles.contains("ROLE_MEMBER")){
		 * resq.sendRedirect("/sample3/member"); return; }
		 * resq.sendRedirect("/sample3/all");
		 */
	}

}
