package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    /*
    @Override
    public Validator getValidator() {
        return new RegisterRequestValidator();
    }
    */

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
    }


    // 빈의 아이디를 messageSource 로 하지 않으면 정상적으로 동작하지 않음.

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();

        // message 패키지에 속한 label 프로퍼티 파일로부터 메세지를 읽어온다고 설정
        ms.setBasename("message.label");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
