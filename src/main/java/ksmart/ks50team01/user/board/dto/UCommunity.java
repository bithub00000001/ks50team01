package ksmart.ks50team01.user.board.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class UCommunity {
	
	@Id
	private Integer id;
	private String title;
	private String content;

}
