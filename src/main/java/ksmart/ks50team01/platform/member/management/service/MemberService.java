package ksmart.ks50team01.platform.member.management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.member.management.dto.PMember;
import ksmart.ks50team01.platform.member.management.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	
	public int updateMember(PMember member) {
		return memberMapper.updateMember(member);
	}
	
	
	public int delMember(String memberId) {
		int delMember = memberMapper.delMember(memberId);
		
		return delMember;
	}
	
	public int updateMemberGrade(String memberId, String gradeNum) {
		int updateMemberGrade = memberMapper.updateMemberGrade(memberId, gradeNum);
		
		return updateMemberGrade;
	}
	
	public List<PMember> getMemberList(){
		List<PMember> memberList = memberMapper.getMemberList();
		
		if(memberList != null) {
			memberList.forEach(member -> {
				String memberGrdNum = member.getMemberGrdNum();
				String memberLevelName = null;
				switch (memberGrdNum) {
					case "uln_001" -> memberLevelName = "플랫폼 운영 담당자";
					case "uln_101" -> memberLevelName = "개인사업자";
					case "uln_301" -> memberLevelName = "회원";		
				}
				member.setMemberLevelName(memberLevelName);
			});
		}
		
		return memberList;
	}

	public PMember getMemberInfoById(String memberId) {
	    PMember memberInfo = memberMapper.getMemberInfoById(memberId);
	    
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
	
	public List<PMember> getMemberGrade() {
		List<PMember> memberGrade = memberMapper.getMemberGrade();
		
		return memberGrade;
	}
}
