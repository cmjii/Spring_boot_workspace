package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.handler.FileHandler;
import com.example.demo.security.MemberVO;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService msv;
	private final PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/register")
	public String join() {
		return "/member/join";
	}
	
	@PostMapping("/register")
	public String join(MemberVO mvo) {
		log.info("mvo::::::::::::::::::::::>>>>>>>>>>>>>>>>>>>>>>"+mvo);
		mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
		int isok = msv.insert(mvo);
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
}
