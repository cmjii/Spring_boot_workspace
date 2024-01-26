package com.example.demo.controller;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@Controller
@RequiredArgsConstructor
public class CommentController {
	private final CommentService csv;
	
	
//	원래 하던 방법
//	@PostMapping(value="/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
//		
//		log.info("cvo:::::::::::::::::::::::::::::::::::::::::::::"+cvo);
//		int isok = csv.insert(cvo);
//		return isok >0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
//		
//	}
	
	
	//-----requestbody로 받기
	@PostMapping("/post")
	@ResponseBody
	public String post(@RequestBody CommentVO cvo) {
		log.info("cvo:::::::::::::::::::>>>>>>>>>>>>>>>"+cvo);
		int isok = csv.insert(cvo);
		return isok >0? "1" : "0";
	}
	
	@GetMapping("/{bno}/{page}")
	@ResponseBody
	public PagingHandler list(@PathVariable("bno")long bno, @PathVariable("page") int page){
		log.info("bno:::::::::::::::::::::>>>>>>"+bno+":::::page>>>>>>>>>>>>>>>>>>>>>"+page);
		//list<commentVO> / PagingHandler
		//비동기 => 한객체만 전송가능
		PagingVO pgvo = new PagingVO(page,5); //한 페이지에 5개씩 표시
		PagingHandler ph = csv.getList(bno, pgvo);
		return ph;
	}
	
	@PutMapping("/edit")
	@ResponseBody
	public String edit(@RequestBody CommentVO cvo) {
		log.info("cvo:::::::::::::::::::>>>>>>>>>>>>>>>"+cvo);	
		int isok = csv.edit(cvo);
		return isok >0? "1" : "0";
	}
}