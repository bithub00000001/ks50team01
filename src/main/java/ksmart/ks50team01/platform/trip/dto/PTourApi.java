package ksmart.ks50team01.platform.trip.dto;

import lombok.Data;

@Data
public class PTourApi {
	private Long areaId;
	private String areaCode;
	private String areaName;
	private String areaRNum;
	private String regDate;
	private String modDate;
	private String areaNum;

	private Long sigunguId;
	private String sigunguCode;
	private String sigunguName;
	private Long sigunguRNum;
	private String sigunNum;

	private String destinationTitle;
	private String destinationFirstAddress;
	private String destinationSecondAddress;

	private Double destinationLatitude;
	private Double destinationLongitude;
	private String destinationTelNum;
	private String destinationFirstImageLink;
	private String destinationSecondImageLink;
	private String destinationContentId;
	private String destinationContents;

}
