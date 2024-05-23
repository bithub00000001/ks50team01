/*
 * package ksmart.ks50team01.user.review.service;
 * 
 * import java.util.Comparator; import java.util.List; import
 * java.util.stream.Collectors;
 * 
 * import org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import ksmart.ks50team01.user.review.dto.UComment; import
 * ksmart.ks50team01.user.review.dto.UReview; import
 * ksmart.ks50team01.user.review.mapper.UReviewMapper; import
 * lombok.RequiredArgsConstructor;
 * 
 * @Service
 * 
 * @Transactional
 * 
 * @RequiredArgsConstructor public class UReviewTyService {
 * 
 * private final UReviewMapper uReviewMapper;
 * 
 *//**
	 * 댓글 목록
	 */
/*
 * public List<UReview> getUReviewList(String sortOption){ List<UReview>
 * uReviewList = uReviewMapper.getUReview();
 * 
 * 
 * if (uReviewList != null) { uReviewList.forEach(review -> { String
 * reviewApprove = review.getReviewApprove(); String reviewApproveName = "";
 * String reviewStar = review.getReviewStar(); int reviewStarPoint = 0; switch
 * (reviewApprove) { case "DISCLOSURE_001": reviewApproveName = "전체공개"; break;
 * case "DISCLOSURE_002": reviewApproveName = "나만공개"; break; }
 * review.setReviewApproveName(reviewApproveName); switch (reviewStar) { case
 * "RAT_CTGRY_01": reviewStarPoint = 1; break; case "RAT_CTGRY_02":
 * reviewStarPoint = 2; break; case "RAT_CTGRY_03": reviewStarPoint = 3; break;
 * case "RAT_CTGRY_04": reviewStarPoint = 4; break; case "RAT_CTGRY_05":
 * reviewStarPoint = 5; break; } review.setReviewStarPoint(reviewStarPoint); });
 * 
 * 
 * // 정렬 로직 추가 switch (sortOption) { case "최신순": uReviewList =
 * uReviewList.stream()
 * .sorted(Comparator.comparing(UReview::getReviewDate).reversed())
 * .collect(Collectors.toList()); break; case "오래된순": uReviewList =
 * uReviewList.stream() .sorted(Comparator.comparing(UReview::getReviewDate))
 * .collect(Collectors.toList()); break; case "좋아요순": uReviewList =
 * uReviewList.stream() .sorted(Comparator.comparingInt((UReview review) ->
 * Integer.parseInt(review.getReviewLike())).reversed())
 * .collect(Collectors.toList()); break; case "추천순": uReviewList =
 * uReviewList.stream() .sorted((UReview a, UReview b) -> { int likeA =
 * Integer.parseInt(a.getReviewLike()); int dislikeA =
 * Integer.parseInt(a.getReviewDislike()); int likeB =
 * Integer.parseInt(b.getReviewLike()); int dislikeB =
 * Integer.parseInt(b.getReviewDislike());
 * 
 * double ratioA = likeA / (double) (likeA + dislikeA); double ratioB = likeB /
 * (double) (likeB + dislikeB); return Double.compare(ratioB, ratioA); })
 * .collect(Collectors.toList()); break; default: break; } }
 * 
 * return uReviewList; }
 * 
 * 
 *//**
	 * 답글 목록
	 *//*
		 * public List<UComment> getUComment(){ return uReviewMapper.getUComment(); }
		 * 
		 * 
		 * }
		 */