package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.repository.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService{
	private final CommentMapper cmapper;

	@Override
	public int insert(CommentVO cvo) {
		return cmapper.insert(cvo);
	}

	/*
	 * @Override public List<CommentVO> getList(long bno) { return
	 * cmapper.getList(bno); }
	 */

	@Override
	public int edit(CommentVO cvo) {
		return cmapper.edit(cvo);
	}

	@Override
	@Transactional
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// controller 처리 가능, 서비스에서 처리하는게 속도가 더 빠름
		int totalCount = cmapper.getTotal(bno);
		List<CommentVO> list = cmapper.getList(bno,pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}


}
