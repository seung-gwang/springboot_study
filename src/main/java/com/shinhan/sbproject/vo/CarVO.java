package com.shinhan.sbproject.vo;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Builder;


@Entity
@Table(name = "tbl_car")
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
public class CarVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long carNum;
	String model;
	int price;
}
