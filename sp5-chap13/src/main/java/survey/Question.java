package survey;

import lombok.Getter;

import java.util.Collections;
import java.util.List;


/**
 * surveyForm.jsp 에 설문 항목을 하드 코딩했었는데
 * 설문 항목을 컨트롤러에서 생성해서 뷰에 전달하는 방식으로 변경하기 위해
 * 개별 설문 항목 데이터를 담기 위한 클래스를 아래와 같이 작성.
 */

@Getter
public class Question {

    private String title;
    private List<String> options;

    public Question(String title, List<String> options) {
        this.title = title;
        this.options = options;
    }


    /**
     * 주관식일 때는 생성자를 사용해서 답변 옵션이 없는 Question 객체를 생성.
     */

    public Question(String title) {
        this(title, Collections.<String>emptyList());
    }

    public boolean isChoice() {
        return options != null && !options.isEmpty();
    }
}
