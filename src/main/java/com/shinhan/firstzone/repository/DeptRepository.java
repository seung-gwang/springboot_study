package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo.DeptVO;

public interface DeptRepository extends CrudRepository<DeptVO, Integer>{
	
	/*
	 * 특정 managerId가 관리하는 부서들의 부서 이름 뒤에 "OK"라는 문자를 추가(수정)
	 * */
	List<DeptVO> findByManagerId(int managerId);
}
