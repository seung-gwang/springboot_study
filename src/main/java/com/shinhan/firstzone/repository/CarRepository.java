package com.shinhan.firstzone.repository;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo.CarVO;

/*
 * jpa가 알아서 구현 객체를 만들어준다
 * */
public interface CarRepository extends CrudRepository<CarVO, Long>{

}
