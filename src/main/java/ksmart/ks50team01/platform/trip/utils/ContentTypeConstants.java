package ksmart.ks50team01.platform.trip.utils;

import java.util.HashMap;
import java.util.Map;

public class ContentTypeConstants {
	public static final Map<String, String> CONTENT_TYPE_MAP = new HashMap<>();
	static {
		CONTENT_TYPE_MAP.put("12", "관광지");
		CONTENT_TYPE_MAP.put("14", "문화시설");
		CONTENT_TYPE_MAP.put("15", "축제공연행사");
		CONTENT_TYPE_MAP.put("25", "여행코스");
		CONTENT_TYPE_MAP.put("28", "레포츠");
		CONTENT_TYPE_MAP.put("32", "숙박");
		CONTENT_TYPE_MAP.put("38", "쇼핑");
		CONTENT_TYPE_MAP.put("39", "음식점");
		// ... 나머지 코드
	}
}
