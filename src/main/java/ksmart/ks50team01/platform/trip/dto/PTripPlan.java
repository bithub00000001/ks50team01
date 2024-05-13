package ksmart.ks50team01.platform.trip.dto;

import java.util.List;

import lombok.Data;

@Data
public class PTripPlan {
	private String planId;
	private Integer planNumber;
	private String memberId;
	private String planRegionSmallCate;
	private String planAddr;
	private String startDate;
	private String endDate;
	private String planDays;
	private String planTitle;
	private String planAttend;
	private Integer budgetPerAttend;
	private Integer totalAvailBudget;
	private Integer totalPlanBudget;
	private Integer diffBudget;
	private String planStatus;
	private String isShare;
	private String isShareString;
	private String regDate;
	private String modDate;
	private String sessionId;
	private Status status;

	public enum Status {
		작성중, 작성_완료, 계획_완료;

		public String getEnumValue() {
			return this.name().replace("_", " ");
		}

		public static Status getEnumFromEnumValue(String enumValue) {
			String enumName = enumValue.replace(' ','_');
			return Status.valueOf(enumName);
		}
	}
	public boolean isShared() {
		return "Y".equals(isShare);
	}

}
