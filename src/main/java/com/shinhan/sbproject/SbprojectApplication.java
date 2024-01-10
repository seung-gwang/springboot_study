package com.shinhan.sbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = {"com.shinhan"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.shinhan"})
/*
 * 기본 패키지 하위는 추가 코드 없음
 * 그 외의 폴더면 @ComponentScan으로 추가 함
 * */
public class SbprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbprojectApplication.class, args);
	}

}
