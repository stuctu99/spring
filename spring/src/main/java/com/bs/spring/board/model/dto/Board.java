package com.bs.spring.board.model.dto;

import java.sql.Date;
import java.util.List;

import com.bs.spring.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

	private int boardNo;
	private String boardTitle;
//	private String boardWriter;
	private Member writer;
	private String boardContent;
	private Date boardDate;
	private int boardReadCount;
	
	private List<Attachment> files;
	
}
