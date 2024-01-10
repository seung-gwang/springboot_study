package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo.MemberDTO;

public interface MemberRepository extends CrudRepository<MemberDTO, String>{
	
	//멤버 정보를 이용해서 Profile의 정보를 알아내기 -> JPQL 이용 : JPA Query Language
	@Query("select count(p) "
			+ "from MemberDTO m join ProfileDTO p "
			+ "on (m = p.member) "
			+ "where m.mid = ?1")
	int getProfileCountByMember(String mid);
	
	@Query("select m.mid, count(p) "
			+ "from MemberDTO m left outer join ProfileDTO p "
			+ "on (m = p.member) "
			+ "group by m.mid")
	List<Object[]> getProfileCountByMember();
}
