package spring;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;


@Getter
@Setter

// 회원 가입을 처리할 때 필요한 이메일, 암호, 이름 데이터를 담고 있는 클래스
public class RegisterRequest {
    @NotBlank
    @Email
    private String email;
    @Size(min = 6)
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String name;

    public boolean isPasswordEqualToConfirmPassword(){
        return password.equals(confirmPassword);
    }
}
