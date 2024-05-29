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
	
	/*
	// 게시판 종류에 해당하는 카테고리 조회
	@GetMapping("/categoryList")
	public String categoryList(String boardType, Model model) {
		List<PCategory> categoryList = pCategoryService.getFaqCategoryList();
		log.info("controller categoryList:{}", categoryList);
		model.addAttribute("title", "카테고리 조회");
		model.addAttribute("categoryList", categoryList);
		return "platform/board/categoryList";
	}
	*/
	
	
	
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
    
	
	@PostMapping("/categoryList/{dataTrans}")
    @ResponseBody
    public Map<String, Object> categoryList(@PathVariable String dataTrans, Model model) {
        Map<String, Object> responseMap = new HashMap<>();
        List<?> categoryList;
        
        // dataTrans에 따라 적절한 카테고리 목록을 가져옴
        if ("noticeCateList".equals(dataTrans)) {
            categoryList = pCategoryService.getNoticeCategoryList();
        } else if ("reportCateList".equals(dataTrans)) {
            categoryList = pCategoryService.getReportCategoryList();
        } else if ("faqCateList".equals(dataTrans)) {
            categoryList = pCategoryService.getFaqCategoryList();
        } else if ("communityCateList".equals(dataTrans)) {
            categoryList = pCategoryService.getCommunityCategoryList();
        } else {
            // 유효하지 않은 dataTrans 값이 들어온 경우에 대한 처리
            // 여기서는 간단히 빈 목록을 반환하도록 함
            categoryList = Collections.emptyList();
        }
        
        
        // 반환할 데이터 설정
        responseMap.put("categoryList", categoryList);
        responseMap.put("dataTrans", dataTrans);
        
        // 모델에 데이터 추가
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("dataTrans", dataTrans);
        
        log.info("categoryList: {}", categoryList);
        
        return responseMap;
    }
	
    @GetMapping("/platform/board/categoryList/{dataTrans}")
    public String getCategoryList(@PathVariable String dataTrans) {
        // 여기에 카테고리 목록을 로드하고 해당하는 뷰 이름을 반환하는 코드를 작성합니다.
        // 이 예시에서는 간단하게 뷰 이름을 반환하도록 합니다.
        return "categoryList"; // 실제로는 해당 뷰의 이름을 반환해야 합니다.
    }

	
	/*
	@GetMapping("/{dataTrans}")
	@ResponseBody
    public Map<String, Object> getCategoryList(@PathVariable String dataTrans) {
        Map<String, Object> response = new HashMap<>();
        response.put("dataTrans", dataTrans);
        List<PCategory> categoryList = null;

        // dataTrans 값에 따라 적절한 카테고리 목록을 가져옴
        switch (dataTrans) {
            case "noticeCateList":
                categoryList = pCategoryService.getNoticeCategoryList();
                break;
            case "reportCateList":
                categoryList = pCategoryService.getReportCategoryList();
                break;
            case "faqCateList":
                categoryList = pCategoryService.getFaqCategoryList();
                break;
            case "communityCateList":
                categoryList = pCategoryService.getCommunityCategoryList();
                break;
            default:
                categoryList = Collections.emptyList();
                break;
        }

        // 응답 데이터 설정
        response.put("categoryList", categoryList);
        return response;
    }*/
	
	
	/*
    @GetMapping("/noticeCateList")
    @ResponseBody
    public Map<String, Object> getNoticeCategoryList() {
        Map<String, Object> response = new HashMap<>();
        List<PCategory> noticeCateList = pCategoryService.getNoticeCategoryList();
        response.put("noticeCateList", noticeCateList);
        return response;
    }
    

    

    @GetMapping("/reportCateList")
    @ResponseBody
    public Map<String, Object> getReportCategoryList() {
        Map<String, Object> response = new HashMap<>();
        List<PCategory> reportCateList = pCategoryService.getReportCategoryList();
        response.put("reportCateList", reportCateList);
        return response;
    }

    @GetMapping("/faqCateList")
    @ResponseBody
    public Map<String, Object> getFaqCategoryList() {
        Map<String, Object> response = new HashMap<>();
        List<PCategory> faqCateList = pCategoryService.getFaqCategoryList();
        response.put("faqCateList", faqCateList);
        return response;
    }

    @GetMapping("/communityCateList")
    @ResponseBody
    public Map<String, Object> getCommunityCategoryList() {
        Map<String, Object> response = new HashMap<>();
        List<PCategory> communityCateList = pCategoryService.getCommunityCategoryList();
        response.put("communityCateList", communityCateList);
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
