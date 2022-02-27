package com.learn.basespring.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn.basespring.repository.JdbcMemberRepository;
import com.learn.basespring.repository.MemberRepository;
//di 주입방식중에 하나 @Repository 어노테이션 없이 bean 객체 생성
@Configuration
public class SpringConfig {

	private DataSource dataSource;
	
	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
		return new JdbcMemberRepository(dataSource);
	}
}
