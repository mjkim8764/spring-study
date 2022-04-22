package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }


    /**
     * 컨트롤러 구현 없는 경로 매핑 : WebMvcConfigurer 인터페이스의 addViewControllers 메서드
     * 단순 연결을 위해 특별한 로직이 없는 컨트롤러 클래스를 만드는 것보다
     * 아래처럼 간단하게 요청 경로와 뷰 이름을 연결하자.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
    }
}
