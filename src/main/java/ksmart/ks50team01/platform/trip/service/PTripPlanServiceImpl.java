package ksmart.ks50team01.platform.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			if (!plan.isShare()) {
				plan.setIsShareString("공유 안함");
			}else {
				plan.setIsShareString("공유 중");
			}
		});
		log.info("tripPlanList: {}", tripPlanList);
		return tripPlanList;
	}

	@Override
	public PTripPlan getPTripPlanById(String planId) {
		PTripPlan pTripPlan = pTripPlanMapper.getTripPlanById(planId);
		if (!pTripPlan.isShare()){
			pTripPlan.setIsShareString("공유 안함");
		}else {
			pTripPlan.setIsShareString("공유 중");
		}
		return pTripPlan;
	}

	@Override
	public int UpdatePTripPlan(PTripPlan pTripPlan) {
		return 0;
	}
}
