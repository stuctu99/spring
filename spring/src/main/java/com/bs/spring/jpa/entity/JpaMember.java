package com.bs.spring.jpa.entity;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="jpa_member", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"memberNo","memberId"})}
) //연결되는 테이블에 대한 설정
//sequence를 생성해주는 어노테이션
//@SequenceGenerator -> DB에서 sequence객체를 생성
//	속성값으로 sequence에 대한 설정
//	name 시퀀스이름, sequenceName DB에서 시퀀스이름, initialValue 시작설정, allocationSize 증가값 
@SequenceGenerator(name="seq_jpamember_no",sequenceName = "seq_jpamember_no",
					initialValue = 1, allocationSize = 1)
//@JsonIdentityInfo -> Entity 객체를 가져올 때 양방향으로 일대다, 다대일 관계일 경우 json으로 반환하면
//무한루핑현상이 발생함.
public class JpaMember {

	@Id
	@GeneratedValue(generator = "seq_jpamember_no", strategy = GenerationType.SEQUENCE)
	private Long memberNo;
	private String memberId;
	private String memberpwd;
	
	private String memberName;
//	private double height;
	private Double height;
	
	
}
