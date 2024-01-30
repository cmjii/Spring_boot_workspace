package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.repository.BoardMapper;
import com.example.demo.repository.FileMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServicelmpl implements BoardService{
	private final BoardMapper mapper;
	private final FileMapper fmapper;

	@Override
	@Transactional
	public int insert(BoardDTO boardDTO) {
		int isok = mapper.insert(boardDTO.getBvo());
		if(boardDTO.getFlist()==null) {
			return isok;
		}
		if(isok > 0 && boardDTO.getFlist().size()>0) {
			long bno = mapper.getBno();
			for(FileVO fvo : boardDTO.getFlist()) {
				fvo.setBno(bno);
				isok *= fmapper.insertFile(fvo);
			}
		}
		return isok;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return mapper.getList(pgvo);
	}
	@Transactional
	@Override
	public BoardDTO detail(long bno) {
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(mapper.detail(bno));
		bdto.setFlist(fmapper.getFileList(bno));;
		log.info("bdto::::::::::::::::::::::::::::>>>>>>>>>>>>>>>>>>>>"+bdto);
		return bdto;
	}

	@Override
	@Transactional
	public void modify(BoardDTO boardDTO) {
		int isok = mapper.modify(boardDTO.getBvo());
		if(isok>0 && boardDTO.getFlist().size()>0) {
			long bno = boardDTO.getBvo().getBno();
			for(FileVO fvo : boardDTO.getFlist()) {
				fvo.setBno(bno);
				isok*= fmapper.insertFile(fvo);
			}
		}
	}

	@Override
	public int delete(int bno) {
		return mapper.delete(bno);
	}

	@Override
	public int gettotalCount(PagingVO pgvo) {
		return mapper.gettotalCount(pgvo);
	}




}
