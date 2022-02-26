package com.learn.basespring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.learn.basespring.domain.Member;
import com.learn.basespring.repository.MemoryMemberRepository;
import com.learn.basespring.service.MemberService;

public class MemberServiceTest {
	
	MemberService memberService = new MemberService();
	MemoryMemberRepository memberRepository = new MemoryMemberRepository();
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("hello");
		//when
		Long saveId = memberService.join(member);
		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		//when
		memberService.join(member1);
		
		IllegalStateException e =  assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
		
		
		/*수정 후
		 * assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		 */
		
		/* 수정전 , test 코드에서 try catch 로 잡을 필요 없게 변경해야함
		 * memberService.join(member1); try { memberService.join(member2); fail(); }
		 * catch (IllegalStateException e) {
		 * assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); }
		 */
		
		//then
	}
	
	@Test
	void findMember() {
		
	}
	
	
}