package com.shinhan.sbproject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository.DeptRepository;
import com.shinhan.sbproject.vo.DeptVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DeptTest {
	
	@Autowired
	DeptRepository drepo;
	
	//@Test
	void create() {
		IntStream.rangeClosed(1, 10).forEach(
				i->{
					DeptVO dept = DeptVO.builder()
							.departmentName("deptName"+i)
							.locationId(i+2)
							.managerId(i/20)
							.build();
					
					DeptVO newDept = drepo.save(dept);
					
					assert dept.equals(newDept);
				}
				);
	}
	
	//@Test
	void findById() {
		drepo.findById(1).ifPresentOrElse(
				b->{log.info("있습니다.");}, 
				()->{log.info("없네요.ㅋ");});
	}
	
	
	//@Test
	void findAll() {
		List<DeptVO> depts = new ArrayList<>();
		drepo.findAll().forEach(
				d->{depts.add(d);}
		);
		
		long cnt = drepo.count();
		assert cnt == depts.size();
		log.info(cnt+"");
	}
	
	//@Test
	void update() {
		Integer searchId = 2;
		drepo.findById(searchId).ifPresent(d-> {
			int newDeptId = d.getDepartmentId() * 2 - 1;
			d.setDepartmentId(newDeptId);
			DeptVO newDept = drepo.save(d);
			
			assert !newDept.equals(d);
			assert newDeptId == newDept.getDepartmentId();
		});			
	}
	
	/*
	 * 특정 managerId가 관리하는 부서들의 부서 이름 뒤에 "OK"라는 문자를 추가(수정)
	 * */
	@Test
	void f9() {
		drepo.findByManagerId(7).forEach(dept->{
			String dname = dept.getDepartmentName().concat("OK");
			dept.setDepartmentName(dname);
			drepo.save(dept);
		});
	}
	
	void delete() {
		
	}
}
