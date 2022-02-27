package com.learn.basespring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.learn.basespring.domain.Member;

//@Repository 어노테이션 없이 빈 등록 가능함
public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById (Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
