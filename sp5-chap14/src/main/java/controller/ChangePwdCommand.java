package controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ChangePwdCommand {

    @Size(min = 6)
    private String currentPassword;
    @Size(min = 6)
    private String newPassword;

}
