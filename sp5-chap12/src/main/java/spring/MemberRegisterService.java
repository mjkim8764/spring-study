package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
    private MemberDao memberDao;

    // 생성자를 통해 의존 객체를 주입받고 있음
    public MemberRegisterService(MemberDao memberDao){

        // 주입받은 의존 객체를 필드에 할당
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {

        // 가입할 때 쓴 email(=req.email)이 이미 Map 안에 존재하면(member != null) Exception 처리

        // 주입받은 의존 객체(MemberDao)의 메서드를 사용하고 있음
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }

        // 존재하지 않으면 req의 정보들을 이용하여 Member 객체 생성한 후 insert
        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();

    }
}
