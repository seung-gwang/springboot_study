package com.shinhan.sbproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository.composite.UserCellPhoneRepository;
import com.shinhan.firstzone.repository.composite.UserCellPhoneVO2Repository;
import com.shinhan.firstzone.repository.composite.UserVO3Repository;
import com.shinhan.firstzone.repository.composite.UserVORepository;
import com.shinhan.sbproject.vo4.UserCellPhoneVO;
import com.shinhan.sbproject.vo4.UserCellPhoneVO2;
import com.shinhan.sbproject.vo4.UserCellPhoneVO3;
import com.shinhan.sbproject.vo4.UserVO;
import com.shinhan.sbproject.vo4.UserVO2;
import com.shinhan.sbproject.vo4.UserVO3;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OneToOneTest {
	@Autowired
	UserCellPhoneRepository pRepo;
	
	@Autowired
	UserVORepository uRepo;
	
	@Autowired
	UserCellPhoneVO2Repository p2Repo;
	
	@Autowired
	UserVO3Repository urepo3;
	
	@Test
	void f5() {
		UserCellPhoneVO3 p = UserCellPhoneVO3.builder()
				.phoneNumber("12345")
				.model("DDD")
				.build();
		UserVO3 user = UserVO3.builder().userid("First")
				.username("정우")
				.phone(p)
				.build();
		
		p.setUser(user);
		urepo3.save(user);
	}
	
	//@Test
	void f3() {
		UserVO2 user = UserVO2.builder()
				.userid("jj")
				.username("찐")
				.build();
		
		
		UserCellPhoneVO2 phone = UserCellPhoneVO2.builder()
				.phoneNumber("010-1234-5555")
				.model("galaxy 21")
				.user(user)
				.build(); 
		
		p2Repo.save(phone);
	}
	
	
	//@Test
	void f2() {
		uRepo.findById("zzilre").ifPresent(u->{
			log.info(u.toString());
		});
	}
	
	//@Test
	void f1() {
		UserCellPhoneVO phone = UserCellPhoneVO.builder()
				.phoneNumber("010-1234-5678")
				.model("galaxy 21")
				.build(); 
		
		UserCellPhoneVO newPhone = pRepo.save(phone);
		
		
		UserVO user = UserVO.builder()
				.userid("zzilre")
				.username("진례")
				.phone(newPhone)
				.build();
		
		uRepo.save(user);
	}
}
