package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
// 배열의 형태로 두 개 이상의 설정 클래스 Import 가능함
// @Import( {AppConf1.class, AppConf2.class} )
@Import(AppConf2.class)
public class AppConfImport {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
}
