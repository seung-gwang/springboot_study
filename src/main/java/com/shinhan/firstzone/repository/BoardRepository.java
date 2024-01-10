package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.shinhan.sbproject.vo.BoardVO;

public interface BoardRepository extends PagingAndSortingRepository<BoardVO, Long>, 
CrudRepository<BoardVO, Long>,
QuerydslPredicateExecutor<BoardVO>
{
	
	List<BoardVO> findByWriter(String writer);
	List<BoardVO> findByContent(String content);
	List<BoardVO> findByContentLike(String content);
	List<BoardVO> findByContentContaining(String content);
	List<BoardVO> findByBnoBetweenAndWriterNotContaining(int left, int right, String writerExclude);
	List<BoardVO> findByBnoBetweenAndContentContainingAndWriterNotContainingOrderByUpdateDate(int left, int right, String contentInclude, String writerExclude);
	int countByWriter(String writer);
	
	//Paging, Sort 추가
	List<BoardVO> findByBnoGreaterThan(Long bno, Pageable paging);
	
	Page<BoardVO> findByBnoBetween(Long left, Long right, Pageable paging);
	
	//JPA QUERY LANGUAGE : 규칙에 맞는 함수 정의가 한계가 있을 때 해결법.
	@Query("select b from BoardVO b where b.title like %?2% and b.writer like %?3% and b.bno > ?1 order by bno desc")
	List<BoardVO> selectByTitleAndWriter(Long bno, String title, String writer);
	
	@Query(value="select * from t_boards "
			+ "where title like %:title% "
			+ "and writer like %:writer% "
			+ "and bno > :bno order by bno desc", nativeQuery = true)
	List<BoardVO> selectByTitleAndWriterNative(@Param("bno")Long bno, @Param("title")String title, @Param("writer")String writer);
	
	@Query(value="select title, writer, content from t_boards "
			+ "where title like %:title% "
			+ "and writer like %:writer% "
			+ "and bno > :bno order by bno desc", nativeQuery = true)
	List<Object[]> selectTitleWriterContentByTitleAndWriter(@Param("bno")Long bno, @Param("title")String title, @Param("writer")String writer);

}
