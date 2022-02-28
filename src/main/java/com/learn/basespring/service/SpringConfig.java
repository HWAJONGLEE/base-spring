package com.learn.basespring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn.basespring.aop.TimeTraceAop;
import com.learn.basespring.repository.MemberRepository;
//di 주입방식중에 하나 @Repository 어노테이션 없이 bean 객체 생성
@Configuration
public class SpringConfig {

	private final MemberRepository memberRepository;
	
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
	@Bean
	public TimeTraceAop timeTraceAop() {
		return new TimeTraceAop();
	}
	//@Bean
	//public MemberRepository memberRepository() {
		//return new MemoryMemberRepository();
		//return new JdbcMemberRepository(dataSource);
		//return new JpaMemberRepository(em);
	//}
}
