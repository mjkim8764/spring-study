package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

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
