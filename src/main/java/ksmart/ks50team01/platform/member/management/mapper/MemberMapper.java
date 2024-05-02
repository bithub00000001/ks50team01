package ksmart.ks50team01.platform.member.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.member.management.dto.Member;

@Mapper
public interface MemberMapper {
	
	// 회원목록조회
	List<Member> getMemberList();
	
	// 회원정보조회
	Member getMemberInfoById(String memberId);
	
	// 회원등급조회
	List<Member> getMemberGrade();
	
	// 회원등급업데이트
	Member updateMemberGrade(String memberId, String gradeNum);
	
}
