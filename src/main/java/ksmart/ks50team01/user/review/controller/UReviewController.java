package ksmart.ks50team01.user.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ksmart.ks50team01.user.review.dto.UReview;
import ksmart.ks50team01.user.review.service.UReviewService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="/user/review")
@RequiredArgsConstructor
@Slf4j
public class UReviewController {
	
	private final UReviewService uReviewService;
	
	 
	
	/**
	 * 리뷰작성화면
	 * @param model
	 * @param session
	 * @return
	 */
	/*		@GetMapping("/write")
			public String reviewWrite(Model model, HttpSession session) {
				// 세션에서 로그인된 사용자의 ID를 가져옴
				String loginId = (String) session.getAttribute("loginId");
				
				if (loginId != null) {
					// 로그인된 경우
					List<UReview> reviewList = uReviewService.getUReviewList();
					System.out.println("reviewList: " + reviewList);
					
					// 세션에 저장된 로그인 아이디를 모델에 추가하여 HTML 페이지에서 사용할 수 있도록 함
					model.addAttribute("loginId", loginId);
					model.addAttribute("title", "리뷰작성");
					model.addAttribute("reviewList", reviewList);
					
					return "user/review/reviewWrite";
				} else {
					// 로그인되지 않은 경우 로그인 페이지로 리다이렉트
					return "redirect:/trip"; // 로그인 페이지 경로로 변경
				}
			}*/
		 
	/**
	 * 리뷰 작성 화면
	 * @return
	 */
	@GetMapping("/write") 
	public String reviewWrite(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		if (loginId == null) {
			return "redirect:/trip"; // 로그인 페이지 경로로 변경
		}
		
		List<UReview> reviewList = uReviewService.getUReviewList();
		System.out.println("reviewList: "+reviewList);
		
		model.addAttribute("title", "리뷰작성");
		model.addAttribute("reviewList", reviewList);
		
		return "user/review/reviewWrite"; 
	}
	
	/**
	 * 리뷰작성
	 * @param review
	 * @return
	 */
	/*	@PostMapping("/write")
		public String reviewWrite(@ModelAttribute UReview review, HttpSession session) {
		    String loginId = (String) session.getAttribute("loginId");
	
		    if (loginId == null) {
		        return "redirect:/login"; // 로그인 페이지 경로로 변경
		    }
	
		    // 리뷰 작성 시 로그인된 사용자의 ID를 설정
		    review.setReviewId(loginId);
	
		    uReviewService.reviewWrite(review); // 리뷰 데이터를 DB에 저장
		    
		    return "redirect:/user/review/write";
		}*/
	
		@PostMapping("/write")
		public String reviewWrite(UReview review) {
			
			System.out.println("리뷰 작성 화면에서 입력받은 data"+review);
			
			uReviewService.reviewWrite(review); //리뷰 데이터를 db에 저장
			
			return "redirect:/user/review/list";
		}
	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	/**
	 * 리뷰 상세 내용
	 * @return
	 */
	@GetMapping("/detail")
	public String reviewDetail() {
		return "user/review/reviewdetail";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 리뷰 json
	 * @param model
	 * @return
	 */
	@GetMapping("/lybalb")
	@ResponseBody
	public List<UReview> reviewJsonView(Model model) {
		
		List<UReview> uReviewList = uReviewService.getUReviewList();
		log.info("uReview: {}", uReviewList);
		System.out.println("uReviewList"+uReviewList);
		
		model.addAttribute("title", "상품 후기 목록");
		model.addAttribute("uReviewList", uReviewList);
		
		/* return "user/review/reviewList"; */
		return uReviewList;
	}
	
	/**
	 * 리뷰 목록 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String reviewList(Model model) {
		
		List<UReview> uReviewList = uReviewService.getUReviewList();
		log.info("uReview: {}", uReviewList);
		System.out.println("uReviewList"+uReviewList);
		
		model.addAttribute("title", "상품 후기 목록");
		model.addAttribute("uReviewList", uReviewList);
		
		return "user/review/reviewList";
	}

	
	@GetMapping("/list2")
	public String reviewList2() {
		return "user/review/reviewList2";
	}
	
	@GetMapping("/list3")
	public String reviewList3() {
		return "user/review/reviewList3";
	}
	
	@GetMapping("/list4")
	public String reviewList4() {
		return "user/review/reviewList4";
	}
	@GetMapping("/list5")
	public String reviewList5() {
		return "user/review/reviewList5";
	}
	
}
