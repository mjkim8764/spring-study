<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <!-- 스프링 MVC 는 커맨드 객체의 클래스 이름과 첫 글자를 소문자로 한 동일한 속성 이름을 사용해서
    커맨드 객체를 뷰에 전달한다. JSP 에서 아래와 같이 커맨드 객체에 접근 가능 -->
    <p><strong>${registerRequest.name}님</strong>
        회원 가입을 완료했습니다.</p>
    <p><a href="<c:url value='/main'/>">[첫 화면 이동]</a></p>
</body>
</html>
