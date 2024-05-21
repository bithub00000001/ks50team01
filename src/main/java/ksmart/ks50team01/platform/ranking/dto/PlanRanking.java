package ksmart.ks50team01.platform.ranking.dto;

import lombok.Data;

@Data
public class PlanRanking {
	private String userTripPlanId;
	private String userTripPlan;
	private String pRankingRegId;
	private String tripPlanNum;
	private String rgnSctgryName;
	private int  tripPlanRank;
	private String reviewStatsStart;
	private String reviewStatsEnd;
	private String platformAdStart;
	private String platformAdEnd;
	private String activate;
	private String regDate;
	private String modifyDate;
}
