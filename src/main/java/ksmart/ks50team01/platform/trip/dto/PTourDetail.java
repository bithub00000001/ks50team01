package ksmart.ks50team01.platform.trip.dto;

import lombok.Data;

@Data
public class PTourDetail {

	private String detailNum;
	private String contentId;
	private String contentTypeId;
	private String contentTypeName;
	private String areaCode;
	private String areaName;
	private String sigunguCode;
	private String sigunguName;
	private String title;
	private String tel;
	private String telName;
	private String homepage;
	private String firstImage;
	private String secondImage;
	private String overview;
	private String mainAddr;
	private String detailAddr;
	private String zipcode;
	private Double longitude; // 경도 (x좌표)
	private Double latitude; // 위도 (y좌표)
	private String mapLevel;
	private String regDate;
	private String modDate;
}
