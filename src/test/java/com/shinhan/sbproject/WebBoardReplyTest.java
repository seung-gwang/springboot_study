package com.shinhan.sbproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.sbproject.webboard.WebBoardRepository;
import com.shinhan.sbproject.webboard.WebReplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class WebBoardReplyTest {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@Test
	void f1() {
		//Board 100개 insert
		
		//Reply 1, 10, 20 board에 댓글 각각
	}
}
