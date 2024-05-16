package ksmart.ks50team01.platform.board.controller;

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
	
	// 게시판 종류에 해당하는 카테고리 조회
    @GetMapping("/categoryList")
    public String categoryList(String boardType, Model model) {
    	List<PCategory> categoryList = pCategoryService.getFaqCategoryList();
    	log.info("controller categoryList:{}", categoryList);
		model.addAttribute("title", "카테고리 조회");
		model.addAttribute("categoryList", categoryList);
    	return "platform/board/categoryList";
    }
    
    @PostMapping("/{dataTrans}")
    @ResponseBody
    public Map<String, Object> postMethodName(@PathVariable String dataTrans) {
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	responseMap.put("dataTrans", dataTrans);
    	List<?> dataList = null;
		if("noticeCateList".equals(dataTrans)) {
			dataList = pCategoryService.getNoticeCateList();
		}
		if(dataList != null) responseMap.put("data", dataList);
		return responseMap;
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
