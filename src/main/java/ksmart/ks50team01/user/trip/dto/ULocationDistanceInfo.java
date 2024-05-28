package ksmart.ks50team01.user.trip.dto;

import lombok.Data;

@Data
public class ULocationDistanceInfo {
	private String startContentId;
	private String endContentId;
	private String contentId;
	private int order;
	private double startMapX;
	private double startMapY;
	private double endMapX;
	private double endMapY;
	private double distance;
	private int duration;

}
