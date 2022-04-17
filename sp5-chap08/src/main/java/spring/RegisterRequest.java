package spring;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

// 회원 가입을 처리할 때 필요한 이메일, 암호, 이름 데이터를 담고 있는 클래스
public class RegisterRequest {

    private String email;

    private String password;

    private String confirmPassword;

    private String name;

    public boolean isPasswordEqualToConfirmPassword(){
        return password.equals(confirmPassword);
    }
}
