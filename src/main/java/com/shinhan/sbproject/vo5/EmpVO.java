package com.shinhan.sbproject.vo5;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name="employees")
@Entity
public class EmpVO {
	@Id
	private Integer employeeId;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private Double salary; 
	
	@Column(nullable = true)
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Double commissionPct;
	
	@Column(nullable = true)
	private Integer managerId;
	
	@Column(nullable = true)
	private Integer departmentId;
	 
	 
	 
    
	 
}
