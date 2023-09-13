<html>
<head>
    <title>My HTML Page</title>
</head>
<body>
    My html page with body
</body>
</html>

## JSP

/src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp

/say-hello.jsp => SayHelloController - sayHelloJsp method => sayHello

/WEB-INF/jsp/sayHello.jsp

## Login JSP

/ login => com.nareun.springboot.mywebapp.login.LoginController =>login.jsp

##
localhost:8080/login

1 : DispatcherServlet이 옳바른 컨트롤러 메서드를 식별 
    /login => LoginController.gotoLoginPage
2 : 이 컨트롤러 메서드를 실행 ~> 컨트롤러가 필요한 데이터를 모델에 넣고 뷰 이름을 리턴 
    => login
3 : View Resolver가 prefix와 suffix설정을 조합하여 적절한 뷰를 찾아줌.
    => /WEB-INF/jsp/login.jsp
4 : 뷰 실행