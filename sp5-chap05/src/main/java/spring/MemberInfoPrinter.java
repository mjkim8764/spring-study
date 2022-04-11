package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Component 에 값을 넣으면
 * 빈으로 등록할 때 사용할 이름이 결정된다.
 */

@Component("infoPrinter")
public class MemberInfoPrinter {

    private MemberDao memberDao;
    private MemberPrinter printer;

    public void printMemberInfo(String email) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) {
            System.out.println("데이터 없음\n");
            return;
        }
        printer.print(member);
        System.out.println();
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    @Qualifier("printer")
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }

}
