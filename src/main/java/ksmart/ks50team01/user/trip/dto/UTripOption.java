package ksmart.ks50team01.user.trip.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UTripOption {

    private String region;
    private String category;
    private String optionName;

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

    private Integer numDate;

    private String tripDuration;
}
