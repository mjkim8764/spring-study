<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<!-- spring:message 태그 활용 -->

<html>
<head>
    <title><spring:message code="member.register" /></title>
</head>
<body>
    <h2><spring:message code="term" /></h2>
    <p>약관 내용</p>
    <form action="step2" method="post">
        <label>
            <input type="checkbox" name="agree" value="true">
            <spring:message code="term.agree" />
        </label>

        <input type="submit" value="<spring:message code="next.btn" />" />

    </form>
</body>
</html>



<!-- 메뉴 하드코딩

<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <h2>약관</h2>
    <p>약관 내용</p>
    <form action="step2" method="post">
        <label>
            <input type="checkbox" name="agree" value="true"> 약관 동의
        </label>

        <input type="submit" value="다음 단계" />

    </form>
</body>
</html>

-->
