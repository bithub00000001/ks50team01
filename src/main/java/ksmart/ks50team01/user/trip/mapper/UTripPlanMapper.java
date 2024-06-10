package ksmart.ks50team01.user.trip.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ksmart.ks50team01.platform.trip.dto.PTourDetail;
import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.trip.dto.UTripOption;
import ksmart.ks50team01.user.trip.dto.UTripPlanItem;

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

	// UUID로 임시 저장된 여행 계획 조회
	UTripOption selectUTripOptionByUUID(String planUUID);

	// 여행 계획에 관련된 모든 가상 회원 삭제
	void deleteTripVirtualMembers(String tripUuid);

	// 여행 계획에 관련된 모든 실제 회원 삭제
	void deleteTripRealMembers(String tripUuid);

	// 여행 계획에 가상 회원 추가
	void insertTripVirtualMember(String tripUuid, String virtualMemberId);

	// 여행 계획에 실제 회원 추가
	void insertTripRealMember(String tripUuid, String realMemberId);

	// 여행 계획 업데이트
	void updateTempPlanInfo(UTripOption uTripOption);

	// 가상 회원 ID 조회
	Integer selectVirtualMemberIdByName(String name);

	// 실제 회원 ID 조회
	Integer selectRealMemberIdByMemberId(String memberId);

	// 가상 회원 추가
	void insertVirtualMember(String name);

	// 실제 회원 추가
	void insertRealMember(String memberId);

	// 여행 계획 목록 조회
	List<UTripOption> selectTempPlanListBySessionId(String sessionId);

	// Tour API에서 DB에 저장한 여행 상세 정보 목록 조회
	List<Map<String, Object>> getTourDataList();

	// UUID와 일치하는 여행 계획이 존재하는지 조회
	boolean existsByPlanUUID(String planUUID);

	// UUID와 일치하는 여행 계획을 삭제
	int deleteDetailInfo(String planUUID);

	// UUID와 여행 계획을 저장
	int insertDetailInfo(
		@Param("planUUID") String planUUID,
		@Param("dayNumber") int dayNumber,
		@Param("contentId") String contentId,
		@Param("order") int order);

	// UUID와 일치하는 여행 계획을 조회
	UTripOption getTripPlanByUUID(String planUUID);

	// UUID와 일치하는 여행 계획 세부 아이템 조회
	List<UTripPlanItem> getTripItemsByUUID(String planUUID);

	// 컨텐트 ID와 일치하는 여행 상세 정보 조회
	List<PTourDetail> getPTourDetailByContentId(List<String> contentIds);

	// UUID와 일치하는 여행에 초대받은 실제 회원 조회
	List<String> getRealMembersByPlanUUID(String planUUID);

	// UUID와 일치하는 여행에 초대받은 가상 회원 조회
	List<String> getVirtualMembersByPlanUUID(String planUUID);
}
