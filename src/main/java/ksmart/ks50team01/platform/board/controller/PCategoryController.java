package ksmart.ks50team01.platform.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.service.PCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(value = "/platform/board")
@RequiredArgsConstructor
@Slf4j
public class PCategoryController {
	
	private final PCategoryService pCategoryService;
	
	// 게시판 종류에 해당하는 카테고리 조회
	@GetMapping("/categoryList")
	public String categoryList(String boardType, Model model) {
		List<PCategory> categoryList = pCategoryService.getFaqCategoryList();
		log.info("controller categoryList:{}", categoryList);
		model.addAttribute("title", "카테고리 조회");
		model.addAttribute("categoryList", categoryList);
		return "platform/board/categoryList";
	}
	

	
	
	/*
    @GetMapping("/notice")
    @ResponseBody
    public Map<String, Object> getNoticeCategory() {
        Map<String, Object> response = new HashMap<>();
        List<PCategory> noticeCategory = pCategoryService.getNoticeCategoryList();
        response.put("noticeCategory", noticeCategory);
        return response;
    }
    

    

    @GetMapping("/report")
    @ResponseBody
    public Map<String, Object> getReportCategory() {
        Map<String, Object> response = new HashMap<>();
        List<PCategory> reportCategory = pCategoryService.getReportCategoryList();
        response.put("reportCategory", reportCategory);
        return response;
    }

    @GetMapping("/faq")
    @ResponseBody
    public Map<String, Object> getFaqCategory() {
        Map<String, Object> response = new HashMap<>();
        List<PCategory> faqCategory = pCategoryService.getFaqCategoryList();
        response.put("faqCategory", faqCategory);
        return response;
    }

    @GetMapping("/community")
    @ResponseBody
    public Map<String, Object> getCommunityCategory() {
        Map<String, Object> response = new HashMap<>();
        List<PCategory> communityCategory = pCategoryService.getCommunityCategoryList();
        response.put("communityCategory", communityCategory);
        return response;
    }*/
	
	
	
	
	// 카테고리 추가 post 요청
	@PostMapping("/categoryAdd")
	public String categoryAdd(PCategory pCategory, Model model) {
		pCategoryService.categoryAdd(pCategory);
		model.addAttribute("title", "카테고리 추가 페이지");
		return "redirect:/platform/board/categoryList";
	}
	
	// 카테고리 추가 페이지
	@GetMapping("/categoryAdd")
	public String categoryAdd(Model model) {
		model.addAttribute("title", "카테고리 추가");
		return "platform/board/categoryAdd";
	}
	
	// 카테고리 수정 post 요청
	@PostMapping("/categoryModify")
	public String categoryModify(PCategory pCategory, Model model) {
		pCategoryService.categoryModify(pCategory);
		model.addAttribute("title", "카테고리 수정 페이지");
		return "redirect:/platform/board/categoryList";
	}
	
	// 카테고리 수정 페이지
	@GetMapping("/categoryModify")
	public String categoryModify(Model model) {
		model.addAttribute("title", "카테고리 수정");
		return "platform/board/categoryModify";
	}
	
    
    /*
    @PostMapping("/{dataTrans}")
    @ResponseBody
    public Map<String, Object> postMethodName(@PathVariable String dataTrans) {
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	responseMap.put("dataTrans", dataTrans);
    	List<?> dataList = null;
		if("noticeCateList".equals(dataTrans)) {
			dataList = pCategoryService.getNoticeCategoryList();
		}
		if(dataList != null) responseMap.put("data", dataList);
		return responseMap;
    } */
    
    @PostMapping("/{dataTrans}")
    @ResponseBody
    public Map<String, Object> getCategoryList(@PathVariable String dataTrans) {
        Map<String, Object> response = new HashMap<String, Object>();
        List<PCategory> categoryList = null;
        
        // dataTrans에 따라 적절한 카테고리 목록을 가져옴
        if ("notice".equals(dataTrans)) {
            categoryList = pCategoryService.getNoticeCategoryList();
        } else if ("report".equals(dataTrans)) {
            categoryList = pCategoryService.getReportCategoryList();
        } else if ("faq".equals(dataTrans)) {
            categoryList = pCategoryService.getFaqCategoryList();
        } else if ("community".equals(dataTrans)) {
            categoryList = pCategoryService.getCommunityCategoryList();
        } else {
            // 유효하지 않은 dataTrans 값이 들어온 경우에 대한 처리
            // 여기서는 간단히 빈 목록을 반환하도록 함
            categoryList = Collections.emptyList();
        }
        
        // 반환할 데이터 설정
        response.put("categoryList", categoryList);
        
        return response;
    }
    

    
    
	
	
	/**
	// 카테고리 조회 페이지
	@GetMapping("/categoryList")
	public String faqList(Model model) {
		List<PCategory> faqCategoryList = pCategoryService.getFaqCategoryList();
		log.info("faqCategoryList:{}", faqCategoryList);
		model.addAttribute("title", "카테고리 조회");
		model.addAttribute("faqCategoryList", faqCategoryList);
		return "platform/board/categoryList";
	}
	*/
}
