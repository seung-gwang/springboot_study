package com.shinhan.sbproject.controller;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.firstzone.repository.FreeBoardRepository;
import com.shinhan.sbproject.vo3.FreeBoard;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ThymeleafController {
	@Autowired
	FreeBoardRepository brepo;
	
	@GetMapping("/hello3")
	public void f4(Model model, HttpServletRequest request, HttpSession session) {
		model.addAttribute("myname",  request.getParameter("name"));
		model.addAttribute("myname2",  session.getId());
		
		model.addAttribute("now", new Date());
		model.addAttribute("price", 12345678);
		model.addAttribute("title", "TITLETIELTIELTE TEST TEST TEST TEST TEST");
		model.addAttribute("options", Arrays.asList("AA", "BBB", "CCCC", "I am Happy"));
	}
	
	@GetMapping("freeboard/list")
	public void fBoardList(Model model) {
		model.addAttribute("loginUser", "유저0");
		model.addAttribute("myFriend", "용수");
		model.addAttribute("blist", brepo.findAll());
	}
	
	
	@GetMapping("/hello1")
	public void f1(Model model) {
		log.info("hello 요청");
		model.addAttribute("greeting", "헬로~");
		model.addAttribute("board", brepo.findById(6L).orElse(null));
		//접두사 : classpath:templates
		//접미사 : .html
		//return classpathtemplates/hello1.html
	}
	
	@GetMapping("/hello2")
	public String f2(Model model) {
		log.info("hello 요청 - /hello2");
		//접두사 : classpath:templates
		//접미사 : .html
		//return classpathtemplates/hello1.html
		model.addAttribute("greeting", "안뇽~");
		
		FreeBoard board = FreeBoard.builder()
				.bno(99L)
				.title("99번째 글 제목")
				.writer("김작성")
				.regdate(new Timestamp(System.currentTimeMillis()))
				.build();
		model.addAttribute("board", board);
		return "hello1";
	}
	
}

