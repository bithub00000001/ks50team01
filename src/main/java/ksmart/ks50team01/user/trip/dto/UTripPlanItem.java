package ksmart.ks50team01.user.trip.dto;

import java.time.LocalDate;

import ksmart.ks50team01.platform.trip.dto.PTourDetail;
import lombok.Data;

@Data
public class UTripPlanItem {
	private int itemId;
	private String planUUID;
	private String dayNumber;
	private String contentId;
	private Integer orderNumber;
	private LocalDate date;
	private String regDate;

	// contentId에 일치하는 필드 가져오도록
	private PTourDetail tourDetail;
}
