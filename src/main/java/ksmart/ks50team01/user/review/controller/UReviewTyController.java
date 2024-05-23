/*
 * package ksmart.ks50team01.user.review.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody;
 * 
 * import ksmart.ks50team01.user.review.dto.UReview; import
 * ksmart.ks50team01.user.review.service.UReviewService; import
 * lombok.RequiredArgsConstructor; import lombok.extern.slf4j.Slf4j;
 * 
 * @Controller
 * 
 * @RequestMapping(value = "/user/review")
 * 
 * @RequiredArgsConstructor
 * 
 * @Slf4j public class UReviewTyController {
 * 
 * private final UReviewService uReviewService;
 * 
 * @GetMapping("/write") public String reviewWrite() { return
 * "user/review/reviewWrite"; }
 * 
 * @GetMapping("/list") public String reviewList(@RequestParam(value =
 * "sortOption", required = false, defaultValue = "추천순") String sortOption
 * ,@RequestParam(value = "tabName", required = false) String tabName , Model
 * model) {
 * 
 * List<UReview> uReviewList = uReviewService.getUReviewList(sortOption);
 * log.info("uReview: {}", uReviewList); System.out.println("uReviewList" +
 * uReviewList);
 * 
 * model.addAttribute("title", "상품 후기 목록"); model.addAttribute("uReviewList",
 * uReviewList); model.addAttribute("selectedOption", sortOption); if(tabName !=
 * null) model.addAttribute("tabName", tabName); return
 * "user/review/reviewList"; }
 * 
 * }
 */