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
	
	public boolean findPasswordByIdAndEmail(String id, String email) {
	    return loginMapper.findPasswordByIdAndEmail(id, email) > 0;
	}

	public String generateAndUpdatePassword(String id) {
	    String newPassword = generateRandomPassword();
	    loginMapper.updatePassword(id, newPassword);
	    return newPassword;
	}

	private String generateRandomPassword() {
	    // 1~9, A~Z 중 랜덤으로 8자리 비밀번호 생성 로직
		return PasswordGenerator.generateRandomPassword();
	}
	
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
