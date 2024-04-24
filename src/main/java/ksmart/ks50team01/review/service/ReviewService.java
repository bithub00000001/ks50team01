package ksmart.ks50team01.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ksmart.ks50team01.review.dto.Review;
import ksmart.ks50team01.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewMapper reviewMapper;
	
	public List<Review> getReivewList(){
		
		return reviewMapper.getReivewList();
	}
}
