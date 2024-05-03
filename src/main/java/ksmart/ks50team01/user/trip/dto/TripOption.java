package ksmart.ks50team01.user.trip.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TripOption {

    private String region;
    private String category;
    private String optionName;

    private String tripTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numPeople;
    private String memberType;
    private String dateRange;

    private int numDate;
}
