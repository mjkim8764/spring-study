package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.AuthInfo;
import spring.AuthService;
import spring.WrongIdPasswordException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    private AuthService authService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }


    /**
     * @CookieValue
     * value 속성 : 쿠키의 이름을 지정 (여기서는 REMEMBER)
     * 체크박스를 선택하지 않을 수 있기 때문에 required 는 false 로
     * (required 가 true 일 때 value 값을 이름으로 하는 쿠키가 없으면 Exception 발생)
     */

    @GetMapping
    public String form(LoginCommand loginCommand,
                       @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
        if(rCookie != null) {
            loginCommand.setEmail(rCookie.getValue());
            loginCommand.setRememberEmail(true);
        }
        return "login/loginForm";
    }

    @PostMapping
    public String submit(LoginCommand loginCommand, Errors errors, HttpSession session,
                         HttpServletResponse response) {
        // new LoginCommandValidator().validate(loginCommand, errors);
        if(errors.hasErrors()) {
            return "login/loginForm";
        }

        try {
            AuthInfo authInfo = authService.authenticate(
                    loginCommand.getEmail(), loginCommand.getPassword()
            );

            session.setAttribute("authInfo", authInfo);


            /**
             * 실제 쿠키를 생성하는 코드
             * submit 메서드의 파라미터로 HttpServletResponse 객체를 생성했고
             * addCookie 메서드로 쿠키를 넣어줌.
             */

            Cookie rememberCookie =
                    new Cookie("REMEMBER", loginCommand.getEmail());
            rememberCookie.setPath("/");
            if(loginCommand.isRememberEmail()) {
                rememberCookie.setMaxAge(60 * 60 * 24 * 30);  // 단위 : 초
            } else {
                rememberCookie.setMaxAge(0);
            }

            response.addCookie(rememberCookie);

            return "/login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "login/loginForm";
        }
    }
}
