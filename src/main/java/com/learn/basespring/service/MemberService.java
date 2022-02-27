package com.learn.basespring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learn.basespring.domain.Member;
import com.learn.basespring.repository.MemberRepository;

@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public Long join(Member member) {
		
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		//같은 이름이 있는 중복 회원X
		memberRepository.findByName(member.getName())
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
	}
	
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
