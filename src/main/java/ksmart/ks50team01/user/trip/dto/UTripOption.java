package ksmart.ks50team01.user.trip.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UTripOption {

    private String region;
    private String category;
    private String optionName;

    private String tripTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Integer numPeople = 1;
    private String memberType;

    private Integer numDate;
    private Integer numVirtualMember;
    private Integer numRealMember;

    private String tripDuration;
}
