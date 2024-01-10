package com.shinhan.firstzone.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo2.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long>{
	@Modifying 
	@Query("UPDATE PDSFile f set f. pdsfilename = ?2 WHERE f.fno = ?1 ") 
	int updatePDSFile(Long fno, String newFileName); 

}
