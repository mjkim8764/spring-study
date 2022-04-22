package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    private MemberRegisterService memberRegisterService;

    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @RequestMapping("/register/step1")
    public String handleStep1() {
        return "register/step1";
    }

    /**
     * @PostMapping("/register/step2")
     * public String handleStep2(HttpServletRequest request) {
     *     String agreeParam = request.getParameter("agree");
     *     if(agreeParam == null || !agreeParam.equals("true")) {
     *         return "register/step1";
     *     }
     *
     *     return "register/step2";
     * }
     */


    /**
     * 요청 파라미터 개수가 몇 개 되지 않으면
     * @RequestParam 을 사용해서 간단하게 요청 파라미터의 값을 구할 수 있다.
     * @RequestParam 의 속성
     *  value (String 타입) : HTTP 요청 파라미터의 이름
     *  required (boolean 타입) : 필수 여부를 지정. required 가 true 인데 해당 요청 파라미터에
     *  값이 없으면 Exception 발생, 기본 값은 true
     *  defaultValue (String 타입) : 요청 파라미터 값이 없을 때 사용할 문자열 값을 지정, 기본값 없음.
     */

    @PostMapping("/register/step2")
    public String handleStep2(Model model,
                              @RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
        if(!agree) {
            return "register/step1";
        }
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register/step2";

    }

    /**
     * step1.jsp 를 거쳐서 가지 않고 직접
     * localhost:8090/sp5-chap11/register/step2 를 요청하는 것은 GET 방식 요청이므로
     * 약관 동의 화면 (step1) 으로 redirect 시킨다.
     */
    @GetMapping("/register/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";

        // return "redirect:step1";
        // "/" 로 시작하지 않으면 현재 경로를 기준으로 상대 경로를 사용함.

        // return "redirect:http://localhost:8080/sp5-chap11/register/step1"
        // 위와 같이 완전한 URL 을 사용해도 된다.
    }

    /**
     * @PostMapping("/register/step3")
     * public String handleStep3(HttpServletRequest request){
     *      String email = request.getParameter("email");
     *      String name = request.getParameter("name");
     *      String password = request.getParameter("password");
     *      String confirmPassword = request.getParameter("confirmPassword");
     *
     *      RegisterRequest regReq = new RegisterRequest();
     *      regReq.setEmail(email);
     *      regReq.setName(name);
     *      ....
     *  }
     *
     *  서버로 전송하는 파라미터가 수십개 일 때는
     *  파라미터를 읽고 설정하는 코드만 몇십 줄일 것이다.
     *
     *  스프링은 이런 불편함을 줄이기 위해
     *  요청 파라미터의 값을 커맨드(command) 객체에 담아주는 기능을 한다.
     */

    @PostMapping("/register/step3")
    public String handleStep3(RegisterRequest reqReq) {

        try {
            memberRegisterService.regist(reqReq);
            return "register/step3";
        } catch (DuplicateMemberException ex) {
            return "register/step2";
        }
    }

    /**
     * public String handleStep3( @ModelAttribute("formData") RegisterRequest reqReq) {
     *     ...
     * }
     * 위와 같이 모델에서 사용할 속성 이름을 값으로 설정할 수 있다.
     * 뷰 코드에서는 위에서 설정한 formData 이름으로 커맨드 객체에 접근하면 된다.
     *
     */



}
