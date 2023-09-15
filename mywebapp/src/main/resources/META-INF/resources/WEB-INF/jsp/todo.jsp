<%@ include file="common/header.jspf"%> <%@ include file="common/navigation.jspf"%>
<div class="container">
  <div>Welcome ${name}</div>
  <hr />
  <h1>Enter Todo Details</h1>
  <!-- ! javascript나 html에서의 검증은 보안이 약함. -> 서버 측 검사가 최선! -->
  <form:form method="post" modelAttribute="todo">
    <!-- ? description에 해당하는 필드들을 fieldset으로 묶어준다. -->
    <fieldset class="mb-3">
      <form:label path="description">Description</form:label>
      <form:input type="text" path="description" required="required" />
      <form:errors path="description" cssClass="text-warning" />
    </fieldset>

    <fieldset class="mb-3">
      <form:label path="targetDate">Target Date</form:label>
      <form:input type="text" path="targetDate" required="required" />
      <form:errors path="targetDate" cssClass="text-warning" />
    </fieldset>

    <!-- ! Http의 비연결성 특징 때문에 밑의 input을 추가! -->
    <form:input type="hidden" path="id" />
    <form:input type="hidden" path="done" />
    <input type="submit" class="btn btn-success" />
  </form:form>
</div>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
  $("#targetDate").datepicker({
    format: "yyyy-mm-dd", //~> datepicker에서는 MM이 September가 나옴.
  });
</script>
