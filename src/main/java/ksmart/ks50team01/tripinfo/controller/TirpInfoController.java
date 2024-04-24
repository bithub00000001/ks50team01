package ksmart.ks50team01.tripinfo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.tripinfo.dto.TripInfo;
import ksmart.ks50team01.tripinfo.service.TripInfoService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/tripinfo")
@RequiredArgsConstructor
public class TirpInfoController {
	
	private final TripInfoService tripInfoService;
	
	@GetMapping("/tripinfo")
	public String getTripInfo(Model model) {
		
		List<TripInfo> tripinfo = tripInfoService.getTripInfo();
		
		model.addAttribute("title", "여행지정보");
		model.addAttribute("tripinfo", tripinfo);
		
		return "tripinfo/tripinfo";
	}

}
