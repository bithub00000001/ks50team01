package ksmart.ks50team01.platform.trip.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.dto.PTourDetail;
import ksmart.ks50team01.platform.trip.dto.PTripPlan;
import ksmart.ks50team01.platform.trip.mapper.PTripPlanMapper;
import ksmart.ks50team01.platform.trip.utils.ContentTypeConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PTripPlanServiceImpl implements PTripPlanService {

	private final PTripPlanMapper pTripPlanMapper;

	/**
	 * 여행 계획 목록 조회 메서드
	 */
	@Override
	public List<PTripPlan> getPTripPlanList() {
		List<PTripPlan> tripPlanList = pTripPlanMapper.getPlanList();
		tripPlanList.forEach(this::setSharedString);
		log.info("tripPlanList: {}", tripPlanList);
		return tripPlanList;
	}

	/**
	 * 여행 계획 ID와 일치하는 1개의 여행 계획 조회 메서드
	 * @param planId 여행 계획 ID(PK)
	 */
	@Override
	public PTripPlan getPTripPlanById(String planId) {
		PTripPlan pTripPlan = pTripPlanMapper.getTripPlanById(planId);
		setSharedString(pTripPlan);
		return pTripPlan;
	}

	/**
	 * 여행 계획 공유 여부 문자열 삽입 메서드
	 * @param pTripPlan PTripPlan DTO 객체
	 */
	private void setSharedString(PTripPlan pTripPlan) {
		String sharedString;
		if (pTripPlan.isShared()) {
			sharedString = "공유 안함";
		}else {
			sharedString = "공유 중";
		}
		pTripPlan.setIsShareString(sharedString);
		Integer totalAvailBudget = pTripPlan.getTotalAvailBudget();
		Integer totalPlanBudget = pTripPlan.getTotalPlanBudget();
		pTripPlan.setDiffBudget(totalAvailBudget - totalPlanBudget);
	}

	/**
	 * 1개의 여행 계획 업데이트 메서드
	 * @param pTripPlan 여행 계획 DTO 객체
	 */
	@Override
	public int UpdatePTripPlan(PTripPlan pTripPlan) {
		Integer totalAvailBudget = pTripPlan.getTotalAvailBudget();
		Integer totalPlanBudget = pTripPlan.getTotalPlanBudget();
		pTripPlan.setDiffBudget(totalAvailBudget - totalPlanBudget);

		return pTripPlanMapper.updateTripPlan(pTripPlan);
	}

	/**
	 * 지역 코드 목록 조회 메서드
	 */
	@Override
	public List<PTourApi> getAreaCodeList(){
		return pTripPlanMapper.getAreaCodeList();
	}

	/**
	 * 시군구 코드 목록 조회 메서드
	 */
	@Override
	public List<PTourApi> getSigunguCodeList(){
		return pTripPlanMapper.getSigunCodeList();
	}

	/**
	 * 지역 코드에 해당하는 시군 코드 조회
	 * @param areaCode 지역 코드
	 */
	@Override
	public List<PTourApi> getSigunguCodesByAreaCode(String areaCode) {
		return pTripPlanMapper.getSigunguCodesByAreaCode(areaCode);
	}

	/**
	 * 여행지 정보 목록 조회
	 */
	@Override
	public List<PTourApi> getDestinationList() {
		List<PTourApi> destinationList = pTripPlanMapper.getDestinationList();
		destinationList.forEach(info -> {
			String contentTypeName = ContentTypeConstants.CONTENT_TYPE_MAP.getOrDefault(info.getDestinationContentTypeId(),"");
			info.setDestinationContentTypeName(contentTypeName);
		});
		return destinationList;
	}

	/**
	 * 관광 타입에 따른 여행지 정보 목록 조회
	 * @param contentTypeId 관광 타입
	 */
	@Override
	public List<PTourApi> getDestinationListByContentType(String contentTypeId) {
		List<PTourApi> destinationList = pTripPlanMapper.getDestinationListByContentType(contentTypeId);
		destinationList.forEach(info -> {
			String contentTypeName = ContentTypeConstants.CONTENT_TYPE_MAP.getOrDefault(info.getDestinationContentTypeId(),"");
			info.setDestinationContentTypeName(contentTypeName);
		});
	return destinationList;
	}

	/**
	 * 관광 타입과 컨텐츠 ID와 일치하는 여행지 세부 정보 조회
	 *
	 * @param contentId 컨텐츠 아이디(PK)
	 * @param contentTypeId 관광 타입
	 */
	@Override
	public PTourDetail getPTourDetailByContentId(String contentId, String contentTypeId) {
		PTourDetail tourDetail = pTripPlanMapper.getPTourDetailByContentId(contentId, contentTypeId);
		PTourApi tourInfo = pTripPlanMapper.getSigunguNameBySigunCode(tourDetail.getAreaCode(), tourDetail.getSigunguCode());
		String contentTypeName = ContentTypeConstants.CONTENT_TYPE_MAP.getOrDefault(tourDetail.getContentTypeId(),"");
		tourDetail.setAreaName(tourInfo.getAreaInfo().getAreaName());
		tourDetail.setSigunguName(tourInfo.getSigunguName());
		tourDetail.setContentTypeName(contentTypeName);
		return tourDetail;
	}

}
