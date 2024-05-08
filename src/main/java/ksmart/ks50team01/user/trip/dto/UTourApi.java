package ksmart.ks50team01.user.trip.dto;

import lombok.Data;

@Data
public class UTourApi {


	private String title;
	private String address;
	private Long areaCode;
	private Long contentTypeId;
	private String firstImage;
	private double mapX;
	private double mapY;

}
