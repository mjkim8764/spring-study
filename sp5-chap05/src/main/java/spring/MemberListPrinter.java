package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Component 에 값을 넣으면
 * 빈으로 등록할 때 사용할 이름이 결정된다.
 */

@Component("listPrinter")
public class MemberListPrinter {

    private MemberDao memberDao;
    private MemberPrinter printer;

    public MemberListPrinter() {
    }

    public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
        this.memberDao = memberDao;
        this.printer = printer;
    }

    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(v -> printer.print(v));
    }


    /**
     * setter 에 Autowired
     * 빈 설정 클래스(AppCtx)에서 생성자 수동 주입을 할 필요가 없다.
     */

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    // @Qualifier("printer")
    // public void setMemberPrinter(MemberPrinter printer) {
    public void setMemberPrinter(MemberSummaryPrinter printer) {
        this.printer = printer;
    }


}
