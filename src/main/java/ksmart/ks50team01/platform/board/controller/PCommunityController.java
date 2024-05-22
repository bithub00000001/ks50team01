package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.board.dto.PCommunity;
import ksmart.ks50team01.platform.board.service.PCommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value = "platform/board")
@RequiredArgsConstructor
@Slf4j
public class PCommunityController {
	
	private final PCommunityService pCommunityService;
	
	
	// getCommunityList, getPostList ??
	
	

	// 커뮤니티 조회 페이지
	@GetMapping("/communityList")
	public String communityList(Model model) {
		List<PCommunity> communityList = pCommunityService.getCommunityList();
		log.info("communityList: {}", communityList);
		model.addAttribute("communityList", communityList);
		model.addAttribute("title", "커뮤니티 조회");
		return "platform/board/communityList";
	}
	
	/**
	 * 게시글 비활성화
	 * @param postNum
	 * @return
	 */
    @PostMapping("/deactivatePost")
    public ResponseEntity<String> deactivatePost(@RequestBody List<String> postNums) {
        log.info("비활성화할 게시글 번호: {}", postNums); // 로깅 추가
        pCommunityService.deactivatePost(postNums);
        return ResponseEntity.ok("비활성화 성공");
    }

    @GetMapping("/deactivatePost")
    public String deactivatePost(@RequestParam(value="postNum" , required = false) String postNum, Model model) {
        log.info("게시글 비활성화 : {}", postNum);
        model.addAttribute("title", "게시글 비활성화");
        return "platform/board/communityList";
    }
}
