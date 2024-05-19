package ksmart.ks50team01.user.board.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmart.ks50team01.user.board.dto.UCommunity;
import ksmart.ks50team01.user.board.service.UCommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
			postList.sort((p1, p2) -> p2.getPostRegDate().compareTo(p1.getPostRegDate())); // 최신순으로 정렬

			
		    // 각 게시글에 대한 댓글 수를 가져와서 모델에 추가
		    Map<String, Integer> commentCntMap = new HashMap<>();
		    for (UCommunity post : postList) {
		        int commentCnt = uCommunityService.getCommentCntByPostNum(post.getPostNum());
		        commentCntMap.put(post.getPostNum(), commentCnt);
		    }
		    
			model.addAttribute("postList", postList); // postList를 모델에 추가
			model.addAttribute("currentDate", LocalDate.now()); // 현재 날짜 추가
			model.addAttribute("commentCntMap", commentCntMap); // 댓글 수 맵을 모델에 추가
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
			List<UCommunity> commentList = uCommunityService.getCommentByPostNum(postNum);
			

			model.addAttribute("commentList", commentList);
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
		@ModelAttribute("postCateList")
		public List<String> postCateList() {
			return uCommunityService.getPostCateList();
		}
	
		// 게시글 저장
		@PostMapping("/postWrite")
		public String postWrite(@RequestParam("postRegId") String postRegId,
								@RequestParam("postCateNum") String postCateNum,
								@RequestParam("postTitle") String postTitle,
								@RequestParam("postContent") String postContent,
								RedirectAttributes redirectAttributes,
								Model model) {
	        try {
	            log.info("postRegId: {}, postCateNum: {}, postTitle: {}, postContent: {}", 
	                      postRegId, postCateNum, postTitle, postContent);

	            uCommunityService.insertPost(postRegId, postCateNum, postTitle, postContent);

	            redirectAttributes.addFlashAttribute("success", "게시글이 성공적으로 저장되었습니다.");
	            return "redirect:/community";
	        } catch (Exception e) {
	            log.error("게시글 저장 실패", e);
	            redirectAttributes.addFlashAttribute("error", "게시글 저장에 실패하였습니다.");
	            return "redirect:/error";
	            
	        }
	    }
		
		// 게시글 작성 폼 이동
		@GetMapping("/postWrite")
		public String postWrite(Model model) {
			List<UCommunity> postList = uCommunityService.getPostList();
			log.info("postList: {}", postList);
			model.addAttribute("title", "게시글 작성");
			model.addAttribute("postList", postList);
			return "user/board/postWrite";
		}
	
		
		
		@PostMapping("/generateCode")
		public ResponseEntity<String> generatePostCode() {
		    try {
		        // 게시물 서비스를 호출하여 새로운 게시물 코드를 생성합니다.
		        String postCode = uCommunityService.generatePostCode(0);
		        return new ResponseEntity<>("게시물 코드가 생성되었습니다: " + postCode, HttpStatus.OK);
		    } catch (Exception e) {
		        // 게시물 코드 생성에 실패한 경우 예외를 처리합니다.
		        return new ResponseEntity<>("게시물 코드 생성에 실패했습니다. 에러 메시지: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		}
	
	
	
		// 댓글 작성
		@PostMapping("/replySave")
		public String replySave(String replyContent) {
			// 클라이언트로부터 받은 답글을 서비스에 전달하여 저장하고 결과를 반환
			return uCommunityService.replySave(replyContent);
		}

}
