package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;

@Mapper
public interface BoardMapper {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO detail(long bno);

	int modify(BoardVO bvo);

	int delete(int bno);

	int gettotalCount(PagingVO pgvo);

	long getBno();

}
