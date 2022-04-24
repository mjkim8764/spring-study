package config;

import controller.RegisterController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberRegisterService;
import survey.SurveyController;

@Configuration
@RequiredArgsConstructor
public class ControllerConfig {

    // @Autowired
    // private MemberRegisterService memberRegSvc;

    // 필드 주입 -> 생성자 주입으로 변경해봄. (@RequiredArgsConstructor & final)
    private final MemberRegisterService memberRegSvc;

    @Bean
    public RegisterController registerController() {
        RegisterController controller = new RegisterController();
        controller.setMemberRegisterService(memberRegSvc);
        return controller;
    }

    @Bean
    public SurveyController surveyController() {
        return new SurveyController();
    }

}
