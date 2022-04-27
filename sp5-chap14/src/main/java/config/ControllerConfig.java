package config;

import controller.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration
@RequiredArgsConstructor
public class ControllerConfig {

    // @Autowired
    // private MemberRegisterService memberRegSvc;

    // 필드 주입 -> 생성자 주입으로 변경해봄. (@RequiredArgsConstructor & final)
    private final MemberRegisterService memberRegSvc;
    private final AuthService authService;
    private final ChangePasswordService changePasswordService;
    private final MemberDao memberDao;

    @Bean
    public RegisterController registerController() {
        RegisterController controller = new RegisterController();
        controller.setMemberRegisterService(memberRegSvc);
        return controller;
    }

    @Bean
    public LoginController loginController() {
        LoginController controller = new LoginController();
        controller.setAuthService(authService);
        return controller;
    }

    @Bean
    public LogoutController logoutController() {
        return new LogoutController();
    }

    @Bean
    public ChangePwdController changePwdController() {
        ChangePwdController controller = new ChangePwdController();
        controller.setChangePasswordService(changePasswordService);
        return controller;
    }

    @Bean
    public MemberListController memberListController(){
        MemberListController controller = new MemberListController();
        controller.setMemberDao(memberDao);
        return controller;
    }

}
