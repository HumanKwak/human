package com.zero_jun.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.zero_jun.domain.MemberVo;
import lombok.Getter;

@Getter
public class CustomUser extends User{
	private MemberVo memberVo;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(MemberVo vo) {
		super(vo.getUserid(),vo.getUserpw(),
				vo.getAuths()
					.stream()
					.map(
						a->new SimpleGrantedAuthority
						(a.getAuth()))
						.collect(Collectors.toList()));
		this.memberVo = vo;
	}
}
