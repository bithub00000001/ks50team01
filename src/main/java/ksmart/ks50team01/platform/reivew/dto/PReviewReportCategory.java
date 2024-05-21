package ksmart.ks50team01.platform.reivew.dto;
import lombok.Data;

@Data
public class PReviewReportCategory {
    private String reportCategoryId; //신고분류번호
    private String registeredPlatformAuthorityId; //등록플랫폼권한아이디
    private String reportCategoryType; //신고분류유형
    private String modifiedPlatformAuthorityId; //수정플랫폼권한아이디
    private String isActive; //활성화여부
    private String registrationDate; //등록일자
    private String modificationDate; //수정일자
}
