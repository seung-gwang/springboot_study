package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.sbproject.vo3.FreeBoard;
import com.shinhan.sbproject.vo3.FreeBoardReply;

public interface FreeBoardReplyRepository extends 
CrudRepository<FreeBoardReply, Long>
,PagingAndSortingRepository<FreeBoardReply, Long>
, QuerydslPredicateExecutor<FreeBoardReply> {

	//기본 제공 메서드 사용
	
	//규칙에 맞는 함수 추상 메서드 선언하기 (구현은 알아서 해줌)
	List<FreeBoardReply> findByBoard(FreeBoard board);
	
	
	
	
}
