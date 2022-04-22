<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 스프링이 제공하는 폼 태그를 사용하기 위해 taglib 디렉티브를 설정 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
    <title>회원가입</title>
</head>

<body>
    <h2>회원 정보 입력</h2>

    <!-- 커맨드 객체와 스프링 폼 연동
        modelAttribute : 커맨드 객체의 속성 이름을 지정한다.
                         설정하지 않는 경우 "command" 가 기본값

        step2.jsp 가 registerRequest 이름의 커맨드 객체를 사용하고 있기 때문에
        step1.jsp -> step2.jsp 로 넘어오는 단계에서 이름이 "registerRequest" 인 객체를 모델에 넣어야
        form:form 태그가 정상적으로 작동함  (RegisterController.java 의 handleStep2 메서드)
    -->

    <form:form action="step3" modelAttribute="registerRequest">
        <p>
            <label>
                이메일:<br>
                <form:input path="email" />
            </label>
        </p>
        <p>
            <label>
                이름:<br>
                <form:input path="name" />
            </label>
        </p>
        <p>
            <label>
                비밀번호:<br>
                <form:password path="password" />
            </label>
        </p>
        <p>
            <label>
                비밀번호 확인:<br>
                <form:password path="confirmPassword" />
            </label>
        </p>
        <input type="submit" value="가입 완료">
    </form:form>

</body>
</html>
