package ksmart.ks50team01.user.board.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UComment;
import ksmart.ks50team01.user.board.dto.UCommunity;
import ksmart.ks50team01.user.board.dto.UPostFile;
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
	public String postList(@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage,
			Model model) {
		//List<UCommunity> postList = uCommunityService.getPostList();
		
		Map<String, Object> resultMap = uCommunityService.getPostList(currentPage);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> postList = (List<Map<String, Object>>) resultMap.get("postList");
		int lastPage = (int) resultMap.get("lastPage");
		int startPageNum = (int) resultMap.get("startPageNum");
		int lastPageNum = (int) resultMap.get("lastPageNum");
				
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		
		model.addAttribute("postList", postList);
		model.addAttribute("title", "커뮤니티");
		
		return "user/board/postList";
	}
	
	
	
	// 검색 목록 조회
	@GetMapping("/searchList")
	public String searchList(@RequestParam(value="searchKey", required = false) String searchKey
							,@RequestParam(value="searchValue", required = false) String searchValue
							,Model model) {
		List<UCommunity> postList = uCommunityService.getSearchList(searchKey, searchValue);
		
		model.addAttribute("title", "검색목록조회");
		model.addAttribute("postList", postList);
		
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "user/board/postList";
	}
	
	
	// 게시글 상세 조회
	@GetMapping("/postDetail")
    public String postDetail(@RequestParam(name = "postNum", required = false) String postNum,
            Model model, HttpSession session) {
		
		// 세션에서 로그인한 사용자 아이디 가져오기
		String loginId = (String) session.getAttribute("loginId");
		model.addAttribute("loginId", loginId);

		
		// 상세 페이지에 접속할 때마다 조회수 증가
		uCommunityService.increaseViewCount(postNum);
		
		// 게시물 정보를 가져와서 모델에 담아 상세 페이지로 전달
		UCommunity postDetail = uCommunityService.getPostDetail(postNum);
		
		// 해당 게시글의 모든 댓글 가져오기
		List<UComment> commentList = uCommunityService.getCommentByPostNum(postNum);
		

		model.addAttribute("postDetail", postDetail);
		model.addAttribute("commentList", commentList);
		model.addAttribute("title", "게시글 상세");

		log.info("postNum: {}", postNum);
		log.info("getPostByPostNum: {}", postDetail);
		log.info("getCommentByPostNum: {}", commentList);

		return "user/board/postDetail";
	}

	
	// 게시글 등록
	@PostMapping("/postAdd")
	public String postAdd(UCommunity uCommunity, Model model, @RequestParam(required = false) MultipartFile[] uploadfile) {
		String contentWithLineBreaks = uCommunity.getPostContent().replace("\n", "<br>");
		uCommunity.setPostContent(contentWithLineBreaks);
		
		uCommunityService.postAdd(uCommunity);

		log.info("게시글 등록:{}", uCommunity);
		log.info("입력받은 file data: {}",Arrays.toString(uploadfile));
		
		model.addAttribute("title", "게시글 작성");
		
        return "redirect:/community";
	}
	
	

	// 게시글 등록 페이지
	@GetMapping("/postAdd")
	public String postAdd(Model model) {
		
		List<UCategory> postCateList = uCommunityService.getPostCateList();
		log.info("postCateList: {}", postCateList);
		
		model.addAttribute("title", "게시글 작성 페이지");
		model.addAttribute("postCateList", postCateList);
		
		return "user/board/postAdd";
	}
	
	
	
	
	/**
	 * 파일 json
	 */
	@GetMapping("/file/json")
	@ResponseBody
	public List<UPostFile> postFileJsonView(Model model) {
		
		List<UPostFile> postFileList = uCommunityService.getFileList();
		log.info("postFileList: {}", postFileList);
		
		model.addAttribute("title", "게시글 파일 목록");
		model.addAttribute("postFileList", postFileList);
		
	return postFileList;
		
	}

	
	// 게시글 수정 POST 요청
	@PostMapping("/postModify")
	public String postModify(UCommunity uCommunity, Model model) {
		String contentWithLineBreaks = uCommunity.getPostContent().replace("\n", "<br>");
		uCommunity.setPostContent(contentWithLineBreaks);
		
		uCommunityService.postModify(uCommunity);
		
		log.info("게시글 수정", uCommunity);
		
		// 수정된 게시글의 상세 페이지로 이동
		return "redirect:/community/postDetail?postNum=" + uCommunity.getPostNum();
	}

	
	// 게시글 수정 페이지
	@GetMapping("/postModify")
	public String postModify(@RequestParam(value = "postNum") String postNum, Model model) {
		UCommunity postInfo = uCommunityService.getPostInfoByNum(postNum);
		
	    // <br> 태그를 \n으로 변환
	    String contentWithLineBreaks = postInfo.getPostContent().replace("<br>", "\n");
	    postInfo.setPostContent(contentWithLineBreaks);
	    
		log.info("postInfo :{}", postInfo);
		
		model.addAttribute("postInfo", postInfo);
		model.addAttribute("title", "게시글 수정 페이지");
		
		return "user/board/postModify";
	}
	
	// 게시글 삭제 POST 요청
	@PostMapping("/postRemove")
	public String postRemove(@RequestParam (value = "postNum") String postNum, Model model) {
		
		uCommunityService.postRemove(postNum);
		uCommunityService.postCommentRemove(postNum);
		
		model.addAttribute("postNum", postNum);
		model.addAttribute("title", "게시글 삭제");
		
		return "redirect:/community";
		
	}
	
	
    /*
	// 게시글 삭제
	@GetMapping("/postRemove")
	public String postRemove(@RequestParam (value = "postNum") String postNum, Model model) {
		
		List<UCommunity> postList = uCommunityService.getPostList();
		uCommunityService.postRemove(postNum);

		model.addAttribute("postList", postList);
		model.addAttribute("postNum", postNum);
		model.addAttribute("title", "게시글 삭제");
		
		return "redirect:/community";
	} */
	
	
	// 댓글 작성
	@PostMapping("/commentSave")
	@ResponseBody
	public List<UComment> postComment(Model model
									,@RequestParam(value="commentRegId", required = false) String commentRegId
									,@RequestParam(value="postNum", required = false) String postNum
									,@RequestParam(value="commentContent", required = false) String commentContent) {
		
		uCommunityService.commentSave(commentRegId, postNum, commentContent);
		
		// 댓글 저장 후 해당 게시글의 모든 댓글을 가져옴
		List<UComment> postCommentList = uCommunityService.getPostCommentList(postNum);
		
		log.info("댓글 작성자: {}", commentRegId);
	    log.info("댓글 작성: {}", postCommentList);
	    
	    model.addAttribute("postCommentList", postCommentList);
	    model.addAttribute("title", "게시글 댓글 목록");
	    
		return postCommentList;
	} 
	
	

    // 댓글 수정
    @PostMapping("/commentModify")
    @ResponseBody
    public Map<String, Object> commentModify(@RequestBody UComment uComment) {
        Map<String, Object> response = new HashMap<>();
        try {
            uCommunityService.commentModify(uComment);
            response.put("status", "success");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }
    
	
    
    // 댓글 삭제
    @PostMapping("/commentRemove")
    @ResponseBody
    public Map<String, Object> commentRemove(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        String commentNum = request.get("commentNum");
        String postNum = request.get("postNum");

        try {
            uCommunityService.commentRemove(commentNum);

            // 댓글 삭제 후 해당 게시글의 모든 댓글을 가져옴
            List<UComment> postCommentList = uCommunityService.getPostCommentList(postNum);

            response.put("status", "success");
            response.put("postCommentList", postCommentList);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }

        return response;
    }
    
	
    // 답글 작성
    @PostMapping("/replySave")
    @ResponseBody
    public List<UComment> postReply(Model model,
                                    @RequestParam(value = "commentRegId", required = false) String commentRegId,
                                    @RequestParam(value = "postNum", required = false) String postNum,
                                    @RequestParam(value = "commentContent", required = false) String commentContent,
                                    @RequestParam(value = "parentCommentId", required = false) String parentCommentId) {
    	// 답글 저장
    	uCommunityService.replySave(commentRegId, postNum, commentContent, parentCommentId);
    	
    	// 답글 저장 후 해당 게시글의 모든 댓글을 가져옴
        List<UComment> postCommentList = uCommunityService.getPostCommentList(postNum);

        log.info("답글 작성자: {}", commentRegId);
        log.info("답글 작성: {}", postCommentList);

        model.addAttribute("postCommentList", postCommentList);
        model.addAttribute("title", "게시글 답글 목록");

        return postCommentList;
    }
	
    
    
    // 좋아요 버튼 클릭 시 처리
    @PostMapping("/like")
    @ResponseBody
    public String like(@RequestParam String postNum, Model model) {
        uCommunityService.increaseLikeCount(postNum);

        UCommunity uCommunity = uCommunityService.getPostByNum(postNum);
        return Integer.toString(uCommunity.getTotalLikes());
    }
    
    
    // 싫어요 버튼 클릭 시 처리
    @PostMapping("/dislike")
    @ResponseBody
    public String dislike(@RequestParam String postNum, Model model) {
        uCommunityService.increaseDislikeCount(postNum);
        
        UCommunity uCommunity = uCommunityService.getPostByNum(postNum);
        return Integer.toString(uCommunity.getTotalDislikes());
    }
    

}
