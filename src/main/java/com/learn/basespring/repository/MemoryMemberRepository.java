package com.learn.basespring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.learn.basespring.domain.Member;

public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> finById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
			.filter(member -> member.getName().equals(name))
			.findAny(); //결과값이 없으면 옵셔널로 반환
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

}
