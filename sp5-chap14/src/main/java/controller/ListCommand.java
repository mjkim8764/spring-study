package controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

// 검색을 위한 커맨드 객체

@Getter
@Setter
public class ListCommand {

    /**
     * jsp form 에서 <input> 태그로 입력받은 문자열을
     * 스프링은 보통 Long, int 와 같은 기본 데이터로의 변환은 해준다.
     * 하지만 입력받은 문자열을 LocalDateTime 타입으로의 변환은 아래처럼 추가로 어노테이션으로 설정해줘야 한다.
     */

    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime from;

    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime to;
}
