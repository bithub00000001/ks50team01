package ksmart.ks50team01.user.trip.dto;

import java.util.List;

import lombok.Data;

@Data
public class UDayDistanceResult {
	private String day;
	private List<ULocationDistanceInfo> locations;
}
