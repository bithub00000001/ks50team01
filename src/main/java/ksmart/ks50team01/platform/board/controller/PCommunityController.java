package ksmart.ks50team01.platform.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.platform.board.dto.PComment;
import ksmart.ks50team01.platform.board.dto.PCommunity;
import ksmart.ks50team01.platform.board.service.PCommentService;
import ksmart.ks50team01.platform.board.service.PCommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value = "platform/board")
@RequiredArgsConstructor
@Slf4j
public class PCommunityController {
	
	private final PCommunityService pCommunityService;
	private final PCommentService pCommentService;
	
	
	// getCommunityList, getPostList ??
	
	

	// 커뮤니티 게시글 조회 페이지
	@GetMapping("/communityList")
	public String communityList(@RequestParam(value = "category", required = false) String category, Model model) {
		List<PCommunity> communityList;
		
	    if (category != null) {
	    	communityList = pCommunityService.getCommunityListByCategory(category);
	    } else {
	    	communityList = pCommunityService.getCommunityList();
	    }
		
		log.info("communityList: {}", communityList);
		
		model.addAttribute("communityList", communityList);
		model.addAttribute("selectedCategory", category);
		model.addAttribute("title", "커뮤니티 조회");
		
		return "platform/board/communityList";
	}
	
	
	/*
    @PostMapping("/dataTrans")
    @ResponseBody
    public Map<String, Object> getData(String type) {
        Map<String, Object> response = new HashMap<>();
        if ("post".equalsIgnoreCase(type)) {
            List<PCommunity> post = pCommunityService.getAllPost();
            response.put("dataList", post);
        } else if ("comment".equalsIgnoreCase(type)) {
            List<PComment> comment = PCommentService.getAllComment();
            response.put("dataList", comment);
        }
        return response;
    }*/
	
	
	
	/**
	 * 게시글 비활성화
	 * @param postNum
	 * @return
	 */
    @PostMapping("/deactivatePost")
    @ResponseBody
    public Map<String, Object> deactivatePost(@RequestParam("postNum") String postNum) {
        log.info("비활성화할 게시글 번호: {}", postNum);

        Map<String, Object> response = new HashMap<>();
        try {
            pCommunityService.deactivatePost(postNum);
            response.put("success", true);
            response.put("message", "게시물이 비활성화되었습니다.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시물 비활성화에 실패했습니다.");
        }

        return response;
    }


}
    

