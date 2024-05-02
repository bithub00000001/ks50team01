package ksmart.ks50team01.platform.member.management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.member.management.dto.Member;
import ksmart.ks50team01.platform.member.management.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	
	public List<Member> getMemberList(){
		List<Member> memberList = memberMapper.getMemberList();
		
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

	public Member getMemberInfoById(String memberId) {
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		
		return memberInfo;
	}
	
	public Member memberCount() {
		Member memberCount = memberMapper.memberCount();
		
		return memberCount;
	}
}
