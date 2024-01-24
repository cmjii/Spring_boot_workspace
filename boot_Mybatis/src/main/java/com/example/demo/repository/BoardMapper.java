package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardVO;

@Mapper
public interface BoardMapper {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(long bno);

	void modify(BoardVO bvo);

	int delete(int bno);

}
