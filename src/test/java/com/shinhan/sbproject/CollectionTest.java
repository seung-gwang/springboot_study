package com.shinhan.sbproject;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository.EnumTypeRepository;
import com.shinhan.sbproject.vo4.EnumTypeVO;
import com.shinhan.sbproject.vo4.MemberRoleEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CollectionTest {
	@Autowired
	EnumTypeRepository repo;
	
	@Test
	void f1() {
		Set<MemberRoleEnum> role = new HashSet<>();
		
		role.add(MemberRoleEnum.USER);
		role.add(MemberRoleEnum.ADMIN);
		role.add(MemberRoleEnum.MANAGER);
		
		EnumTypeVO vo = EnumTypeVO.builder()
				.mid("ju")
				.mname("주영")
				.mpassword("1234")
				
				.mrole(role)
				.build();
		
		repo.save(vo);
	}
}
