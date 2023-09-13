<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" />
    <title>List Todos Page</title>
  </head>
  <body>
    <div class="container"> 
      <div>Welcome ${name}</div>
      <hr />
      <h1>Enter Todo Details</h1>
      <form:form method="post" modelAttribute="todo">
        <!-- ! javascript나 html에서의 검증은 보안이 약함. -> 서버 측 검사가 최선! -->
        Description: <form:input type="text" path="description" required="required" />
        <form:input type="hidden" path="id" />
        <form:input type="hidden" path="done" />
        <input type="submit" class="btn btn-success" />
      </form:form>
      <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
      <script src="webjars/jquery/3.6.0/jquery.min.js "></script>
    </div>
  </body>
</html>
