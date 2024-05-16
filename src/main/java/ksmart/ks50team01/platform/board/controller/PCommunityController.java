package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	

}
