package ksmart.ks50team01.user.trip.controller;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import ksmart.ks50team01.user.member.login.dto.Login;
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

    @Value("${tour.api.key}")
    private String apiKey;

    @GetMapping("/plan")
    public String tripPlanPage(Model model){
        model.addAttribute("title", "여행 계획 작성");
        return "user/trip/tripItem";
    }

    @GetMapping("/board")
    public String boardPage(Model model){
        model.addAttribute("title", "여행 계획 공유");
        return "user/trip/sharePlan";
    }

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


    @GetMapping("/clusterer/{contentTypeId}")
    @ResponseBody
    public Map<String, Object> getClusterInfo(@PathVariable(value = "contentTypeId") String contentTypeId) throws
        JsonProcessingException {
        return uTripPlanService.getTourInfoObject(contentTypeId);
    }

    @GetMapping("/detailContentId/{contentId}")
    @ResponseBody
    public Map<String, Object> getDetailContentId(@PathVariable(value = "contentId") String contentId) throws
        JsonProcessingException {
        return uTripPlanService.getTourInfoObject(contentId);
    }

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

    @GetMapping("/list")
    public String planListPage(Model model){

        // uTourDataService.upsertSigunguData(apiKey);
        model.addAttribute("title", "내 여행 계획 목록");
        return "user/trip/planList";
    }

    @GetMapping("/schedule")
    public String planSchedulePage(Model model){

        // uTourDataService.upsertAreaData(apiKey);
        model.addAttribute("title", "여행 스케줄");

        return "user/trip/planSchedule";
    }
}
