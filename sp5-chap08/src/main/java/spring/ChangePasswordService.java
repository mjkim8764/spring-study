package spring;

import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Setter
public class ChangePasswordService {

    private MemberDao memberDao;


    /**
     * 별도의 설정을 하지 않으면 발생한 Exception 이 RuntimeException 일 때
     * 트랜잭션을 롤백한다. WrongIdPasswordException 클래스 등을 구현할 때
     * RuntimeException 을 상속받도록 한 이유가 트랜잭션 롤백을 염두에 두었기 때문.
     *
     * JdbcTemplate 은 DB 연동 과정에 문제가 있으면 DataAccessException 을 발생
     * DataAccessException 역시 RuntimeException 을 상속받고 있기 때문에
     * JdbcTemplate 의 기능을 실행하는 도중 Exception 이 발생해도
     * 프록시는 트랜잭션을 롤백함.
     *
     * 하지만 SQLException 은 RuntimeException 을 상속하고 있지 않으므로
     * SQLException 이 발생했을 때도 롤백 하고 싶다면
     * 아래와 같이 rollbackFor 속성을 사용한다.
     */

    // @Transactional(rollbackFor = SQLException.class)
    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd){
        Member member = memberDao.selectByEmail(email);
        if(member == null) {
            throw new MemberNotFoundException();
        }

        member.changePassword(oldPwd, newPwd);

        memberDao.update(member);

    }

}
