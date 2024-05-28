package ksmart.ks50team01.user.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.member.login.dto.Login;

@Mapper
public interface UTripPlanMapper {
	// JSON 객체를 하나의 String으로 합친 목록을 반환
	List<String> getTourInfoObject(String colName, String content);

	// 회원 중 일반 회원 목록만 조회하는 메서드
	List<Login> getUserMembers();

	// 회원 중 일반 회원이며 닉네임과 일치하는 목록 조
	List<Login> searchUserMembers(String nickname);
}
