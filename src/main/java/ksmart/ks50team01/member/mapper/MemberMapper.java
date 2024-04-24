package ksmart.ks50team01.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.member.dto.Member;

@Mapper
public interface MemberMapper {
	List<Member> getMemberList();
}
