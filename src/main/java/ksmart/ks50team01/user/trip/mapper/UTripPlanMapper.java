package ksmart.ks50team01.user.trip.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.trip.dto.UTripOption;

@Mapper
public interface UTripPlanMapper {
	// JSON 객체를 하나의 String으로 합친 목록을 반환
	List<String> getTourInfoObject(String colName, String content);

	// 회원 중 일반 회원 목록만 조회하는 메서드
	List<Login> getUserMembers();

	// 회원 중 일반 회원이며 닉네임과 일치하는 목록 조회
	List<Login> searchUserMembers(String nickname);

	// 컨텐트 ID와 일치하는 위,경도 조회
	Map<String, Object> getMapXY(String contentId);

	// 임시 상태로 여행 계획을 저장
	int addTempPlanInfo(UTripOption uTripOption);
}
