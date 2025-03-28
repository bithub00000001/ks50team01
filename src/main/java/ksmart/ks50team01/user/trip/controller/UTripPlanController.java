package ksmart.ks50team01.user.trip.controller;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpSession;
import ksmart.ks50team01.platform.trip.service.PTripPlanService;
import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.trip.dto.UDayInfo;
import ksmart.ks50team01.user.trip.dto.UDistanceRequest;
import ksmart.ks50team01.user.trip.dto.UTripDetailOption;
import ksmart.ks50team01.user.trip.dto.UTripOption;
import ksmart.ks50team01.user.trip.service.UTourDataService;
import ksmart.ks50team01.user.trip.service.UTripPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/trip")
@RequiredArgsConstructor
@Slf4j
public class UTripPlanController {

    private final UTripPlanService uTripPlanService;
    private final UTourDataService uTourDataService;
    private final PTripPlanService pTripPlanService;

    @Value("${tour.api.key}")
    private String apiKey;

    // 여행 계획 세부 아이템 페이지
    @GetMapping("/plan")
    public String tripPlanPage(Model model){
        model.addAttribute("title", "여행 계획 작성");
        return "user/trip/tripItem";
    }

    // 여행 계획 공유 게시판 페이지
    @GetMapping("/board")
    public String boardPage(Model model){
        model.addAttribute("title", "여행 계획 공유");
        return "user/trip/sharePlan";
    }

    // 여행 계획 작성 페이지
    @GetMapping("/detail")
    public String tripCreatePage(@ModelAttribute("uTripOption") Optional<UTripOption> uTripOption,Model model){
        model.addAttribute("title", "여행 계획 작성");
        // ifPresentOrElse 메서드 : Optional 객체가 값을 포함하고 있는 경우와 그렇지 않은 경우에 대해 각각 다른 동작을 정의
        // 무조건 첫 번째 인자로 Consumer 람다, 두번째 인수로 Runnable 람다를 갖는다
        // Consumer : 입력 값을 받아서 실행되고 결과를 리턴하지 않음
        // Runnable : 인자도 받지 않고 결과도 리턴하지 않음
        // UTripOption 이 null이 아닌 경우 해당 값을 model의 속성으로 추가하고, null 인 경우 새로운 객체를 생성한다
        uTripOption.ifPresentOrElse(
            option -> model.addAttribute("uTripOption", option),
            () -> model.addAttribute("uTripOption", new UTripOption())
        );
        return "user/trip/tripPlan";
    }

    /**
     * 전달받은 순서, contentId, 일자를 입력받아 거리와 시간을 계산하고 반환하는 메서드
     * @param uDayInfoList 여행지 세부 사항 DTO
     * @return
     */
    @PostMapping("/calculate-info")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> calculateInfo(@RequestBody List<UDayInfo> uDayInfoList) throws
		JsonProcessingException {
        Map<String, Object> resultMap = uTripPlanService.calculateDistanceDuration(uDayInfoList);
        log.info("resultMap: {}", resultMap);
        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }

