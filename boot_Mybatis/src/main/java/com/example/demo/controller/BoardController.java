package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info("bvo++++++++++++++++++++++++++"+bvo);
		int isok = bsv.insert(bvo);
		return "index";
	}
	
	@GetMapping("/list")
	public void list(Model m,PagingVO pgvo) {
		log.info("pgvo++++++++"+pgvo);
		//totalCount
		int totalCount = bsv.gettotalCount(pgvo);
		//PagingHandler객체 생성
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		List<BoardVO> list = bsv.getList(pgvo);
		m.addAttribute("list", list);
		//model에 PagingHandler객체 보내기
		m.addAttribute("ph", ph);
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model m, @RequestParam("bno") long bno) {
		log.info("bno +++++++++++++++++++"+bno);
		BoardVO bvo = bsv.detail(bno);
		m.addAttribute("bvo", bvo);
	}

	@PostMapping("/modify")
	public String modify(RedirectAttributes re, BoardVO bvo) {
		log.info("bvo : +++++++++++++++++++"+bvo);
		bsv.modify(bvo);
		re.addFlashAttribute("mod", "1");
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") int bno, RedirectAttributes re) {
		log.info("bno ++++++++++++++"+bno);
		int isok = bsv.delete(bno);
		re.addFlashAttribute("del", "1");
		return "redirect:/board/list";
	}
	
	
}
