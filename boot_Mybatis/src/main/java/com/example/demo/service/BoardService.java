package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

public interface BoardService {

	int insert(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pgvo);

	BoardDTO detail(long bno);

	void modify(BoardDTO boardDTO);

	int delete(int bno);

	int gettotalCount(PagingVO pgvo);





}
