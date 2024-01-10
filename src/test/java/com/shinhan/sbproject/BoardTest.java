package com.shinhan.sbproject;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.firstzone.repository.BoardRepository;
import com.shinhan.sbproject.vo.BoardVO;
import com.shinhan.sbproject.vo.QBoardVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
public class BoardTest {

    @Autowired
    BoardRepository brepo;
    
    
    @Test
    void f20() {
    	BooleanBuilder builder = new BooleanBuilder();
    	QBoardVO board = QBoardVO.boardVO;
    	
    	Long bno = 5L;
    	String writer = "user3";
    	String content = "%억이%";
    	
    	if(bno!=null) {
    		builder.and(board.bno.gt(bno));
    	}
    	
    	if(writer != null) {
    		builder.and(board.writer.eq(writer));
    	}
    	
    	if(content != null) {
    		builder.and(board.content.like(content));
    	}
    	
    	
    	
    	
    	
    	
    	log.info(builder.toString());
    	
    	//동적으로 SQL문을 생성함
    	
    	
    	List<BoardVO> blist = (List<BoardVO>)brepo.findAll(builder);
    	blist.forEach(b->log.info(b.toString()));
    }
    
    @Test
    void f18() {
    	List<BoardVO> result = brepo.selectByTitleAndWriter(2L, "ING", "user");
    	List<Object[]> result2 = brepo.selectTitleWriterContentByTitleAndWriter(2L, "ING", "user");
    	assert result.size() == 3;
    	assert result.size() == brepo.selectByTitleAndWriterNative(2L, "ING", "user").size();
    	for(Object oArray : result2) {
    		//log.info(oArray.ge);
    	}
    	assert result.size() == result2.size();
    }
    
    //@Test
    void f17() {
    	Pageable paging = PageRequest.of(2, 50, Sort.by("writer")); //page, pageSize
    	Page<BoardVO> result = brepo.findByBnoBetween(0L, 999999L, paging);
    	
    	List<BoardVO> boards = result.getContent();
    	int cnt = 0;
    	for(BoardVO b : result) {
    		cnt++;
    		log.info(b.toString());
    	}
    	
    	assert cnt == 40;
    	assert boards.size() == 40;
    	
    }

    //@Test
    //paging
    //@Test
    void f16() {
        Pageable paging = PageRequest.of(0, 6, Sort.by("writer")); //page, pageSize
        brepo.findByBnoGreaterThan(5L, paging).forEach(b -> log.info(b.toString()));
    }

    //@Test
    void f9() {
        List<BoardVO> blist = brepo.findByContentLike("%재미있다%");
        blist.forEach(b -> {
            assert b.getContent().contains("재미있다");
        });

        blist = brepo.findByContentContaining("재미있다");
        blist.forEach(b -> {
            assert b.getContent().contains("재미있다");
        });

        blist = brepo.findByBnoBetweenAndContentContainingAndWriterNotContainingOrderByUpdateDate(50, 100, "기억", "용수");
        blist.forEach(b -> {
            long bno = b.getBno();
            assert bno >= 50 && bno <= 100;
            assert b.getContent().contains("기억");
            assert !b.getWriter().contains("용수");
            log.info(b.toString());
        });
    }

    //@Test
    void f7() {
        List<BoardVO> blist = brepo.findByWriter("user3");

        blist.forEach(b -> {
            log.info(b.toString());
        });

        blist = brepo.findByContent("재미있다5");

        blist.forEach(b -> {
            assert b.getContent().equals("재미있다5");
        });
    }

    //@Test
    void f1() {
        IntStream.rangeClosed(21, 40).forEach(i -> {
            BoardVO board = BoardVO.builder()
                    .title("JAVA" + i)
                    .content("기억이 난다?ㅎㅎ")
                    .writer("user" + i % 5)
                    .build();
            BoardVO newBoard = brepo.save(board);
            log.info("생성된 Board: " + board);
            log.info("입력된 board: " + newBoard);
            assert board.equals(newBoard);
        });
    }

    //@Test
    void f3() {
        Long searchId = 20L;
        brepo.findById(searchId).ifPresentOrElse(
                b -> {
                    log.info("조회정보" + b);
                },
                () -> {
                    log.info("존재하지 않음");
                });
    }

    //@Test
    void f2() {
        brepo.findAll().forEach(board -> {
            log.info(board.toString());
        });
    }

    //@Test
    //update
    void f4() {
        Long searchId = 10L;
        brepo.findById(searchId).ifPresent(b -> {
            b.setTitle("수요일....");
            b.setContent("점심은 돈까스 좋아해요?");
            b.setWriter("용수");
            BoardVO updateBoard = brepo.save(b);

            log.info("원본:" + b);
            log.info("수정:" + updateBoard);
        });
    }

    //@Test
    void f5() {
        Long searchId = 19L;
        brepo.findById(searchId).ifPresent(b -> {
            brepo.delete(b);
        });

        brepo.deleteById(18L);
    }

    //@Test
    void f6() {
        long cnt = brepo.count();
        log.info("건수 :" + cnt);
    }

    //@Test
    void f15() {
        String writer = "user3";
        int cnt = brepo.countByWriter(writer);
        assert cnt == brepo.findByWriter(writer).size();
    }
}