    /**
     * 여행 계획 정보 임시 저장 메서드
     * @param uTripOption 작성된 여행 계획 정보
     * @return
     */
    @PostMapping("/save-temp-plan-info")
    @ResponseBody
    public ResponseEntity<String> addTempPlanInfo(@RequestBody UTripOption uTripOption) {
        log.info("uTripOption: {}", uTripOption);
        try {
            // 기존 데이터가 있는지 확인하고, 있다면 업데이트, 없다면 새로 추가
            uTripPlanService.saveOrUpdateTempPlanInfo(uTripOption);
            return ResponseEntity.ok("여행 계획이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("여행 정보를 임시 저장하는데 실패했습니다.");
        }
    }

    /**
     * 회원 중 일반 회원 목록을 조회하는 메서드
     * @return List<Login>
     */
    @GetMapping("/user-member")
    @ResponseBody
    public List<Login> getUserMembers() {
        return uTripPlanService.getUserMembers();
    }

    /**
     * 회원 중 일반 회원이며 nickname과 유사한 회원의 목록을 조회하는 메서드
     * @param nickname 회원의 닉네임
     * @return List<Login>
     */
    @GetMapping("/search-user-member")
    @ResponseBody
    public List<Login> searchUserMember(@RequestParam(name = "nickname") String nickname) {
        return uTripPlanService.searchUserMembers(nickname);
    }

    /**
     * 카카오 맵 API 의 클러스터를 생성하기 위한 메서드
     * @param contentTypeId 관광 타입
     * @return 여행 정보 객체
     * @throws JsonProcessingException
     */
    @GetMapping("/clusterer/{contentTypeId}")
    @ResponseBody
    public Map<String, Object> getClusterInfo(@PathVariable(value = "contentTypeId") String contentTypeId) throws
        JsonProcessingException {
        return uTripPlanService.getTourInfoObject(contentTypeId);
    }

    /**
     * 컨텐트 ID와 일치하는 여행지 상세 정보를 요청하고 반환하는 메서드
     * @param contentId 여행지 정보의 컨텐트 ID
     * @return 여행지 상세 정보 객체
     * @throws JsonProcessingException
     */
    @GetMapping("/detailContentId/{contentId}")
    @ResponseBody
    public Map<String, Object> getDetailContentId(@PathVariable(value = "contentId") String contentId) throws
        JsonProcessingException {
        return uTripPlanService.getTourInfoObject(contentId);
    }

    /**
     * index 페이지에서 datepicker, 인원 수를 입력받아 detail 페이지에 전달하는 메서드
     * @param uTripOption 여행 옵션 DTO
     * @param redirectAttributes 리다이렉트하며 DTO를 전달
     * @return 리다이렉트
     */
    @PostMapping("/detail")
    public String processTripDetails(@ModelAttribute(name = "tripOption") UTripOption uTripOption,
        RedirectAttributes redirectAttributes) {
        try {
            uTripOption = uTripPlanService.parseDateRange(uTripOption);
        } catch (DateTimeParseException e) {
            // 오류 처리 로직 사용자에게 오류 메시지 전송
            redirectAttributes.addFlashAttribute("errorMessage", "날짜 형식이 올바르지 않습니다.");
            return "redirect:/";
        }
        log.info("uTripOption: {}", uTripOption);
        redirectAttributes.addFlashAttribute("uTripOption", uTripOption);
        return "redirect:/trip/detail";
    }

    /**
     * 여행 계획 상세 정보 저장 요청 처리
     * @param uTripDetailOption 여행지 상세 정보 DTO
     * @return
     */
    @PostMapping("/save-trip-detail-info")
    public ResponseEntity<String> saveTripDetailInfo(@RequestBody UTripDetailOption uTripDetailOption) {
        log.info("uTripDetailOption: {}", uTripDetailOption);
        try {
            int rowsAffected = uTripPlanService.saveTripDetailInfo(uTripDetailOption);
            if (rowsAffected > 0) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("fail");
            }
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("잘못된 요청입니다: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("오류 발생");
        }
    }

    /**
     * 자신이 작성한 여행 계획 목록을 조회하는 페이지
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String planListPage(HttpSession session, RedirectAttributes redirectAttributes, Model model){
        String sessionId = (String) session.getAttribute("loginId");
        if (sessionId == null) {
            redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
            return "redirect:/trip"; // 로그인 페이지로 리디렉션
        }
        // uTourDataService.upsertSigunguData(apiKey);
        List<UTripOption> tripPlanList = uTripPlanService.getTempPlanList(sessionId);
        log.info("tripPlanList: {}", tripPlanList);

        model.addAttribute("title", "내 여행 계획 목록");
        model.addAttribute("plans", tripPlanList);
        return "user/trip/planList";
    }

    /**
     * 여행 계획 목록 조회 페이지에서 각 계획을 클릭 시 이동하는 페이지
     * @param model
     * @return
     */
    @GetMapping("/schedule")
    public String planSchedulePage(@RequestParam(name = "planUUID", required = false)String planUUID, Model model){

        // uTourDataService.upsertAreaData(apiKey);
        try {
            UTripOption tripOption = uTripPlanService.getTripOptionByUUID(planUUID);
            log.info("tripOption: {}", tripOption);
            if (tripOption != null) {
                model.addAttribute("tripOption", tripOption);
                model.addAttribute("title", "여행 스케줄");
                return "user/trip/planSchedule"; // 스케줄 페이지로 이동
            } else {
                log.error("여행 계획을 찾을 수 없습니다. planUUID: {}", planUUID);
                return "user/error/error"; // 에러 페이지로 이동 혹은 다른 처리
            }
        } catch (Exception e) {
            log.error("여행 계획 조회 중 오류 발생", e);
            return "user/error/error"; // 에러 페이지로 이동 혹은 다른 처리
        }
    }

    /**
     * 여행지 정보 조회 페이지(클라이언트 사이드 페이지네이션)
     * @param model
     * @return
     */
    @GetMapping("/tourInfo")
    public String tourInfoPage(Model model){
        List<?> areaCodeList = pTripPlanService.getAreaCodeList();
        model.addAttribute("title", "관광지 조회");
        model.addAttribute("areaCodeList", areaCodeList);

        return "user/trip/tourInfo";
    }

    /**
     * 여행지 상세 정보 전달 API
     * @return
     */
    @GetMapping("/tourInfo/tourData")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> getTourData() {
        List<Map<String, Object>> tourDataList = uTripPlanService.getTourDataList();
        log.info("tourDataList: {}", tourDataList);
        if (tourDataList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tourDataList);
    }

    /**
     * 클라이언트 사이드 페이지네이션 구현 레퍼런스
     * @return
     */
    @GetMapping("/paginationRefer")
    public String planReferPage(){
        return "user/trip/referPagination";
    }

    /**
     * 클라이언트 사이드 페이지네이션 구현 레퍼런스 Ajax
     * @return
     */
    @GetMapping("/pagination/refer/mockData")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> getPaginationRefer(){
        List<Map<String, Object>> mockData = uTripPlanService.readMockData();

        if (mockData.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 에러 반환(no content)
        }
        return ResponseEntity.ok(mockData);
    }
}
