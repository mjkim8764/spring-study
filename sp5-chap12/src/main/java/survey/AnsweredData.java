package survey;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

/**
 * AnsweredData 는 앞서 예제랑 다르게
 *  1. List 타입의 프로퍼티가 존재.
 *  2. 중첩 프로퍼티를 갖는다. res 는 Respondent 타입이며 res 프로퍼티는 다시 age, location 프로퍼티를 갖음 (Respondent 클래스 참고)
 *
 * 스프링 MVC 는 커맨드 객체가 리스트 타입의 프로퍼티를 가졌거나 중첩 프로퍼티를 가진 경우에도
 * 요청 파라미터의 값을 알맞게 커맨드 객체에 설정해준다.
 *  1. HTTP 요청 파라미터 이름이 "프로퍼티이름[인덱스]" 형식이면 List 타입 프로퍼티의 값 목록으로 처리
 *      ex) responses[0], responses[1]
 *  2. HTTP 요청 파라미터 이름이 "프로퍼티이름.프로퍼티이름" 일 때 중첩 프로퍼티 값을 처리함.
 *      ex) res.location, res.age
 *  ※ surveyForm.jsp 참고
 */

public class AnsweredData {

    private List<String> responses;
    private Respondent res;
}
