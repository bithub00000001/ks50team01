package ksmart.ks50team01.user.trip.service;

import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.trip.dto.UTripOption;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UTripPlanServiceImpl implements UTripPlanService{

	@Override
	public UTripOption parseDateRange(UTripOption uTripOption) throws DateTimeParseException {
		/*String dateRange = uTripOption.getDateRange();
		if (dateRange != null && !dateRange.isEmpty()){
			String[] dates = dateRange.split("-");
			LocalDate startDate = LocalDate.parse(dates[0]);
			LocalDate endDate = LocalDate.parse(dates[1]);

			uTripOption.setStartDate(startDate);
			uTripOption.setEndDate(endDate);
			log.info("Start date: {}", startDate);
			log.info("End date: {}" , endDate);

			uTripOption = calculateNumDate(uTripOption);
		}*/
		uTripOption = calculateNumDate(uTripOption);
		log.info("parseDateRange method called: {}", uTripOption);
		return uTripOption;
	}

	@Override
	public UTripOption calculateNumDate(UTripOption uTripOption) {
		if (uTripOption.getStartDate() != null && uTripOption.getEndDate() != null){
			long daysBetween = ChronoUnit.DAYS.between(uTripOption.getStartDate(), uTripOption.getEndDate()) + 1;
			uTripOption.setNumDate((int)daysBetween);
		}
		log.info("calculateNumDate method called: {}", uTripOption);
		return uTripOption;
	}

	@Override
	public UTripOption calculateTripDuration(UTripOption uTripOption) {
		if (uTripOption.getStartDate() != null && uTripOption.getEndDate() != null) {
			long daysBetween = ChronoUnit.DAYS.between(uTripOption.getStartDate(), uTripOption.getEndDate());
			String tripDuration = String.format("%d박 %d일",daysBetween, daysBetween + 1);
			uTripOption.setTripDuration(tripDuration);
		}else {
			uTripOption.setTripDuration("미정");
		}
		log.info("calculateTripDuration method called: {}", uTripOption);
		return uTripOption;
	}
}
