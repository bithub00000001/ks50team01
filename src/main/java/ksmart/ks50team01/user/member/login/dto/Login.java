package ksmart.ks50team01.user.member.login.dto;

import lombok.Data;


@Data
public class Login {

	private String id;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private String level;
}
