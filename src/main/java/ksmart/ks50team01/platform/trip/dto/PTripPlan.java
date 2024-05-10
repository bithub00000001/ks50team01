package ksmart.ks50team01.platform.trip.dto;

import lombok.Data;

@Data
public class PTripPlan {
	private String planId;
	private Integer planNumber;
	private String memberId;
	private String planAddr;
	private String startDate;
	private String endDate;
	private String planDays;
	private String planTitle;
	private String planAttend;
	private Integer budgetPerAttend;
	private Integer totalAvailBudget;
	private Integer totalPlanBudget;
	private String planStatus;
	private boolean isShare;
	private String isShareString;
	private String regDate;
	private String modDate;

}
