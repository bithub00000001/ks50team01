package ksmart.ks50team01.user.trip.dto;

import java.util.List;

import lombok.Data;

@Data
public class UDayInfo {
	private int day;
	private List<ULocationDistanceInfo> locations;
}
