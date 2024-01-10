package com.shinhan.sbproject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository.DeptRepository2;
import com.shinhan.firstzone.repository.EmpRepository2;
import com.shinhan.sbproject.vo2.DeptDTO;
import com.shinhan.sbproject.vo2.EmpDTO;

import lombok.extern.log4j.Log4j;

@SpringBootTest

public class OneToManyTest2 {
	@Autowired
	EmpRepository2 empRepo;
	
	@Autowired
	DeptRepository2 deptRepo;
	


	
	//@Test
	void empUpdate() {
		//40번 직원은 100번 부서로 배치
		//50번 직원은 신규부서 200번에 배치
		
		EmpDTO emp40 = empRepo.findById(40L).orElse(null);
		EmpDTO emp50 = empRepo.findById(50L).orElse(null); 
		
		if(emp40 != null) {
			deptRepo.findById(100L).ifPresent(d->{
				d.getEmplist().add(emp40);
				deptRepo.save(d);
			});
		}
		
		List<EmpDTO> elist = new ArrayList<>();
		elist.add(emp50);
		if(emp50 != null) {
			DeptDTO newDept = DeptDTO.builder()
					.deptId(200L)
					.deptName("TF 팀")
					.emplist(elist)
					.build();
			deptRepo.save(newDept);
		}
		
	}
	
	//@Test
	void insertEmp() {
		EmpDTO emp1 = EmpDTO.builder().empId(40L).empName("경력사원").build();
		EmpDTO emp2 = EmpDTO.builder().empId(50L).empName("경력사원").build();
		empRepo.save(emp1);
		empRepo.save(emp2);
	}
	
	//@Test
	void insertDeptEmp() {
		List<EmpDTO> elist = new ArrayList<>();
		
		for(int i=1; i<=3; ++i) {
			EmpDTO emp = EmpDTO.builder()
					.empId(i*10L)
					.empName("우수사원" + i)
					.build();
			elist.add(emp);
		}
		
		
		/*
		 * "emp_id" 칼럼(해당 릴레이션 "tbl_dept")의 null 값이 not null 제약조건을 위반했습니다.
		 * */
		DeptDTO dept = DeptDTO.builder()
				.deptId(100L)
				.deptName("개발부")
				.emplist(elist)
				.build();
		
		deptRepo.save(dept);
	}
	
}
