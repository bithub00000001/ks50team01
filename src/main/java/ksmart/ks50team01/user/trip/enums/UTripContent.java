package ksmart.ks50team01.user.trip.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UTripContent {
	CONTENT_TYPE_ID("tour_detail_content_type_id", 2),
	CONTENT_ID("tour_detail_content_id", 4);

	private final String value;
	private final int length;

	public static String getUTripContentByLength(String input) {
		int inputLength = input.length();
		if (inputLength == 2) {
			return CONTENT_TYPE_ID.value;
		} else if (inputLength > 3) {
			return CONTENT_ID.value;
		} else {
			throw new IllegalArgumentException("잘못된 값이 사용됩니다.");
		}
	}
}
