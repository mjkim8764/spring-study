package config;

import interceptor.AuthCheckInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasenames("message.label");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }


    /**
     * 구현한 HandleInterceptor 를 어디에 적용할지 설정.
     * authCheckInterceptor 객체를 인터셉터로 설정 후
     * addPathPatterns 로 적용할 경로 패턴을 지정하였다.
     *
     * 이 경로는 Ant 경로 패턴을 사용하였는데 각 문자는 아래의 의미를 갖는다.
     *  * : 0개 또는 그 이상의 글자
     *  ? : 1개 글자
     *  ** : 0개 또는 그 이상의 폴더 경로
     *
     *  addPathPatterns 에 지정한 경로 패턴 중 일부를 제외하고 싶다면
     *  뒤에 excludePathPatterns 메서드를 사용하면 된다.
     *  제외하고 싶은 경로가 2개 이상이면 각 경로 패턴을 콤마로 구분.
     *
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authCheckInterceptor())
                .addPathPatterns("/edit/**");
    }

    @Bean
    public AuthCheckInterceptor authCheckInterceptor() {
        return new AuthCheckInterceptor();
    }

}
