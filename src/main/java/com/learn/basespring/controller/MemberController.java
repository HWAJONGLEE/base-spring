package com.learn.basespring.controller;

import org.springframework.stereotype.Controller;

import com.learn.basespring.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
}
