package ksmart.ks50team01.user.board.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UComment;
import ksmart.ks50team01.user.board.dto.UCommunity;
import ksmart.ks50team01.user.board.service.UCommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/community")
@RequiredArgsConstructor
@Slf4j
public class UCommunityController {

	private final UCommunityService uCommunityService;

	// 게시글 목록 조회
	@GetMapping({"/",""})
	public String postList(Model model) {
		List<UCommunity> postList = uCommunityService.getPostList();
		//postList.sort((p1, p2) -> p2.getPostRegDate().compareTo(p1.getPostRegDate())); // 최신순으로 정렬

		
	    // 각 게시글에 대한 댓글 수를 가져와서 모델에 추가
		/*
	    Map<String, Integer> commentCntMap = new HashMap<>();
	    for (UCommunity post : postList) {
	        int commentCnt = uCommunityService.getCommentCntByPostNum(post.getPostNum());
	        commentCntMap.put(post.getPostNum(), commentCnt);
	    }
	    */
		model.addAttribute("postList", postList); // postList를 모델에 추가
		model.addAttribute("currentDate", LocalDate.now()); // 현재 날짜 추가
		//model.addAttribute("commentCntMap", commentCntMap); // 댓글 수 맵을 모델에 추가
		model.addAttribute("title", "커뮤니티");
		return "user/board/postList";
	}
	
	
	
	// 게시글 상세 조회
	@GetMapping("/postDetail")
	public String postDetail(@RequestParam(name = "postNum", required = false) String postNum, Model model) {
		
		// 상세 페이지에 접속할 때마다 조회수 증가
		uCommunityService.increaseViewCount(postNum);
		
		
		// 게시물 정보를 가져와서 모델에 담아 상세 페이지로 전달
		UCommunity postDetail = uCommunityService.getPostByPostNum(postNum);
		model.addAttribute("postDetail", postDetail);
		
		// 해당 게시글의 모든 댓글 가져오기
		List<UComment> commentList = uCommunityService.getCommentByPostNum(postNum);
		

		model.addAttribute("commentList", commentList);
		model.addAttribute("postDetail", postDetail);
		model.addAttribute("postTitle", postDetail.getPostTitle());
		model.addAttribute("postContent", postDetail.getPostContent());
		model.addAttribute("postInqCnt", postDetail.getPostInqCnt());
		model.addAttribute("title", "게시글 상세");

		log.info("postNum: {}", postNum);
		log.info("getPostByPostNum: {}", postDetail);
		log.info("getCommentByPostNum: {}", commentList);

		return "user/board/postDetail";
	}
	

	// 게시글 리스트의 카테고리 리스트를 모델에 추가하는 어노테이션
	/*
	@ModelAttribute("postCateList")
	public List<String> postCateList() {
		return uCommunityService.getPostCateList();
	}
	 */
	// 게시글 등록
	@PostMapping("/postWrite")
	public String postWrite(UCommunity uCommunity,
							RedirectAttributes redirectAttributes,
							Model model) {
		log.info("게시글 등록:{}", uCommunity);
		
        uCommunityService.insertPost(uCommunity);
        redirectAttributes.addFlashAttribute("success", "게시글이 성공적으로 저장되었습니다.");
        
        return "redirect:/community";
	}
	
	/*
	@PostMapping("/postWrite")
	public String postWrite(@RequestParam(value = "postRegId") String postRegId,
							@RequestParam(value = "postCateNum") String postCateNum,
							@RequestParam(value = "postTitle") String postTitle,
							@RequestParam(value = "postContent") String postContent,
							RedirectAttributes redirectAttributes,
							Model model) {
        try {
            log.info("postRegId: {}, postCateNum: {}, postTitle: {}, postContent: {}", 
                      postRegId, postCateNum, postTitle, postContent);

            
            // 가장 큰 PST_NO_NUMERIC 값을 조회하여 새로운 PST_NO_NUMERIC 값 생성
            String maxPstNoNumeric = uCommunityService.getMaxPstNoNumeric();
            String newPstNoNumeric;

            if (maxPstNoNumeric != null) {
                // 숫자 부분만 추출해서 1을 더하고 다시 문자열로 변환
                int maxNumber = Integer.parseInt(maxPstNoNumeric);
                newPstNoNumeric = String.format("%03d", maxNumber + 1);
            } else {
                // 테이블이 비어있는 경우 첫 번째 값 설정
                newPstNoNumeric = "001";
            }

            String newPstNo = "PST_" + newPstNoNumeric;

            // 게시글 등록
            uCommunityService.insertPost(newPstNo, postRegId, postCateNum, postTitle, postContent);
            redirectAttributes.addFlashAttribute("success", "게시글이 성공적으로 저장되었습니다.");
            return "redirect:/community";
        } catch (Exception e) {
            log.error("게시글 저장 실패", e);
            redirectAttributes.addFlashAttribute("error", "게시글 저장에 실패하였습니다.");
            return "redirect:/error";
        }
    }
	*/

	// 게시글 작성 폼 이동
	@GetMapping("/postWrite")
	public String postWrite(Model model) {
		List<UCategory> postCateList = uCommunityService.getPostCateList();
		log.info("postCateList: {}", postCateList);
		
		model.addAttribute("title", "게시글 작성");
		model.addAttribute("postCateList", postCateList);
		
		return "user/board/postWrite";
	}
	

	// 댓글 작성
	@PostMapping("/replySave")
	public String replySave(String replyContent) {
		// 클라이언트로부터 받은 답글을 서비스에 전달하여 저장하고 결과를 반환
		return uCommunityService.replySave(replyContent);
	}

}
