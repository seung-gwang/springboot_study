package com.shinhan.sbproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;

import com.shinhan.firstzone.repository.PDSBoardRepository;
import com.shinhan.firstzone.repository.PDSFileRepository;
import com.shinhan.sbproject.vo2.PDSBoard;
import com.shinhan.sbproject.vo2.PDSFile;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OneToMany {
	@Autowired
	PDSBoardRepository brepo;
	
	@Autowired
	PDSFileRepository frepo;
	
	/*Board를 통해서 File을 저장했다면, PDSFile 테이블의 pdsno가 pid로 들어감
	 * File만 저장했다면 pdsno가 Null.
	 * */
	
	@Test
	void getAllFiles() {
		frepo.findAll(Sort.by(Direction.DESC,"fno"))
		.forEach(f->log.info(f.toString()));
		
		Pageable paging = PageRequest.of(0, 3);
		frepo.findAll(paging).forEach(f->log.info("paging->" + f.toString()));;
		
		long filePerPage = 3L;
		long cnt = frepo.count();
		long pageCnt = cnt / filePerPage + (cnt%filePerPage==0?0:1);
		
		log.info("total Page cnt----->{}", pageCnt);
		for(int pageNo = 0; pageNo < pageCnt; ++pageNo) {
			Pageable page = PageRequest.of(pageNo, 3);
			frepo.findAll(page).forEach(f->log.info("paging->" + f.toString()));
		}
		
	}
	
	//@Test
	void deleteBoard() {
		brepo.deleteById(1L);
	}
	
	
	//@Test
	void getFileByBoard() {
		brepo.findById(1L).ifPresent(b->{
			log.info(b.getFiles2().toString());
			List<PDSFile> files2 = b.getFiles2();
			for(PDSFile f : files2) {
				log.info(f.toString());
			}
		});
	}
	
	//@Test
	void fileDelete() {
		frepo.deleteById(5L);
	}
	
	//@Test
	void searchFile() {
		List<PDSBoard> blist = (List<PDSBoard>)brepo.findAll();
		for(PDSBoard board : blist) {
			List<PDSFile> flist = board.getFiles2();
			for(PDSFile f : flist) {
				if(f.getFno()==3L) {
					f.setPdsfilename("FNO3번임");
				}
			}
			brepo.save(board);
		}
	}
	
	@Transactional //Test 클래스이기 때문에 DB에 반영 안됨 (Rollback)
	@Test
	@Commit //DB 반영을 위해 Commit 추가
	void updateFile2() {
		int result = brepo.updatePDSFile(2L, "newfile.jpg");
		log.info("결과" + result);
	}
	
	//@Test
	void updateFile() {
		PDSFile file = frepo.findById(1L).orElse(null);
		
		if(file==null) {
			return;
		}
		
		file.setPdsfilename("수정파일명");
		
		frepo.save(file);
	}
	
	
	//@Test
	void fileCount2() {
		frepo.getFileCountByBoard().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
	//@Test
	void fileCount() {
		brepo.findAll().forEach(b->{
			log.info(b.getPname() + "-------->" + b.getFiles2().size());
		});
	}
	
	//@Test
	void fileUpdateByFname() {
		Long pid = 2L;
		PDSBoard board = brepo.findById(pid).orElse(null);
		
		if(board == null) {
			return;
		}
		
		frepo.findByPdsfilenameContaining("eye").forEach(f->{
			board.getFiles2().add(f);
		});
		
		
		brepo.save(board);
	}
	//@Test
	void fileUpdate() {
		Long fno = 102L;
		Long pid = 2L;
		
		//102번 첨부파일을 2번 board에 넣는다
		frepo.findById(fno).ifPresent(f->{
			brepo.findById(pid).ifPresent(b->{
				b.getFiles2().add(f);
				brepo.save(b);
			});
		});
	}
	//@Test
	void selectByBoard() {
		Long pid = 2L;
		brepo.findById(pid).ifPresent(board->{
			log.info("pname:" + board.getPname());
			log.info("pwriter:" + board.getPwriter());
			log.info("files2:" + board.getFiles2() + "건수..." + board.getFiles2().size());
			log.info("##########################################");
		});
	}
	
	//@Test
	void fileSelect() {
		frepo.findAll().forEach(b->log.info(b.toString()));
	}
	
	//@Test
	void boardSelect() {
		brepo.findAll().forEach(b->log.info(b.toString()));
	}
	
	//@Test
	void insert() {
		//Board(1) - File(n)
		List<PDSFile> flist = new ArrayList<>();
		IntStream.rangeClosed(1, 5).forEach(i->{
			PDSFile file = PDSFile.builder()
					.pdsfilename("eye-" + i)
					.build();
			//flist.add(file);
			frepo.save(file);
		});
		
		/*
		PDSBoard board = PDSBoard.builder()
				.pname("눈이와요")
				.pwriter("지현")
				.files2(flist)
				.build();
		
		brepo.save(board);
		*/
	}
}





