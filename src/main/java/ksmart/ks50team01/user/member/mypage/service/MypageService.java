package ksmart.ks50team01.user.member.mypage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.member.mypage.dto.Mypage;
import ksmart.ks50team01.user.member.mypage.mapper.MypageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MypageService {
	
	private final MypageMapper mypageMapper;
	
	public int delMember(String memberId) {
		int delMember = mypageMapper.delMember(memberId);
		
		return delMember;
	}
	
	public int updateMember(Mypage mypage) {
		return mypageMapper.updateMember(mypage);
	}
	
	public Mypage getMemberInfoById(String memberId) {
		Mypage memberInfo = mypageMapper.getMemberInfoById(memberId);
	    
	    if (memberInfo != null) {
	        String memberGrdNum = memberInfo.getMemberGrdNum();
	        String memberLevelName = null;
	        switch (memberGrdNum) {
	            case "uln_001" -> memberLevelName = "플랫폼 운영 담당자";
	            case "uln_101" -> memberLevelName = "개인사업자";
	            case "uln_301" -> memberLevelName = "회원";
	        }
	        memberInfo.setMemberLevelName(memberLevelName);
	    }
	    
	    return memberInfo;
	}
	
	public List<Mypage> getMemberGrade() {
		List<Mypage> memberGrade = mypageMapper.getMemberGrade();
		
		return memberGrade;
	}

}
