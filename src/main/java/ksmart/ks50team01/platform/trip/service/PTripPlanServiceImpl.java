package ksmart.ks50team01.platform.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.dto.PTripPlan;
import ksmart.ks50team01.platform.trip.mapper.PTripPlanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PTripPlanServiceImpl implements PTripPlanService {

	private final PTripPlanMapper pTripPlanMapper;

	@Override
	public List<PTripPlan> getAllPTripPlan() {
		List<PTripPlan> tripPlanList = pTripPlanMapper.getPlanList();
		tripPlanList.forEach(plan -> {
			String sharedString = "";
			if (plan.isShared()) {
				sharedString = "공유 안함";
			}else {
				sharedString = "공유 중";
			}
			plan.setIsShareString(sharedString);
			Integer totalAvailBudget = plan.getTotalAvailBudget();
			Integer totalPlanBudget = plan.getTotalPlanBudget();
			plan.setDiffBudget(totalAvailBudget - totalPlanBudget);
		});
		log.info("tripPlanList: {}", tripPlanList);
		return tripPlanList;
	}

	@Override
	public PTripPlan getPTripPlanById(String planId) {
		PTripPlan pTripPlan = pTripPlanMapper.getTripPlanById(planId);
		if (!pTripPlan.isShared()){
			pTripPlan.setIsShareString("공유 안함");
		}else {
			pTripPlan.setIsShareString("공유 중");
		}
		Integer totalAvailBudget = pTripPlan.getTotalAvailBudget();
		Integer totalPlanBudget = pTripPlan.getTotalPlanBudget();
		pTripPlan.setDiffBudget(totalAvailBudget - totalPlanBudget);
		return pTripPlan;
	}

	@Override
	public int UpdatePTripPlan(PTripPlan pTripPlan) {
		Integer totalAvailBudget = pTripPlan.getTotalAvailBudget();
		Integer totalPlanBudget = pTripPlan.getTotalPlanBudget();
		pTripPlan.setDiffBudget(totalAvailBudget - totalPlanBudget);

		return pTripPlanMapper.updateTripPlan(pTripPlan);
	}

	/**
	 * 지역 코드를 DB에서 불러오기 위해 Mapper와 연결
	 * @return
	 */
	@Override
	public List<PTourApi> getAreaCodeList(){
		return pTripPlanMapper.getAreaCodeList();
	}

	/**
	 * 시군구 코드를 DB에서 불러오기 위해 Mapper와 연결
	 * @return
	 */
	@Override
	public List<PTourApi> getSigunguCodeList(){
		return pTripPlanMapper.getSigunCodeList();
	}

	@Override
	public List<PTourApi> getSigunguCodesByAreaCode(String areaCode) {
		return pTripPlanMapper.getSigunguCodesByAreaCode(areaCode);
	}
}
