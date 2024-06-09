package ksmart.ks50team01.user.breadcrumb;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Breadcrumb {
	private String name;
	private String url;

	public Breadcrumb(String path, String url) {
		this.name = path;
		this.url = url;
	}

	// breadcrumb 하드코딩으로 저장
	public static Map<String, String> getPageNames(){
		Map<String, String> pageNames = new HashMap<>();
		pageNames.put("about", "About Us");
		pageNames.put("contact", "Contact Us");
		pageNames.put("trip", "여행 계획");
		pageNames.put("plan", "여행 계획 작성");
		pageNames.put("tourInfo", "여행지 정보 조회");
		pageNames.put("schedule", "여행 계획 상세 조회");
		pageNames.put("list", "내 여행 계획 목록");
		pageNames.put("detail", "여행 계획 작성");
		pageNames.put("board", "여행 계획 공유 게시판");
		pageNames.put("shareBoard", "여행 계획 공유");
		pageNames.put("allRanking", "여행 추천");
		pageNames.put("rankingList", "플랫폼 추천");
		pageNames.put("userRankingList", "회원추천");
		pageNames.put("planRankingList", "여행계획 추천");


		// 필요한 만큼 추가
		return pageNames;
	}
}
