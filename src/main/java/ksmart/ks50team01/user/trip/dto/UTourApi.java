package ksmart.ks50team01.user.trip.dto;

import lombok.Data;

@Data
public class UTourApi {

	private String contentId;
	private String title;
	private String address1;
	private String address2;
	private Integer areaCode;
	private Integer sigunguCode;
	private Integer contentTypeId;
	private String firstImage;
	private double mapX;
	private double mapY;
	private String tel;

}
