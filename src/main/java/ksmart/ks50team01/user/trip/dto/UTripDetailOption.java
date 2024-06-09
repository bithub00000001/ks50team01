package ksmart.ks50team01.user.trip.dto;

import java.util.List;

import lombok.Data;

@Data
public class UTripDetailOption {
	private String planUUID;


	private List<UDayInfo> days;
}
