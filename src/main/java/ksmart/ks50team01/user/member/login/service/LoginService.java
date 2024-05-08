package ksmart.ks50team01.user.member.login.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.member.login.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

	private final LoginMapper loginMapper;
	
	public void joinMember(Login Login) {
        loginMapper.joinMember(Login);
    }
	    
	public Login login(Login login) {
	    return loginMapper.login(login);
	}
	
	public String findId(String name, String phone) {
	    return loginMapper.findId(name, phone);
	}
}