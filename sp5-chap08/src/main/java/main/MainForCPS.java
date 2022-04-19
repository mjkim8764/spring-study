package main;

import config.AppCtx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;


public class MainForCPS {

    private static final Logger logger = LoggerFactory.getLogger(MainForCPS.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);

        try {
            cps.changePassword("mjkim8764@gmail.com", "1234", "1111");
            logger.info("암호를 변경");
        } catch (MemberNotFoundException e) {
            logger.debug("회원 데이터 존재하지 않음");
        } catch (WrongIdPasswordException e) {
            logger.debug("암호가 올바르지 않음");
        }

        ctx.close();
    }
}
