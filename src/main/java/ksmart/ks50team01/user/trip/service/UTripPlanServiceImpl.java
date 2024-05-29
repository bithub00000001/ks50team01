package ksmart.ks50team01.user.trip.service;

import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.trip.dto.TMapApiResponse;
import ksmart.ks50team01.user.trip.dto.UDayDistanceResult;
import ksmart.ks50team01.user.trip.dto.UDayInfo;
import ksmart.ks50team01.user.trip.dto.ULocationDistanceInfo;
import ksmart.ks50team01.user.trip.dto.UTripOption;
import ksmart.ks50team01.user.trip.enums.UTripContent;
import ksmart.ks50team01.user.trip.mapper.UTripPlanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UTripPlanServiceImpl implements UTripPlanService {

	private final UTripPlanMapper utripPlanMapper;
	private final UTmapApiService utmapApiService;

	/**
	 * 데이트 레인지를 받아 출발 날짜와 도착 날짜로 분리하는 메서드(현재는 출발 날짜와 도착 날짜를 클라이언트에서 처리함)
	 * @param uTripOption 여행 옵션 DTO
	 * @return 출발 날짜와 도착 날짜, 일수를 입력받은 여행 옵션 DTO
	 * @throws DateTimeParseException
	 */
	@Override
	public UTripOption parseDateRange(UTripOption uTripOption) throws DateTimeParseException {
		uTripOption = calculateNumDate(uTripOption);
		log.info("parseDateRange method called: {}", uTripOption);
		return uTripOption;
	}

	/**
	 * 데이트 레인지가 존재하면 출발 날짜와 도착 날짜 사이의 일수를 계산하는 메서드
	 * @param uTripOption 여행 옵션 DTO
	 * @return 일수를 계산에 입력받은 여행 옵션 DTO
	 */
	@Override
	public UTripOption calculateNumDate(UTripOption uTripOption) {
		if (uTripOption.getStartDate() != null && uTripOption.getEndDate() != null) {
			long daysBetween = ChronoUnit.DAYS.between(uTripOption.getStartDate(), uTripOption.getEndDate()) + 1;
			uTripOption.setNumDate((int)daysBetween);
		}
		log.info("calculateNumDate method called: {}", uTripOption);
		return uTripOption;
	}

	/**
	 * 출발 날짜와 도착 날짜를 입력 받아 몇박 몇일 혹은 미정을 반환 메서드
	 * @param uTripOption 여행 옵션 DTO
	 * @return TripDuration에 일정을 입력 받은 여행 옵션 DTO
	 */
	@Override
	public UTripOption calculateTripDuration(UTripOption uTripOption) {
		if (uTripOption.getStartDate() != null && uTripOption.getEndDate() != null) {
			long daysBetween = ChronoUnit.DAYS.between(uTripOption.getStartDate(), uTripOption.getEndDate());
			String tripDuration = String.format("%d박 %d일", daysBetween, daysBetween + 1);
			uTripOption.setTripDuration(tripDuration);
		} else {
			uTripOption.setTripDuration("미정");
		}
		log.info("calculateTripDuration method called: {}", uTripOption);
		return uTripOption;
	}

	/**
	 * 여행 정보 혹은 여행 상세 정보를 조회하고 카카오 맵 API에서 사용할 수 있도록 객체로 반환하는 메서드
	 * @param content 컨텐트 ID 혹은 관광 타입
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public Map<String, Object> getTourInfoObject(String content) throws JsonProcessingException {
		List<String> concatObject = utripPlanMapper.getTourInfoObject(UTripContent.getUTripContentByLength(content),
			content);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> contentList = objectMapper.readValue(concatObject.toString(),
			new TypeReference<List<Map<String, Object>>>() {
			});
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("positions", contentList);
		return paraMap;
	}

	/**
	 * 회원 중 일반 회원의 목록을 조회하는 메서드
	 * @return Login DTO 의 List
	 */
	@Override
	public List<Login> getUserMembers() {
		return utripPlanMapper.getUserMembers();
	}

	/**
	 * 회원 중 일반 회원이며 nickname과 일치하는 목록을 조회하는 메서드
	 * @param nickname 회원의 닉네임
	 * @return Login DTO 의 List
	 */
	@Override
	public List<Login> searchUserMembers(String nickname) {
		return utripPlanMapper.searchUserMembers(nickname);
	}

	@Override
	public Map<String, Object> calculateDistanceDuration(List<UDayInfo> days) {
		Map<String, Object> results = new HashMap<>();

		for (UDayInfo uDayInfo : days) {
			UDayDistanceResult uDayResult = new UDayDistanceResult();
			uDayResult.setDay(String.valueOf(uDayInfo.getDay()));
			List<ULocationDistanceInfo> locationResults = new ArrayList<>();

			for (int i = 0; i < uDayInfo.getLocations().size() - 1; i++) {
				ULocationDistanceInfo startPoint = uDayInfo.getLocations().get(i);
				ULocationDistanceInfo endPoint = uDayInfo.getLocations().get(i + 1);



				Map<String, Double> startXY = utripPlanMapper.getMapXY(startPoint.getContentId());
				Map<String, Double> endXY = utripPlanMapper.getMapXY(endPoint.getContentId());

				startPoint.setStartMapX(startXY.get("longitude"));
				startPoint.setStartMapY(startXY.get("latitude"));
				endPoint.setEndMapX(endXY.get("longitude"));
				endPoint.setEndMapY(endXY.get("latitude"));

				TMapApiResponse apiResponse = utmapApiService.fetchFromApi(
					startPoint.getContentId(), endPoint.getContentId(),
					startPoint.getStartMapX(), startPoint.getStartMapY(),
					endPoint.getEndMapX(), endPoint.getEndMapY()
				).block();
				log.info("apiResponse: {}", apiResponse);

				ULocationDistanceInfo locationResult = setLocationDistanceInfo(startPoint, endPoint,
					apiResponse);

				locationResults.add(locationResult);
			}
			uDayResult.setLocations(locationResults);
			results.put(String.valueOf(uDayInfo.getDay()), uDayResult);
		}
		return results;
	}

	private static ULocationDistanceInfo setLocationDistanceInfo(ULocationDistanceInfo startPoint,
		ULocationDistanceInfo endPoint, TMapApiResponse apiResponse) {
		ULocationDistanceInfo locationResult = new ULocationDistanceInfo();
		locationResult.setStartContentId(startPoint.getContentId());
		locationResult.setStartMapX(startPoint.getStartMapX());
		locationResult.setStartMapY(startPoint.getStartMapY());
		locationResult.setEndContentId(endPoint.getContentId());
		locationResult.setEndMapX(endPoint.getEndMapX());
		locationResult.setEndMapY(endPoint.getEndMapY());
		locationResult.setDistance(Objects.requireNonNull(apiResponse).getDistance());
		locationResult.setDuration(apiResponse.getDuration());
		return locationResult;
	}
}

