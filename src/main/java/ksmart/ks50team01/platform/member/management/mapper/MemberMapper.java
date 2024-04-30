package ksmart.ks50team01.platform.member.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.member.management.dto.Member;

@Mapper
public interface MemberMapper {

	List<Member> getMemberList();
}
