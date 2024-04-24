package ksmart.ks50team01.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ksmart.ks50team01.member.dto.Member;
import ksmart.ks50team01.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper memberMapper;
	
	public List<Member> getmemberList(){
		return memberMapper.getMemberList();
	}
}
