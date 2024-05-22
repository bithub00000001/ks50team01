package ksmart.ks50team01.platform.reivew.dto;

import lombok.Data;

@Data
public class PReviewBusiness {
    private String partnershipBusinessCode;     // 제휴사업장 코드
    private String businessAuthorityId;         // 사업자권한 아이디
    private String regionalSubcategoryCode;      // 지역소분류코드
    private String partnershipBrandCategoryCode; // 제휴브랜드 분류 코드
    private String regionalSubcategoryName;      // 지역 소분류명
    private String partnershipBrandName;         // 제휴브랜드명
    private String businessRegistrationNumber;   // 사업자등록번호
    private String businessName;                 // 사업자상호
    private String businessOwnerName;            // 사업자성명
    private String businessPhoneNumber;          // 사업장전화번호
    private String businessAddress;              // 사업장 주소
    private String businessDetailedAddress;      // 사업장 상세 주소
    private String paymentAvailability;          // 결제가능유무
    private String reservationAvailability;      // 예약가능 유뮤
    private String registrationDate;             // 등록일자
    private String modificationDate;             // 수정일자
}
