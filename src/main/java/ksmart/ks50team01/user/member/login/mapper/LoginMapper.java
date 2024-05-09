package ksmart.ks50team01.user.member.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ksmart.ks50team01.user.member.login.dto.Login;

@Mapper
public interface LoginMapper {
	
	Integer findPasswordByIdAndEmail(@Param("id") String id, @Param("email") String email);
	
    void updatePassword(@Param("id") String id, @Param("newPassword") String newPassword);

	public Login login(Login login);
	
	String findId(@Param("name") String name, @Param("phone") String phone);
	
	void joinMember(Login Login);
}
