package ksmart.ks50team01.user.member.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

	private String id;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String phone;
}
