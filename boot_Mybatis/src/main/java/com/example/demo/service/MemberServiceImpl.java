package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.CommentMapper;
import com.example.demo.repository.MemberMapper;
import com.example.demo.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper Mmapper;

	@Override
	@Transactional
	public int insert(MemberVO mvo) {
		int isok = Mmapper.insert(mvo);
		return Mmapper.insertAuth(mvo.getEmail());
	}
}
