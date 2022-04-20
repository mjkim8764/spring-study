package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller : 스프링 MVC 에서 컨트롤러로 사용함.
 *              웹 요청을 처리하고 그 결과를 뷰에 전달하는 스프링 빈 객체
 */

@Controller
public class HelloController {

    /**
     * Model 파라미터 : 컨트롤러의 처리 결과를 뷰에 전달할 때 사용
     * greeting 이라는 모델 속성에 값("안녕하세요" + name) 을 설정함.
     */
    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false)String name) {
        model.addAttribute("greeting", "안녕하세요, " + name);
        return "hello";     // 컨트롤러의 처리 결과를 보여줄 뷰 이름으로 "hello" 를 사용.
    }
}
