package com.shinhan.sbproject.webboard;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@SpringBootTest
public class WebBoardReplyTest {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@Test
	void f3() {
		//1,2,3번 게시글의 댓글을 3개씩 입력하고싶음
		;
		boardRepo.findAllById(java.util.Arrays.asList(77L)).forEach(board->{
			
			//댓글을 각각 3개씩 쓴다
			List<WebReply> replies = new ArrayList<>();
			for(int i=0; i<3; ++i) {
				WebReply reply = WebReply.builder()
						.replyText("이것은 댓글을 바로 저장" + new Date())
						.replyer("Savor" + board.getBno())
						.board(board)
						.build();
				//replies.add(reply);
				replyRepo.save(reply);
			}
			//board.setReplies(replies);
			//boardRepo.save(board);
		});
	}
	
	void f2() {
		//do nothing
	}
	
	//@Test
	void f1() {
		for(int i=0; i<100; ++i) {
			WebBoard newBoard = WebBoard.builder()
					.title("title" + i)
					.writer("용수" + (100-i))
					.content("용수가 쓴 내용입니다..^^ 이거 쓴 시간은..." + (new Date()).toString())
					.build();
			
			if(i==1 || i==10 || i==20) {
				List<WebReply> replies = new ArrayList<>();
				for(int j=0; j<5; ++j) {
					WebReply reply = WebReply.builder()
							.replyText("댓글~~~" + (j*13 + i*11 - 2))
							.replyer("정우")
							.board(newBoard)
							.build();
					replies.add(reply);
				}
				newBoard.setReplies(replies);
			}
			
			boardRepo.save(newBoard);
			
		}
	}
}
