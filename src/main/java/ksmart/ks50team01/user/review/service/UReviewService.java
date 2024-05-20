package ksmart.ks50team01.user.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ksmart.ks50team01.user.review.dto.UOpen;
import ksmart.ks50team01.user.review.dto.UReview;
import ksmart.ks50team01.user.review.dto.UReviewComment;
import ksmart.ks50team01.user.review.dto.UReviewFile;
import ksmart.ks50team01.user.review.mapper.UReviewMapper;
import ksmart.ks50team01.user.review.util.FileUtil;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UReviewService {
	
	private final UReviewMapper uReviewMapper;

	private final FileUtil fileUtil;
	
	/*
	public UReviewService(FileUtil fileUtil, UReviewMapper uReviewMapper) {
		this.fileUtil = fileUtil;
		this.uReviewMapper = uReviewMapper;
	}
	*/
	
	/**
	 *  파일 업로드 
	 * @param uploadfile
	 */
	public void fileUpload(MultipartFile[] uploadfile) {
		
		List<UReviewFile> fileList= fileUtil.parseFileInfo(uploadfile);
		
		// 파일리스트 db저장
		if(fileList != null) uReviewMapper.addFile(fileList);
		
	}
	
	/**
	 * 파일목록
	 * @return
	 */
	public List<UReviewFile> getFileList(){
		List<UReviewFile> fileList = uReviewMapper.getFileList();
		
		return fileList;
	}
	
	/**
	 * 특정 파일의 idx 가져오기
	 * @param fileIdx
	 * @return
	 */
	public UReviewFile getFileInfoByIdx(String fileIdx) {
		return uReviewMapper.getFileInfoByIdx(fileIdx);
	}
	
	/**
	 * 특정 파일 삭제
	 * @param fileIdx
	 * @return
	 */
	public void deleteFileByIdx(UReviewFile fileDto) {
		Boolean isDelete = fileUtil.deleteFileByIdx(fileDto);
		if(isDelete) uReviewMapper.deleteFileByIdx(fileDto.getReviewFileCode());
	}
	
	
	/**
	 * 리뷰 수정
	 */
	public void modifyReview(UReview review) {
		uReviewMapper.modifyReview(review);
	}
	
	/**
	 * 리뷰정보조회
	 */
	public UReview getReviewDetail(String reviewId) {
		UReview reviewDetail = uReviewMapper.getReviewDetail(reviewId);
		return reviewDetail;
	}
	
	/**
	 * 리뷰등록
	 */
	public void reviewWrite(UReview review) {
        // 가장 큰 PRCHS_REV_CD 값을 조회
        String maxPrchsRevCd = uReviewMapper.getMaxPrchsRevCd();
        String newPrchsRevCd;

        if (maxPrchsRevCd != null) {
            // 숫자 부분만 추출해서 1을 더하고 다시 문자열로 변환
            int maxNumber = Integer.parseInt(maxPrchsRevCd.substring(10)) + 1;
            newPrchsRevCd = "PRCHS_REV_" + maxNumber;
        } else {
            // 테이블이 비어있는 경우 첫 번째 값 설정
            newPrchsRevCd = "PRCHS_REV_1";
        }

        // 새 PRCHS_REV_CD 값을 review 객체에 설정
        review.setReviewCode(newPrchsRevCd);
        
		uReviewMapper.reviewWrite(review);
	}
	
	
	/**
	 * 공개여부 조회
	 */
	public List<UOpen> getUOpen(){
		return uReviewMapper.getUOpen();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 댓글 목록
	 */

	    public List<UReview> getUReviewList() {
	        List<UReview> uReviewList = uReviewMapper.getUReview();
	        
	        if (uReviewList != null) {
	            for (UReview review : uReviewList) {
	                String reviewApprove = review.getReviewApprove();
	                String reviewApproveName = getReviewApproveName(reviewApprove);
	                review.setReviewApproveName(reviewApproveName);

	                String reviewStar = review.getReviewStar();
	                int reviewStarPoint = getReviewStarPoint(reviewStar);
	                review.setReviewStarPoint(reviewStarPoint);
	            }
	        }
	        
	        return uReviewList;
	    }

	    private String getReviewApproveName(String reviewApprove) {
	        switch (reviewApprove) {
	            case "DISCLOSURE_001":
	                return "전체공개";
	            case "DISCLOSURE_002":
	                return "나만공개";
	            default:
	                return "알 수 없음";
	        }
	    }

	    private int getReviewStarPoint(String reviewStar) {
	        switch (reviewStar) {
	            case "RAT_CTGRY_01":
	                return 1;
	            case "RAT_CTGRY_02":
	                return 2;
	            case "RAT_CTGRY_03":
	                return 3;
	            case "RAT_CTGRY_04":
	                return 4;
	            case "RAT_CTGRY_05":
	                return 5;
	            default:
	                return 0;
	        }
	    }
	/*
	public List<UReview> getUReviewList(){
		List<UReview> uReviewList = uReviewMapper.getUReview();
		
		if (uReviewList != null) {
			uReviewList.forEach(review -> {
				String reviewApprove = review.getReviewApprove();
				String reviewApproveName = "";
				String reviewStar = review.getReviewStar();
				int reviewStarPoint = 0;
				switch (reviewApprove) {
				case "DISCLOSURE_001":
					reviewApproveName = "전체공개";
					break;
				case "DISCLOSURE_002":
					reviewApproveName = "나만공개";
					break;
				}
				review.setReviewApproveName(reviewApproveName);
				switch (reviewStar) {
				case "RAT_CTGRY_01":
					reviewStarPoint = 1;
					break;
				case "RAT_CTGRY_02":
					reviewStarPoint = 2;
					break;
				case "RAT_CTGRY_03":
					reviewStarPoint = 3;
					break;
				case "RAT_CTGRY_04":
					reviewStarPoint = 4;
					break;
				case "RAT_CTGRY_05":
					reviewStarPoint = 5;
					break;
				}
				review.setReviewStarPoint(reviewStarPoint);
			});
		}
		
		return uReviewList;
	}
	*/
	
	/**
	 * 답글 목록
	 */
	public List<UReviewComment> getUReveiwComment(){
		return uReviewMapper.getUReviewComment();
	}
	
	
}
