package spring;

import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Setter
public class ChangePasswordService {

    private MemberDao memberDao;

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
