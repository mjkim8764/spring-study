package survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    /*
    @GetMapping
    public String form() {
        return "survey/surveyForm";
    }
    */


    /*
    @GetMapping
    public String form(Model model) {
        List<Question> questions = createQuestions();
        model.addAttribute("questions", questions);
        return "survey/surveyForm";
    }
    */

    /**
     * 위 메서드는 Model 을 이용해서 뷰에 전달할 데이터를 설정하고
     * 결과를 보여줄 뷰 이름을 리턴하였다.
     * 이를 ModelAndView 를 사용하여 한 번에 처리
     */
    @GetMapping
    public ModelAndView form() {
        List<Question> questions = createQuestions();
        ModelAndView mav = new ModelAndView();
        mav.addObject("questions", questions);
        mav.setViewName("survey/surveyForm");
        return mav;
    }

    private List<Question> createQuestions() {

        Question q1 = new Question("당신의 역할은 무엇입니까?",
                Arrays.asList("서버", "프론트", "풀스택"));
        Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?",
                Arrays.asList("이클립스", "인텔리J", "서브라임"));
        Question q3 = new Question("하고 싶은 말을 적어주세요.");

        return Arrays.asList(q1, q2, q3);
    }

    @PostMapping
    public String submit(@ModelAttribute("ansData") AnsweredData data) {
        return "survey/submitted";
    }

}
