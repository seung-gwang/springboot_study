package com.shinhan.sbproject.vo;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode
@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_departments")
public class DeptVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int departmentId;
	
	@Column (length = 50)
	String departmentName;
	
	
	int managerId;
	int locationId;
	
	@CreationTimestamp
	Timestamp regDate;
	
	@UpdateTimestamp
	Timestamp updateDate;
	
}
