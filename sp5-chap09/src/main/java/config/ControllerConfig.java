package config;

import chap09.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    // 컨트롤러를 빈으로 등록
    @Bean
    public HelloController helloController() {
        return new HelloController();
    }
}
