package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MemberPrinter {

    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if(dateTimeFormatter == null) {
            System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                    member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
        } else {
            System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                    member.getId(), member.getEmail(), member.getName(),
                    dateTimeFormatter.format(member.getRegisterDateTime()));
        }

    }

    /**
     * 설정 클래스에 DateTimeFormatter 타입의 빈이 없으므로
     * Exception 발생한다.
     *
     * 방안
     *    1. @Autowired(required = false)
     *      : 대상 빈이 존재하지 않으면 메서드 호출하지 않음
     *    2. Optional
     *      : 대상 빈이 존재하지 않으면 값이 없는 Optional 을 할당
     *    3. @Nullable parameter
     *      : 대상 빈이 존재하지 않아도 메서드 호출
     */

    // @Autowired
    // public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
    //     this.dateTimeFormatter = dateTimeFormatter;
    // }

    @Autowired(required = false)
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

//    @Autowired
//    public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
//        if(formatterOpt.isPresent()) {
//            this.dateTimeFormatter = formatterOpt.get();
//        } else {
//            this.dateTimeFormatter = null;
//        }
//    }

//    @Autowired
//    public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//        this.dateTimeFormatter = dateTimeFormatter;
//    }


}
