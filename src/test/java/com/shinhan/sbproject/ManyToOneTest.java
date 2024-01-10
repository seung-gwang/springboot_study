package com.shinhan.sbproject;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.firstzone.repository.MemberRepository;
import com.shinhan.firstzone.repository.ProfileRepository;
import com.shinhan.sbproject.vo.MemberDTO;
import com.shinhan.sbproject.vo.MemberRole;
import com.shinhan.sbproject.vo.ProfileDTO;
import com.shinhan.sbproject.vo.QProfileDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ManyToOneTest {
	@Autowired
	MemberRepository mrepo;
	
	@Autowired
	ProfileRepository prepo;
	
	
	@Test
	void jpqlTest() {
		String mid = "27921b9a";
		int cnt = mrepo.getProfileCountByMember(mid);
		
		log.info(mid + "의 프로파일 건수 : " + cnt);
		
		
		List<Object[]> result = mrepo.getProfileCountByMember();
		result.forEach(data->{
			log.info("member의 profile 건수:" + data[0] + "------" + data[1]);
		});
	}
	
	//@Test
	void selectByMember() {
		
		MemberDTO member = MemberDTO.builder().mid("27921b9a").build();
		List<ProfileDTO> plist = prepo.findByMember(member);
		plist.forEach(p->log.info(p.toString()));
		
	}
	
	//@Test
	void eagerLazyTest() {
		//prepo.findAll().forEach(p-> {assert p.getMember() == null;});
	}
	
	//@Test
	void Dynamic() {
		BooleanBuilder builder = new BooleanBuilder();
		QProfileDTO profile = QProfileDTO.profileDTO;
		builder.and(profile.pno.gt(5L));
		builder.and(profile.fname.like("%file%"));
		builder.and(profile.currentYn.eq(false));
		
		prepo.findAll(builder).forEach(pro->{
			log.info(pro.toString());
			assert !pro.isCurrentYn();
			assert pro.getPno() > 5;
			assert pro.getFname().contains("file");
		});
		
	}
	
	//@Test
	void join() {
		prepo.selectByInnerJoin().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
		
		prepo.selectByOuterJoin().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
	//@Test
	void memberProfile() {
		/*Join*/
		Long pno = 53L;
		prepo.findById(pno).ifPresent(pro->{
			log.info("Fname:" + pro.getFname());
			log.info("Mname:" + pro.getMember().getMname());
			assert(pro.getPno() == pno);
		});
		
		
	
		
		
		MemberDTO member = new MemberDTO();
		member.setMid("27921b9a");
		
		prepo.findByMemberAndCurrentYnIsTrue(member).forEach(pro->{
			log.info("Fname:" + pro.getFname());
			log.info("Mname:" + pro.getMember().getMname());
			assert pro.isCurrentYn();
		});
		
		prepo.findByCurrentYnIsTrue().forEach(pro->{
			assert pro.isCurrentYn();
		});
		
		prepo.findByMember(member).forEach(pro->{
			log.info("Fname:" + pro.getFname());
			log.info("Mname:" + pro.getMember().getMname());
			
		});
		
	}
	

	
	
	//@Test
	void profileInsert() {
		MemberDTO memberDTO = mrepo.findById("27921b9a").orElse(null);
		
		if(memberDTO == null) {
			return;
		}
		
		IntStream.rangeClosed(1,5).forEach(i->{
			ProfileDTO profile = ProfileDTO.builder()
					.fname("profile." + i)
					.currentYn(i==5)
					.member(memberDTO)
					.build();
			prepo.save(profile);
		});
	}
	
	//@Test
	void memberInsert() {
		//10명의 member를 저장함.
		/*
		 * 1~5 user
		 * 6~8 manager
		 * 9~10 admin
		 * */
		IntStream.range(1, 10).forEach(i->{
			MemberRole mRole = MemberRole.USER;
			
			if(i>=6 && i<=8) {
				mRole = MemberRole.MANAGER;
			}
			
			if(i > 8) {
				mRole = MemberRole.ADMIN;
			}
			
			UUID uuid = UUID.randomUUID();
			String id = uuid.toString().split("-")[0];
			
			
			MemberDTO member = MemberDTO.builder().mid(id).mname(uuid.toString()).mpassword(uuid.toString() + i).mrole(mRole).build();
			
			mrepo.save(member);
		});
	}

}
