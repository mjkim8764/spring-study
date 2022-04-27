package controller;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Getter
@Setter

public class LoginCommand {

    @NotBlank
    @Email
    private String email;
    @Size(min = 6)
    private String password;
    private boolean rememberEmail;

    public boolean isRememberEmail() {
        return rememberEmail;
    }
}
