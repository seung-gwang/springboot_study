package com.shinhan.sbproject.vo5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
@Controller
public class DeptEmpController {
	@Autowired
	EmpRepository eRepo;
	
	@Autowired
	DeptRepository dRepo;
	
	@GetMapping("/deptemp")
	public void f1(Model model) {
		model.addAttribute("dlist", (List<DeptVO>)dRepo.findAll());
		model.addAttribute("elist", (List<EmpVO>)eRepo.findAll());
	}
	
	@GetMapping("/emp")
	public List<EmpVO> f2() {
		return (List<EmpVO>)eRepo.findAll();
	}
	
	
}
