package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardVO;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(long bno);

	void modify(BoardVO bvo);

	int delete(int bno);





}
