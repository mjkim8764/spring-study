package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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


    @PostMapping("/register/step2")
    public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
                              Model model) {
        if(!agree) {
            return "register/step1";
        }
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register/step2";

    }


    @GetMapping("/register/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";
    }


    // Errors 타입 파라미터는 반드시 커맨드 객체를 위한 파라미터 다음에 위치해야 함. 그렇지 않으면 Exception 발생
    // Errors 대신에 Errors 인터페이스를 상속하는 BindingResult 인터페이스를 파라미터로 사용해도 됨.
    @PostMapping("/register/step3")
    public String handleStep3(@Valid RegisterRequest regReq, Errors errors) {

        // RegisterRequestValidator 객체를 생성하고 validate 메서드 실행
        // RegisterRequest 커맨드 객체의 값이 올바른지 검사하고 그 결과를 errors 에 담음.
        // new RegisterRequestValidator().validate(regReq, errors);

        if(errors.hasErrors())
            return "register/step2";

        try {
            memberRegisterService.regist(regReq);
            return "register/step3";
        } catch (DuplicateMemberException ex) {
            errors.rejectValue("email", "duplicate");
            return "register/step2";
        }
    }

    /*

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new RegisterRequestValidator());
    }

    */

}