package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.sbproject.vo2.PDSFile;

public interface PDSFileRepository 
extends CrudRepository<PDSFile, Long>, PagingAndSortingRepository<PDSFile, Long>{
	List<PDSFile> findByPdsfilenameContaining(String fileName);
	@Query(value = "select board.pname, count(file.fno)"
			+ " from tbl_pdsboard board left outer join tbl_pdsfiles file"
			+ " on (file.pdsno=board.pid) "
			+ "group by board.pname", nativeQuery = true)
	List<Object[]> getFileCountByBoard();
}
