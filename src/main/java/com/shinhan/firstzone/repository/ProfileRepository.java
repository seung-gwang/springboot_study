package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo.MemberDTO;
import com.shinhan.sbproject.vo.ProfileDTO;

public interface ProfileRepository extends CrudRepository<ProfileDTO, Long>, QuerydslPredicateExecutor<ProfileDTO>{
	List<ProfileDTO> findByMember(MemberDTO member);
	List<ProfileDTO> findByMemberAndCurrentYnIsTrue(MemberDTO member);
	List<ProfileDTO> findByCurrentYnIsTrue();
	
	@Query(value="SELECT m.mname, p.fname FROM tbl_members m left outer join tbl_profile p on(m.mid = p.member_mid)", nativeQuery = true)
	List<Object[]> selectByOuterJoin();
	
	@Query(value="SELECT m.mname, p.fname FROM tbl_members m join tbl_profile p on(m.mid = p.member_mid)", nativeQuery = true)
	List<Object[]> selectByInnerJoin();
}
