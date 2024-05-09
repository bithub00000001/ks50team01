package ksmart.ks50team01.user.member.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.member.mypage.dto.Mypage;

@Mapper
public interface MypageMapper {

	// 회원정보조회
	Mypage getMemberInfoById(String memberId);
	
	// 회원등급조회
	List<Mypage> getMemberGrade();
	
	// 회원 정보 수정
	int updateMember(Mypage mypage);
	
	// 회원탈퇴
	int delMember(String memberId);
}
