package com.learn.basespring.repository;

import org.junit.jupiter.api.Test;

import com.learn.basespring.domain.Member;

public class MemoryMemberRepositoryTest {

	MemberRepository repository = new MemoryMemberRepository();
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.finById(member.getId()).get();
	}
}
