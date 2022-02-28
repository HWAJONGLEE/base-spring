package com.learn.basespring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.basespring.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository{
	
	@Override
	Optional<Member> findByName(String name);
}
