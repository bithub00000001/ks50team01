package ksmart.ks50team01.platform.member.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.member.management.dto.PMember;

@Mapper
public interface MemberMapper {
	
	// 회원 정보 수정
	int updateMember(PMember member);
	
	// 회원탈퇴
	int delMember(String memberId);
	
	// 회원목록조회
	List<PMember> getMemberList();
	
	// 회원정보조회
	PMember getMemberInfoById(String memberId);
	
	// 회원등급조회
	List<PMember> getMemberGrade();
	
	// 회원등급업데이트
	int updateMemberGrade(String memberId, String memberGrdNum);
	
	
}
