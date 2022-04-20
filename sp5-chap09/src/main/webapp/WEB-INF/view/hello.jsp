<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>Hello</title>
</head>
<body>
    <!--
        model.addAttribute("greeting", "안녕하세요, " + name);

        스프링 MVC 프레임워크가 greeting 속성을
        JSP 코드에서 접근할 수 있게 HttpServletRequest 에 옮겨줌
        ( request.setAttribute("greeting", 값) )
    -->
    인사말: ${greeting}
</body>
</html>
