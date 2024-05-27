package ksmart.ks50team01.user.board.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
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
	public String postList(Model model) {
		List<UCommunity> postList = uCommunityService.getPostList();

		model.addAttribute("postList", postList); // postList를 모델에 추가
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

	
	// 게시글 작성
	@PostMapping("/postWrite")
	public String postWrite(UCommunity uCommunity, HttpServletRequest request, Model model, @RequestParam(required = false) MultipartFile[] uploadfile) {
		uCommunityService.insertPost(uCommunity);

		log.info("게시글 등록:{}", uCommunity);
		log.info("입력받은 file data: {}",Arrays.toString(uploadfile));
		
		model.addAttribute("title", "게시글 작성");
		
        return "redirect:/community";
	}
	
	

	// 게시글 작성 폼 이동
	@GetMapping("/postWrite")
	public String postWrite(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		if (loginId == null) {
			model.addAttribute("loginRequired", true);
			return "redirect:/trip"; // 로그인 페이지 경로로 변경
		}
		
		List<UCategory> postCateList = uCommunityService.getPostCateList();
		log.info("postCateList: {}", postCateList);
		
		model.addAttribute("title", "게시글 작성 페이지");
		model.addAttribute("postCateList", postCateList);
		
		return "user/board/postWrite";
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
		
		log.info("게시글 수정", uCommunity);
		
		uCommunityService.postModify(uCommunity);
		
	   
		// 수정된 게시글의 상세 페이지로 이동
		return "redirect:/community/postDetail?postNum=" + uCommunity.getPostNum();
	}

	// 게시글 수정 페이지
	@GetMapping("/postModify")
	public String postModify(@RequestParam(value = "postNum") String postNum, Model model) {
		UCommunity postInfo = uCommunityService.getPostInfoByNum(postNum);
		
		log.info("postInfo :{}", postInfo);
		
		model.addAttribute("postInfo", postInfo);
		model.addAttribute("title", "게시글 수정 페이지");
		
		return "user/board/postModify";
	}
	
	// 게시글 삭제 POST 요청
	@PostMapping("/postDelete")
	public String postDelete(@RequestParam (value = "postNum") String postNum, Model model) {
		
		uCommunityService.postDelete(postNum);
		
		model.addAttribute("postNum", postNum);
		model.addAttribute("title", "게시글 삭제");
		
		return "redirect:/community";
		
	}
	

	
    /*
	// 게시글 삭제
	@GetMapping("/postDelete")
	public String postDeletePage(@RequestParam (value = "postNum") String postNum, Model model) {
		
		List<UCommunity> postList = uCommunityService.getPostList();
		uCommunityService.postDelete(postNum);

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
		// 댓글 저장
		uCommunityService.commentSave(commentRegId, postNum, commentContent);
		
		// 댓글 저장 후 해당 게시글의 모든 댓글을 가져옴
		List<UComment> postCommentList = uCommunityService.getPostCommentList(postNum);
		
		log.info("댓글 작성자: {}", commentRegId);
	    log.info("댓글 작성: {}", postCommentList);
	    
	    model.addAttribute("postCommentList", postCommentList);
	    model.addAttribute("title", "게시글 댓글 목록");
	    
		return postCommentList;
	} 

}
