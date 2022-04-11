package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.*;

/**
 * @Component 를 붙인 클래스를 스캔하여 빈으로 등록하려면
 * 설정 클래스에 @ComponentScan 을 적용해야 함.
 *
 * excludeFilters : 스캔할 때 특정 대상을 등록 대상에서 제외함.
 * 다양한 Filtertype 적용 가능(정규식, 어노테이션, 클래스 등)
 *
 * @ComponentScan(basePackages = {"spring"},
 *         excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "spring\\...*Dao"))
 */

@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtx {

    /**
     * 현재 @Component 를 붙인 클래스는 아래와 같다.
     *   ChangePasswordService
     *   MemberDao
     *   MemberInfoPrinter
     *   MemberListPrinter
     *   MemberRegisterService
     *
     * @Component 를 활용하여 빈 설정 코드를 더 간결하게 만들었다.
     *
     */

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }


    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }


    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
