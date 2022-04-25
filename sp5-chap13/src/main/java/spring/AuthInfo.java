package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthInfo {

    private Long id;
    private String email;
    private String name;

}
