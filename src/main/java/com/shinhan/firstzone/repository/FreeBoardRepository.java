package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.shinhan.sbproject.vo3.FreeBoard;

public interface FreeBoardRepository extends 
CrudRepository<FreeBoard, Long>
,PagingAndSortingRepository<FreeBoard, Long>
, QuerydslPredicateExecutor<FreeBoard> 
{
	List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);
	List<FreeBoard> findByBnoBetween(Long left, Long right, Pageable page);
	List<FreeBoard> findByWriter(String writer, Pageable page);
	
	
	/*
	 * Title이 특정 문자를 포함하는 board 얻기
	 * sort
	 * 특정 칼럼만 SELECT
	 * */
	@Query("select free.bno, free.title, free.writer from FreeBoard free "
			+ "where title like %?1% "
			+ "order by free.bno desc")
	List<Object[]>selectByTitle(String title);
	
	/* 위보다는
	 * 아래처럼 하는 것을 권장*/
	@Query("select free.bno, free.title, free.writer from #{#entityName} free "
			+ "where title like %:title% "
			+ "order by free.bno desc")
	List<Object[]>selectByTitle2(@Param("title")String title);
	
	@Query(nativeQuery = true, 
			value = "select free.bno, free.title, free.writer from tbl_freeboards free "
			+ "where title like concat('%', :title, '%') "
			+ "order by free.bno desc")
	List<Object[]>selectByTitle3(@Param("title")String title);
}
