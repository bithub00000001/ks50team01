package ksmart.ks50team01.user.trip.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import ksmart.ks50team01.platform.trip.dto.PTourDetail;
import lombok.Data;

@Data
public class UTripOption {

    private String region;
    private String category;
    private String optionName;

    private Integer tripNum;
    private Integer tripId;
    private String regDate;
    private String modDate;
    private String inProgress;
    private String description;

    private String dayDiff;

    private String sessionId;

    private String tripTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Integer numPeople = 1;

    private List<String> virtualMembers = new ArrayList<>();
    private List<String> invitedMembers = new ArrayList<>();
    private Integer numVirtualMembers;
    private Integer numRealMembers;

    // 여행 계획을 구분하기 위해 uuid 생성
    private String planUUID;

    private Integer numDate;

    private String tripDuration;

    // TRIP_PLAN_ITEMS_NEW 테이블 정보
    private List<UTripPlanItem> tripItems;

    // TOUR_DETAIL_FROM_API 테이블 정보
    private List<PTourDetail> tourDetails;

    // description 필드를 구성하는 메서드 추가
    public String getDescription() {
        return String.format("출발 날짜: %s, 도착 날짜: %s, 상태: %s",
            startDate != null ? startDate.toString() : "미정",
            endDate != null ? endDate.toString() : "미정",
            inProgress != null ? inProgress : "작성중");
    }

    private List<DayPlan> dayPlans = new ArrayList<>();

    @Data
    public static class DayPlan {
        private Integer dayNumber;
        private LocalDate date;
        private List<UTripPlanItem> items = new ArrayList<>();

        public void setStartDateAndDayNumber(LocalDate startDate, Integer dayNumber) {
            this.dayNumber = dayNumber;
            this.date = startDate.plusDays(dayNumber - 1);
        }
    }

}
