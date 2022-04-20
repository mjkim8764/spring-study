package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * EnableWebMvc : 스프링 MVC 설정 활성화
 * 내부적으로 다양한 빈 설정을 추가해줌.
 *
 * WebMvcConfigurer : 스프링 MVC 의 개별 설정을 조정할 때 사용하는 인터페이스
 */


@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    /**
     * DispatcherServlet 의 매핑 경로를 '/' 로 주었을 때,
     * JSP/HTML/CSS 등을 올바르게 처리하기 위한 설정
     */

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * JSP 를 이용해서 컨트롤러의 실행 결과를 보여주기 위한 설정
     */

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

}
