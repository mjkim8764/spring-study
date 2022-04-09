package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {

    /**
     * Autowired 기본 : 설정 클래스(AppCtx)에서 의존을 주입하지 않아도 됨. 의존 자동 주입 !
     */

    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email, String oldPwd, String newPwd){
        Member member = memberDao.selectByEmail(email);
        if(member == null) {
            throw new MemberNotFoundException();
        }

        member.changePassword(oldPwd, newPwd);

        memberDao.update(member);

    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}
