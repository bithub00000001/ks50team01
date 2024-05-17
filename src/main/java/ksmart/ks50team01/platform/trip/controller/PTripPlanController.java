package ksmart.ks50team01.platform.trip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.platform.reivew.service.PReviewService;
import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.dto.PTripPlan;
import ksmart.ks50team01.platform.trip.service.PTourApiService;
import ksmart.ks50team01.platform.trip.service.PTripPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/platform/plan")
@Slf4j
@RequiredArgsConstructor
public class PTripPlanController {

	private final PTripPlanService pTripPlanService;
	private final PTourApiService pTourApiService;
	private final PReviewService pReviewService;

	@Value("${tour.api.key}")
	private String apiKey;

	// 여행 계획 목록 수정 post 요청
	@PostMapping("/modify")
	public String modify(@ModelAttribute PTripPlan pTripPlan) {
		pTripPlanService.UpdatePTripPlan(pTripPlan);
		return "redirect:/platform/plan/list";
	}

	// 여행 계획 목록 수정 페이지
	@GetMapping("/modify")
	public String planModify(@RequestParam(name = "planId") String planId,
		Model model) {
		PTripPlan pTripPlan = pTripPlanService.getPTripPlanById(planId);
		PTripPlan. Status[] statuses = PTripPlan.Status.values();
		model.addAttribute("title", "여행 계획 수정");
		model.addAttribute("planInfo", pTripPlan);
		model.addAttribute("statuses", statuses);

		return "platform/trip/planModify";
	}


	// 여행 계획 목록 조회 페이지
	@GetMapping("/list")
	public String planList(Model model) {
		List<PTripPlan> tripPlanList = pTripPlanService.getAllPTripPlan();
		log.info("tripPlanList: {}", tripPlanList);
		model.addAttribute("title", "여행 계획 리스트 조회");
		model.addAttribute("tripPlanList", tripPlanList);
		return "platform/trip/planList";
	}

	// 지역 코드, 시군 코드 API 에서 요청 후 DB에 삽입
	@GetMapping("/code")
	public String areaCodeManage(Model model) {

		List<PTourApi> areaCodeList = pTripPlanService.getAreaCodeList();
		model.addAttribute("title", "지역 코드 관리");
		model.addAttribute("areaCodeList", areaCodeList);

		return "platform/trip/areaCodeManage";
	}

	// Tour API 에서 음식점, 숙소, 관광지를 선택해서 DB에 인서트하는 페이지
	@GetMapping("/tourInfo")
	public String tourInfoManage(Model model) {
		List<?> areaCodeList = pTripPlanService.getAreaCodeList();
		model.addAttribute("title", "투어 정보 관리");
		model.addAttribute("areaCodeList", areaCodeList);

		return "platform/trip/tourInfoManage";
	}

	@GetMapping("/sigungu-codes")
	@ResponseBody
	public List<PTourApi> getSigunguCodes(@RequestParam(name = "areaCode") String areaCode) {
		List<PTourApi> sigunguCodes = pTripPlanService.getSigunguCodesByAreaCode(areaCode);
		return sigunguCodes;
	}





	// dataTables ajax를 위한 refer 페이지
	@PostMapping("/refer/{dataTrans}")
	@ResponseBody
	public Map<String, Object> planCodeManage(@PathVariable String dataTrans) {
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("dataTrans", dataTrans);
		List<?> dataList;
		if ("reportList".equals(dataTrans)) {
			dataList = pReviewService.getPReviewReports();
		}else {
			dataList = pReviewService.getPReviewReact();
		}
		if (dataList != null && !dataList.isEmpty()) {
			responseMap.put("dataList", dataList);
		}
		log.info("dataList: {}", dataList);
		return responseMap;
	}



	// 지역 코드, 시군구 코드 dataTables ajax
	@PostMapping(value = "/{dataTrans}")
	@ResponseBody
	public Map<String, Object> areaCodeManage(@PathVariable String dataTrans) {
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("dataTrans", dataTrans);
		List<?> dataList;
		if ("areaCode".equals(dataTrans)) {
			dataList = pTripPlanService.getAreaCodeList();
		}else {
			dataList = pTripPlanService.getSigunguCodeList();
		}
		if (dataList != null && !dataList.isEmpty()) {
			responseMap.put("dataList", dataList);
		}
		return responseMap;
	}

	/**
	 * 업데이트 버튼을 눌렀을때 지역 코드 혹은 시군 코드를 업서트 하는 메서드
	 * @param dataTrans
	 * @return
	 */
	@PostMapping(value = "/update/{dataTrans}")
	@ResponseBody
	public String yourControllerMethod(@PathVariable String dataTrans) {
		String status;
		if ("areaCode".equals(dataTrans)) {
			pTourApiService.upsertAreaData(apiKey);
			status = "지역 코드 업서트 성공";
		}else if ("sigunguCode".equals(dataTrans)) {
			pTourApiService.upsertSigunguData(apiKey);
			status = "시군 코드 업서트 성공";
		}else {
			status = "연결에 실패했습니다";
		}
		return status; // 또는 다른 응답
	}

	// 여행지를 api에서 업데이트 처리
	@PostMapping("/update/tourInfo")
	public ResponseEntity<String> updateTourInfo(@RequestBody Map<String, Object> requestData) {
		int numOfRows = Integer.parseInt((String) requestData.get("numOfRows"));
		int pageNo = Integer.parseInt((String) requestData.get("pageNo"));
		int contentTypeId = Integer.parseInt((String)requestData.get("contentTypeId"));
		String areaCode = (String)requestData.get("areaCode");
		String sigunguCode = requestData.get("sigunguCode") != null ? (String)requestData.get("sigunguCode") : "";
		try {
			List<PTourApi> tourInfoList = pTourApiService.getTourInfo(apiKey , contentTypeId, numOfRows, pageNo, areaCode,
				Optional.ofNullable(sigunguCode)).block();

			if (tourInfoList != null) {
				pTourApiService.saveData(tourInfoList);
				return ResponseEntity.ok("데이터 업데이트 성공");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 받아오기 실패");
			}
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러: " + ex.getMessage());
		}
	}

}
