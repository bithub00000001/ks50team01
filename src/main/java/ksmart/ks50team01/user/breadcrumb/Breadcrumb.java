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

	public static Map<String, String> getPageNames(){
		Map<String, String> pageNames = new HashMap<>();
		pageNames.put("about", "About Us");
		pageNames.put("contact", "Contact Us");
		pageNames.put("trip", "여행 계획");
		pageNames.put("plan", "여행 계획 작성");
		// 필요한 만큼 추가
		return pageNames;
	}
}
