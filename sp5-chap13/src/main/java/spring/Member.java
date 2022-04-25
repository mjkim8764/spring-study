package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

public class Member {

    // id : 회원 아이디
    private Long id;

    // email : 회원 이메일
    private String email;

    // password : 회원 비밀번호
    private String password;

    // name : 회원 이름
    private String name;

    // registerDateTime : 가입날짜
    private LocalDateTime registerDateTime;

    public Member(String email, String password, String name, LocalDateTime regDateTime){
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = regDateTime;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if(!password.equals(oldPassword))
            throw new WrongIdPasswordException();
        this.password = newPassword;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

}
