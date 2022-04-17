package main;

import config.AppCtx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Member;
import spring.MemberDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainForMemberDao {

    private static final Logger logger = LoggerFactory.getLogger(MainForMemberDao.class);

    private static MemberDao memberDao;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        memberDao = ctx.getBean(MemberDao.class);

        selectAll();
        updateMember();
        insertMember();

        logger.debug("log test");
    }

    private static void selectAll() {
        logger.info("----- selectAll");
        int total = memberDao.count();
        logger.info("전체 데이터 : " + total);
        List<Member> members = memberDao.selectAll();

        for(Member member : members) {
            logger.warn(member.getId() + ":" + member.getEmail() + ":" + member.getName());
        }

    }

    private static void updateMember() {
        logger.info("----- updateMember");
        Member member = memberDao.selectByEmail("mjkim8764@gmail.com");

        // 임의의 새로운 암호를 넣도록 했음
        String oldPw = member.getPassword();
        String newPw = Double.toHexString(Math.random());

        member.changePassword(oldPw, newPw);

        // 실제 DB 업데이트
        memberDao.update(member);
        logger.debug("암호 변경: " + oldPw + " > " + newPw);

    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

    private static void insertMember() {
        logger.info("----- insertMember");

        // 임의의 Member 데이터를 만들어서 DB에 추가
        String prefix = formatter.format(LocalDateTime.now());
        Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());

        memberDao.insert(member);
        logger.debug(member.getId() + " 데이터 추가");
    }

}
