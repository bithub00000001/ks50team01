package ksmart.ks50team01.platform.ranking.dto;

import lombok.Data;

@Data
public class UserRanking {
	private String userRankingId;
	private String userRankingNum;
	private String userRankingRegId;
	private String partnerBusinessCode;
	private String tripLctgryCode;
	private String destinationType;
	private String destinationCId;
	private String destinationTitle;
	private String destinationAddr;
	private String destinationImg;
	private String destinationDetail;
	private int userRank;
	private String reviewStatsStart;
	private String reviewStatsEnd;
	private String platformAdStart;
	private String platformAdEnd;
	private String activate;
	private String regDate;
	private String modifyDate;
	private String autoUserId;
}
