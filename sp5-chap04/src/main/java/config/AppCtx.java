package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    // 생성자를 통한 의존 객체 주입
    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService();
        // return new MemberRegisterService(memberDao());
    }

    // setter 를 통한 의존 객체 주입
    @Bean
    public ChangePasswordService changePwdSvc() {
        return new ChangePasswordService();


        // ChangePasswordService pwdSvc = new ChangePasswordService();

        /**
         * ChangePasswordService 클래스의 MemberDao 필드에 Autowired 가 설정되어 있으므로,
         * setter 를 이용한 수동 주입을 할 필요가 없다. 자동 주입이 된다.
         */
        // pwdSvc.setMemberDao(memberDao());

        // return pwdSvc;
    }

    // @Bean
    // public MemberPrinter memberPrinter() {
    //    return new MemberPrinter();
    // }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }


    /**
     * MemberSummaryPrinter ---extends---> MemberPrinter
     * memberPrinter2 메서드는 MemberSummaryPrinter 타입의 빈 객체를 주입할 것 같지만
     * MemberSummaryPrinter 는 MemberPrinter 를 상속하기 때문에 문제가 될 수 있다.
     *
     * MemberSummaryPrinter 클래스는 MemberPrinter 타입에도 할당할 수 있으므로,
     * MemberPrinter 타입의 빈을 자동 주입해야 하는 @Autowired 를 만나면
     * memberPrinter1, memberPrinter2 중 어떤 빈 객체를 주입할 지 결정할 수 없기 때문에 Exception 발생.
     *
     * 방안
     *   1. Qualifier 를 확실히 구분짓기
     *   2. MemberSummaryPrinter 타입 빈은 한 개만 존재하므로
     *   MemberSummaryPrinter 빈을 자동 주입 받도록 설정하면 됨.
     */

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }



    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter();
        // return new MemberListPrinter(memberDao(), memberPrinter());
    }

    @Bean
    public MemberInfoPrinter infoPrinter() {
        return new MemberInfoPrinter();


        // MemberInfoPrinter infoPrinter = new MemberInfoPrinter();


        /**
         * MemberInfoPrinter 클래스의
         * setMemberDao, setPrinter 메서드에
         * Autowired 가 붙었기 때문에 setter 로 수동 주입을 할 필요가 없음.
         */

        // infoPrinter.setMemberDao(memberDao());
        // infoPrinter.setPrinter(memberPrinter());
        // return infoPrinter;




        /**
         * 아래 코드는 MemberSummaryPrint 타입의 빈을 setter 로 수동 주입하고 있다.
         * 하지만 MemberInfoPrinter.setPrinter 메서드는
         * @Autowired, @Qualifier("printer")  이므로,
         * 자동 주입을 따라가 한정자가 printer인 MemberPrinter 타입의 빈이 자동 주입된다.
         * (자동 주입이 우선 ! )
         *
         * 자동 주입 코드와 수동 주입 코드를 섞어서 쓰지 말자 !
         * 일부 자동 주입을 적용하기 어려운 것 말고는 모두 자동 주입을 사용하는 것이 좋다.
         *
         */

//        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
//        infoPrinter.setPrinter(memberPrinter2());
//        return infoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
