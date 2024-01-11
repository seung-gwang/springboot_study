package com.shinhan.sbproject.vo3;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@ToString(exclude="board")
@Builder
@Entity@Table(name="tbl_free_replies")
public class FreeBoardReply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long rno;
	String reply;
	String replyer;
	
	@CreationTimestamp
	Timestamp regdate;
	
	@UpdateTimestamp
	Timestamp updatedate;
	
	@ManyToOne //FK : board_bno
	@JsonIgnore //json 생성시 상호참조 제거 -> 무한 loop에 의한 stack overflow 방지
	FreeBoard board;
}
