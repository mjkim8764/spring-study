package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            Object authInfo = session.getAttribute("authInfo");

            /**
             * session 이 존재하고 authInfo 속성이 존재하면 true 를 리턴한다.
             * 즉 컨트롤러를 실행하겠다는 의미
             */

            if(authInfo != null){
                return true;
            }
        }

        /**
         * session 이 존재하지 않으면
         * Redirect 응답을 생성한 뒤 false 를 리턴한다.
         * preHandle 메서드가 false 를 리턴하면 로그인 상태가 아니라는 것을
         * 의미하기 때문에 아래에서 지정한 경로로 리다이렉트한다.
         * 톰캣 로컬 서버 설정에서 Application Context 를 /sp5-chap13 으로
         * 설정하였으므로 request.getContextPath() + "/login" = /sp5-chap13/login 이다.
         */
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
