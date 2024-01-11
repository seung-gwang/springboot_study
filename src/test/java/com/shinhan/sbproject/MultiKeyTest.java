package com.shinhan.sbproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository.composite.MultiKeyAUsingRepository;
import com.shinhan.firstzone.repository.composite.MultiKeyBRepository;
import com.shinhan.sbproject.vo4.MultiKeyAUsing;
import com.shinhan.sbproject.vo4.MultiKeyB;
import com.shinhan.sbproject.vo4.MultiKeyBDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MultiKeyTest {
	@Autowired
	MultiKeyAUsingRepository repo1;
	
	
	@Autowired
	MultiKeyBRepository repoB;
	
	@Test
	void f2() {
		MultiKeyB key = MultiKeyB.builder()
				.id1(1)
				.id2(2)
				.build();
		
		MultiKeyBDTO bdto = MultiKeyBDTO.builder()
				.id(key)
				.userName("정우")
				.address("과천")
				.build();
		
		repoB.save(bdto);
	}
	
	//@Test
	void f1() {
		MultiKeyAUsing multi = MultiKeyAUsing.builder()
				.id1(100)
				.id2(200)
				.userName("용수")
				.address("파주")
				.build();
		repo1.save(multi);
	}
	
}
