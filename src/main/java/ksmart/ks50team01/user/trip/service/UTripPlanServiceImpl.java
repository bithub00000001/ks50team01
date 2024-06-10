package ksmart.ks50team01.user.trip.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ksmart.ks50team01.platform.trip.dto.PTourDetail;
import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.trip.dto.TMapApiResponse;
import ksmart.ks50team01.user.trip.dto.UDayDistanceResult;
import ksmart.ks50team01.user.trip.dto.UDayInfo;
import ksmart.ks50team01.user.trip.dto.ULocationDistanceInfo;
import ksmart.ks50team01.user.trip.dto.UTripDetailOption;
import ksmart.ks50team01.user.trip.dto.UTripOption;
import ksmart.ks50team01.user.trip.dto.UTripPlanItem;
import ksmart.ks50team01.user.trip.enums.UTripContent;
import ksmart.ks50team01.user.trip.mapper.UTripPlanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UTripPlanServiceImpl implements UTripPlanService {

	private final UTripPlanMapper uTripPlanMapper;
	private final UTmapApiService uTmapApiService;

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
		List<String> concatObject = uTripPlanMapper.getTourInfoObject(UTripContent.getUTripContentByLength(content),
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
		return uTripPlanMapper.getUserMembers();
	}

	/**
	 * 회원 중 일반 회원이며 nickname과 일치하는 목록을 조회하는 메서드
	 * @param nickname 회원의 닉네임
	 * @return Login DTO 의 List
	 */
	@Override
	public List<Login> searchUserMembers(String nickname) {
		return uTripPlanMapper.searchUserMembers(nickname);
	}

	/**
	 * 여행 계획 세부 아이템의 위치를 받아 위경도를 설정하고 T map API에 요청해 도보 거리와 소요 시간을 요청하는 메서드
	 * 오류가 발생 했을 경우 Exception 관리를 위해 JSON으로 변환한 후 반환하는 메서드
	 * @param days 일자별로 여행 계획 세부 아이템의 정보가 담겨 있는 객체
	 * @return 일자별로 출발지, 목적지 사이의 거리, 소요시간, 도착지의 contentId가 담겨 있는 객
	 * @throws JsonProcessingException T map API 에러 핸들링 상황에서 에러 상황을 JSON으로 변환할 때 발생하는 에러 예외 처리
	 */
	@Override
	public Map<String, Object> calculateDistanceDuration(List<UDayInfo> days) throws JsonProcessingException {
		Map<String, Object> results = new HashMap<>();

		for (UDayInfo uDayInfo : days) {
			UDayDistanceResult uDayResult = new UDayDistanceResult();
			uDayResult.setDay(String.valueOf(uDayInfo.getDay()));
			List<ULocationDistanceInfo> locationResults = new ArrayList<>();

			for (int i = 0; i < uDayInfo.getLocations().size() - 1; i++) {
				// 출발지와 도착지를 생성
				ULocationDistanceInfo startPoint = uDayInfo.getLocations().get(i);
				ULocationDistanceInfo endPoint = uDayInfo.getLocations().get(i + 1);

				// 각 지점의 위도와 경도를 데이터베이스에서 조회
				Map<String, Object> startXY = uTripPlanMapper.getMapXY(startPoint.getContentId());
				Map<String, Object> endXY = uTripPlanMapper.getMapXY(endPoint.getContentId());

				// 시작점과 끝점의 위도, 경도 설정
				startPoint.setStartMapX((Double)startXY.get("longitude"));
				startPoint.setStartMapY((Double)startXY.get("latitude"));
				endPoint.setEndMapX((Double)endXY.get("longitude"));
				endPoint.setEndMapY((Double)endXY.get("latitude"));

				// T map API에서 오류가 발생할때 오류 처리를 JSON으로 보내기 위해 objectMapper 생성
				ObjectMapper objectMapper = new ObjectMapper();
				TMapApiResponse apiResponse;
				try {
					// 오류가 발생하지 않았을 경우
					// T map API를 호출하여 두 지점 사이의 거리와 소요 시간 계산
					apiResponse = uTmapApiService.fetchFromApi(
						startPoint.getContentId(), endPoint.getContentId(),
						startPoint.getStartMapX(), startPoint.getStartMapY(),
						endPoint.getEndMapX(), endPoint.getEndMapY()
					).block();
				}catch (WebClientResponseException exception) {
					// 오류가 발생해 API에서 오류 내용을 그대로 전달받아 객체로 변환
					log.info("exception.getResponseBodyAsString: {}", exception.getResponseBodyAsString());
					String exceptionMessage = exception.getResponseBodyAsString();
					Map<String, Object> exceptionMapper = objectMapper.readValue(exceptionMessage,new TypeReference<Map<String, Object>>() {});
					exceptionMapper.put("startTitle", startXY.get("title"));
					exceptionMapper.put("endTitle", endXY.get("title"));
					exceptionMapper.put("days", uDayInfo.getDay());
					return exceptionMapper;
				}

				ULocationDistanceInfo locationResult = setLocationDistanceInfo(startPoint, endPoint,
					apiResponse);

				locationResults.add(locationResult);
			}
			uDayResult.setLocations(locationResults);
			results.put(String.valueOf(uDayInfo.getDay()), uDayResult);
		}
		return results;
	}

	/**
	 * 임시 저장 상태로 현재의 여행 계획을 저장하는 메서드
	 * @param uTripOption 계획 제목, 출발 날짜, 도착 날짜, 일자, 초대 인원 등의 정보가 기록
	 * @return 정상 처리되었는지 판단
	 */
	@Override
	public int addTempPlanInfo(UTripOption uTripOption) {
		return uTripPlanMapper.addTempPlanInfo(uTripOption);
	}

	/**
	 * UUID를 기준으로 동일하면 업데이트, 다르다면 임시 저장 상태로 저장하는 메서드
	 * @param uTripOption 계획 제목, 출발 날짜, 도착 날짜, 일자, 초대 인원 등의 정보가 기록
	 */
	@Override
	public void saveOrUpdateTempPlanInfo(UTripOption uTripOption) {
		UTripOption existingOption = uTripPlanMapper.selectUTripOptionByUUID(uTripOption.getPlanUUID());
		if (existingOption == null) {
			uTripPlanMapper.addTempPlanInfo(uTripOption);
		} else {
			uTripPlanMapper.updateTempPlanInfo(uTripOption);
			deleteVirtualMembers(uTripOption.getPlanUUID());
			deleteRealMembers(uTripOption.getPlanUUID());
		}
		saveVirtualMembers(uTripOption.getPlanUUID(), uTripOption.getVirtualMembers());
		saveRealMembers(uTripOption.getPlanUUID(), uTripOption.getInvitedMembers());
	}

	@Override
	public List<UTripOption> getTempPlanList(String sessionId) {
		List<UTripOption> plans = uTripPlanMapper.selectTempPlanListBySessionId(sessionId);
		log.info("plans: {}", plans);

		if (plans.isEmpty()) return List.of();

		// 각 계획의 description 필드를 설정
		plans.forEach(plan -> {
			plan.setDescription(plan.getDescription());
			calculateTripDuration(plan);
			calculateAndSetDayDiff(plan);
		});

		return plans;
	}

	/**
	 * 클라이언트 페이지네이션 구현을 위한 데이터 가공
	 * @return
	 */
	@Override
	public List<Map<String, Object>> readMockData() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ClassPathResource classPathResource = new ClassPathResource("static/user/data/MOCK_DATA.json");
			InputStream inputStream = classPathResource.getInputStream();
			return objectMapper.readValue(inputStream,new TypeReference<List<Map<String, Object>>>(){});
		}catch (IOException exception) {
			log.error("MOCK_DATA.json 를 읽는데 실패했습니다.", exception);
			return new ArrayList<>();
		}
	}

	/**
	 * Tour API에서 DB에 저장한 여행지 상세 정보 목록을 조회하는 메서드
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getTourDataList() {
		return uTripPlanMapper.getTourDataList();
	}

	/**
	 * 여행 계획 상세 정보 저장 메서드
	 * @param uTripDetailOption 여행 계획 상세 정보 DTO
	 */
	@Override
	public int saveTripDetailInfo(UTripDetailOption uTripDetailOption) throws BadRequestException {
		String planUUID = uTripDetailOption.getPlanUUID();
		List<UDayInfo> dayInfos = uTripDetailOption.getDays();

		// 처리된 쿼리 확인
		int totalRowsAffected = 0;

		// 예외 처리 예시: 잘못된 요청 시 BadRequestException 던지기
		if (planUUID == null || planUUID.isEmpty()) {
			throw new BadRequestException("planUUID는 필수입니다.");
		}

		// planUUID가 존재하는지 확인
		if (uTripPlanMapper.existsByPlanUUID(planUUID)) {
			// 기존 데이터 삭제
			totalRowsAffected += uTripPlanMapper.deleteDetailInfo(planUUID);
		}

		// 새로운 데이터 저장
		for (UDayInfo dayInfo : dayInfos) {
			int dayNumber = dayInfo.getDay();
			List<ULocationDistanceInfo> locations = dayInfo.getLocations();

			for (ULocationDistanceInfo location : locations) {
				String contentId = location.getContentId();
				int order = location.getOrder();

				totalRowsAffected += uTripPlanMapper.insertDetailInfo(planUUID, dayNumber, contentId, order);
			}
		}
		return totalRowsAffected;
	}

	/**
	 * UUID와 일치하는 여행 계획 상세 정보 조회 메서드
	 * @param planUUID 여행 계획 UUID
	 * @return
	 */
	@Override
	public UTripOption getTripOptionByUUID(String planUUID) {
		UTripOption tripOption = uTripPlanMapper.getTripPlanByUUID(planUUID);
		if (tripOption != null) {
			// 실제 회원과 가상 회원 목록 조회
			List<String> realMembers = uTripPlanMapper.getRealMembersByPlanUUID(planUUID);
			List<String> virtualMembers = uTripPlanMapper.getVirtualMembersByPlanUUID(planUUID);
			tripOption.setInvitedMembers(realMembers);
			tripOption.setVirtualMembers(virtualMembers);

			// TRIP_PLAN_ITEMS_NEW 테이블에서 일정 항목 조회
			List<UTripPlanItem> tripItems = uTripPlanMapper.getTripItemsByUUID(planUUID);
			log.info("tripItems: {}", tripItems);

			// TOUR_DETAIL_FROM_API 테이블에서 관련 정보 조회
			List<String> contentIds = tripItems.stream()
				.map(UTripPlanItem::getContentId)
				.toList();
			List<PTourDetail> tourDetails = uTripPlanMapper.getPTourDetailByContentId(contentIds);

			// contentId를 키로 하는 Map 생성
			Map<String, PTourDetail> tourDetailMap = tourDetails.stream()
				.collect(Collectors.toMap(PTourDetail::getContentId, Function.identity()));

			addTripItemsForDates(tripOption, tripItems, tourDetailMap);
			tripOption.setTourDetails(tourDetails);

			return tripOption;
		}
		return null;
	}

	/**
	 * 각 일자별로 여행 계획 아이템을 설정하는 메서드
	 * @param tripOption 여행 계획
	 * @param tripItems 여행 계획 세부 아이템
	 * @param tourDetailMap 여행지 세부 정보
	 */
	private void addTripItemsForDates(UTripOption tripOption, List<UTripPlanItem> tripItems, Map<String, PTourDetail> tourDetailMap) {
		if (tripOption.getStartDate() == null || tripItems == null || tripItems.isEmpty()) {
			throw new IllegalArgumentException("Start date and trip items must be provided.");
		}

		tripOption.getDayPlans().clear();

		// 각 날짜별 DayPlan 생성
		for (UTripPlanItem item : tripItems) {
			int dayNumber = Integer.parseInt(item.getDayNumber());
			UTripOption.DayPlan dayPlan = tripOption.getDayPlans().stream()
				.filter(plan -> plan.getDayNumber() == dayNumber)
				.findFirst()
				.orElseGet(() -> {
					UTripOption.DayPlan newDayPlan = new UTripOption.DayPlan();
					newDayPlan.setStartDateAndDayNumber(tripOption.getStartDate(), dayNumber);
					tripOption.getDayPlans().add(newDayPlan);
					return newDayPlan;
				});

			// tourDetails 매핑
			PTourDetail detail = tourDetailMap.get(item.getContentId());
			if (detail != null) {
				item.setTourDetail(detail);
			}
			dayPlan.getItems().add(item);
		}

		// dayPlans를 dayNumber 기준으로 정렬
		tripOption.getDayPlans().sort(Comparator.comparingInt(UTripOption.DayPlan::getDayNumber));
	}

	/**
	 * 가상 회원의 존재 유무를 판단해서 가상회원 테이블, 여행 계획에 참여한 가상 회원 테이블에 추가하는 메서드
	 * @param tripUuid 여행 계획 UUID
	 * @param virtualMemberNames 가상 회원 이름 배열
	 */
	private void saveVirtualMembers(String tripUuid, List<String> virtualMemberNames) {
		for (String name : virtualMemberNames) {
			// 가상 멤버가 존재하는지 확인
			Integer virtualMemberId = uTripPlanMapper.selectVirtualMemberIdByName(name);
			if (virtualMemberId == null) {
				// 존재하지 않으면 가상 회원 테이블에 삽입
				uTripPlanMapper.insertVirtualMember(name);
				virtualMemberId = uTripPlanMapper.selectVirtualMemberIdByName(name);
			}
			// trip_virtual_member 테이블에 삽입 (String 타입으로 변환)
			uTripPlanMapper.insertTripVirtualMember(tripUuid, String.valueOf(virtualMemberId));
		}
	}

	/**
	 * 수정 날짜와 현재 시간을 duration으로 변환하는 메서드
	 * @param uTripOption 여행 계획 DTO
	 */
	private void calculateAndSetDayDiff(UTripOption uTripOption) {
		LocalDateTime currentDateTime = LocalDateTime.now();

		try {
			LocalDateTime pastDateTime = parseDateStringToLocalDateTime(uTripOption.getModDate());

			Duration duration = Duration.between(pastDateTime, currentDateTime);

			String dayDiffWord = calculateDayDiffWord(duration);

			uTripOption.setDayDiff(dayDiffWord);
		} catch (DateTimeParseException e) {
			// 예외 처리: 날짜 문자열 형식 오류
			log.error("날짜 문자열 형식이 잘못되었습니다: {}", uTripOption.getModDate(), e);
			// 기본값이나 오류 처리 로직 추가
			uTripOption.setDayDiff("날짜 형식 오류");
		}
	}

	/**
	 * 입력된 dateString을 포맷에 맞게 설정하는 메서드
	 * @param dateString 일자 형식으로 작성된 String
	 * @return String을 LocalDateTime으로 변경
	 */
	private LocalDateTime parseDateStringToLocalDateTime(String dateString) {
		// 입력된 dateString의 포맷에 맞게 DateTimeFormatter 생성
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// String을 LocalDateTime으로 변환
		return LocalDateTime.parse(dateString, formatter);
	}

	/**
	 * 작성된 시간과 현재를 비교해서 년,달,일,시간,분 전으로 표기하는 메서드
	 * @param duration 과거 날짜와 현재 날짜의 차이
	 * @return 년,달,일,시간,분 전 으로 표기
	 */
	private String calculateDayDiffWord(Duration duration) {
		long years = duration.toDays() / 365;
		long months = duration.toDays() / 30;
		long days = duration.toDays();
		long hours = duration.toHours();
		long minutes = duration.toMinutes();

		String dayDiffWord;

		if (years > 0) {
			dayDiffWord = years + "년 전";
		} else if (months > 0) {
			dayDiffWord = months + "달 전";
		} else if (days > 0) {
			dayDiffWord = days + "일 전";
		} else if (hours > 0) {
			dayDiffWord = hours + "시간 전";
		} else {
			dayDiffWord = minutes + "분 전";
		}
		return dayDiffWord;
	}

	/**
	 * 여행 계획에 참여하는 실제 회원을 조회하고 없다면 삽입하는 메서드
	 * @param tripUuid 여행 계획 UUID
	 * @param realMemberIds 실제 회원 아이디 목록
	 */
	private void saveRealMembers(String tripUuid, List<String> realMemberIds) {
		for (String memberId : realMemberIds) {
			// 실제 멤버가 존재하는지 확인
			Integer realMemberId = uTripPlanMapper.selectRealMemberIdByMemberId(memberId);
			if (realMemberId == null) {
				// 존재하지 않으면 삽입
				uTripPlanMapper.insertRealMember(memberId);
				realMemberId = uTripPlanMapper.selectRealMemberIdByMemberId(memberId);
			}
			// trip_real_member 테이블에 삽입 (String 타입으로 변환)
			uTripPlanMapper.insertTripRealMember(tripUuid, String.valueOf(realMemberId));
		}
	}

	/**
	 * 여행에 참여하는 가상 회원을 삭제하는 메서드
	 * @param tripUuid 여행 계획 UUID
	 */
	private void deleteVirtualMembers(String tripUuid) {
		uTripPlanMapper.deleteTripVirtualMembers(tripUuid);
	}

	/**
	 * 여행에 참여하는 실제 회원을 삭제하는 메서드
	 * @param tripUuid 여행 계획 UUID
	 */
	private void deleteRealMembers(String tripUuid) {
		uTripPlanMapper.deleteTripRealMembers(tripUuid);
	}

	/**
	 * 출발지와 목적지의 위경도, 거리, 소요시간 등을 계산하는 메서드
	 * @param startPoint 출발지 정보
	 * @param endPoint 도착지 정보
	 * @param apiResponse T map API에서 응답받은 데이터
	 * @return
	 */
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

