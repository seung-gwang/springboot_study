package com.shinhan.sbproject.vo3;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString@Builder
@Entity@Table(name="tbl_freeboards")
public class FreeBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long bno;
	
	@NonNull
	@Column(nullable = false)
	String title;
	
	
	String writer;
	String content;
	
	@CreationTimestamp
	Timestamp regdate;
	
	@UpdateTimestamp
	Timestamp updatedate;
	
	//연관관계 설정
	
	@OneToMany(mappedBy="board", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	List<FreeBoardReply> replies;
	
	
}
