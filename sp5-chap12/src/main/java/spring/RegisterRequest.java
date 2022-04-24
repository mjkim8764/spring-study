package spring;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter

// 회원 가입을 처리할 때 필요한 이메일, 암호, 이름 데이터를 담고 있는 클래스
public class RegisterRequest {

    // @NotBlank(message = "필수 항목입니다. 공백 문자는 허용하지 않습니다.")
    @NotBlank
    // @Email(message = "올바른 이메일 주소를 입력해야 합니다.")
    @Email
    private String email;
    // @Size(min = 6, message = "암호 길이는 6자 이상이어야 합니다.")
    @Size(min = 6)
    private String password;
    // @NotEmpty(message = "필수 항목입니다.")
    @NotEmpty
    private String confirmPassword;
    // @NotEmpty(message = "필수 항목입니다.")
    @NotEmpty
    private String name;

    public boolean isPasswordEqualToConfirmPassword(){
        return password.equals(confirmPassword);
    }
}
