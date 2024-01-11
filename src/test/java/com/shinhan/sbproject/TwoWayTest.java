package com.shinhan.sbproject;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.firstzone.repository.FreeBoardReplyRepository;
import com.shinhan.firstzone.repository.FreeBoardRepository;
import com.shinhan.sbproject.vo3.FreeBoard;
import com.shinhan.sbproject.vo3.FreeBoardReply;
import com.shinhan.sbproject.vo3.QFreeBoard;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TwoWayTest {
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo; 
	
	@Transactional
	@Test //QueryDSL
	void f5() {
		String title ="니다";
		BooleanBuilder builder = new BooleanBuilder();
		QFreeBoard board = QFreeBoard.freeBoard;
		
		
		//if (title != null) {...};
		builder.and(board.title.like("%"+title+"%"));
		boardRepo.findAll(builder).forEach(b->log.info(b.toString()));
		
		//Sort, FindAll
		boardRepo.findAll(builder, Sort.by(Direction.DESC, "bno")).forEach(b->log.info(b.toString()));

		//Page
		Pageable page = PageRequest.of(0, 5, Direction.DESC, "bno");
		boardRepo.findAll(builder, page).getContent().forEach(b->log.info(b.toString()));
		
		Page<FreeBoard> result = boardRepo.findAll(builder, page);
		log.info("건수 --->", result.getTotalElements());
		
		
		
	}
	
	//@Test
	void f4() {
		String title = "입니다";
		boardRepo.selectByTitle(title).forEach(b->log.info(Arrays.toString(b)));
		log.info("#################################");
		
		boardRepo.selectByTitle2(title).forEach(b->log.info(Arrays.toString(b)));
		log.info("#################################");
		
		boardRepo.selectByTitle3(title).forEach(b->log.info(Arrays.toString(b)));
		log.info("#################################");
		
		
		
	}
	
	//@Test
	void findByWriterTest() {
		Pageable paging = PageRequest.of(0, 10, Sort.by("regdate").descending());
		String yongsu = "용수";
		boardRepo.findByWriter(yongsu, paging)
		.forEach(b->{
			log.info(b.getTitle());
			log.info(b.getContent());
			log.info("##################{}--{}", b.getRegdate(), b.getUpdatedate());
			});
	}
	
	//@Test
	void findByBnoBetweenTest() {
		Pageable paging = PageRequest.of(0, 5);
		
		boardRepo.findByBnoBetween(1L, 15L, paging)
		.forEach(b->{
			log.info(b.getTitle());
			log.info(b.getContent());
			log.info("##################");
			});
	}
	
	//@Test
	void findByBnoGtTest() {
		Pageable paging = PageRequest.of(0, 5);
		
		boardRepo.findByBnoGreaterThan(1L, paging)
		.forEach(b->{
			log.info(b.getTitle());
			log.info(b.getContent());
			log.info("##################");
			});
	}
	
	//@Test
	void replySelectByBoard() {
		FreeBoard board = FreeBoard.builder().title("temp").bno(20L).build();
		List<FreeBoardReply> replyList = replyRepo.findByBoard(board);
		
		replyList.forEach(reply->{
			log.info("rno:" + reply.getRno());
			log.info("reply:" + reply.getReply());
			log.info("replyer:" + reply.getReplyer());
			log.info("regdate:" + reply.getRegdate());
			log.info("updatedate:" + reply.getUpdatedate());
			log.info("#########################################");
			
		});
	}
	
	//@Test
	void replySelect() {
		replyRepo.findAll(Sort.by(Direction.DESC, "rno")).forEach(reply->{
			log.info("내용->{}", reply.getReply());
			log.info("작성자->{}", reply.getReplyer());
			log.info("board내용->{}", reply.getBoard().getContent());
		});
	}
	
	/*각 board의 댓글의 개수 출력하기
	 * */
	//@Transactional
	//@Test
	void getReplyCount() {
		boardRepo.findAll().forEach(board->{
			log.info(board.getBno() + "-----" + board.getReplies().size());
		});
	}
	
	//@Test
	void replyInsert2() {
		List<Long> blist = java.util.Arrays.asList(5L, 10L, 15L);
		boardRepo.findAllById(blist).forEach(board->{
			IntStream.range(1, 6).forEach(i->{
				FreeBoardReply reply = FreeBoardReply.builder()
						.reply("firstzone" + board.getBno())
						.replyer("방용수" + i)
						.board(board)
						.build();
				replyRepo.save(reply);
			});
		});
	}
	
	/*
	 * 특정 board의 댓글 입력
	 * */
	//@Test
	void replyInsert() {
		FreeBoard board = boardRepo.findById(20L).orElse(null);
		
		if(board == null) {
			return;
		}
		
		//댓글 5개 넣음
		
		IntStream.range(1, 6).forEach(i->{
			FreeBoardReply reply = FreeBoardReply.builder()
					.reply("나는 용수")
					.replyer("방용수")
					.board(board)
					.build();
			
			replyRepo.save(reply);
		});
		
	}
	
	//@Transactional
	//@Test
	void boardSelect() {
		boardRepo.findAll(Sort.by(Direction.DESC, "bno"))
		.forEach(b->log.info(b.toString()));
	}
	
	
	//@Test
	void boardInsert() {
		for(int i=0; i < 20; ++i) {
			FreeBoard newBoard = FreeBoard.builder()
					.title("제목입니다ㅎ")
					.writer("유저"+i%5)
					.content("내용입니다.")
					.build();
			
			boardRepo.save(newBoard);
		}
	}
	
}







