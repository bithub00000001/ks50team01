package ksmart.ks50team01.user.member.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ksmart.ks50team01.user.member.login.dto.Login;

@Mapper
public interface LoginMapper {

	public Login login(Login login);
	
	String findId(@Param("name") String name, @Param("phone") String phone);
}
