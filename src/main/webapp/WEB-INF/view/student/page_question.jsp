<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 29.12.2022
  Time: 04:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Title</title>



</head>
<body>
<div id="timer"> </div>


<div id="text_question">
    <input id="id_question" type="hidden" name="id_question" value="${requestScope.id_question}">
    <input id="number_question" type="hidden" name="number_question" value="${requestScope.number_question}">
</div>

<%--<form method="post" action="result_answer">--%>
<%--    <c:forEach var="ans" items="${requestScope.answers}">--%>
<%--        <input class="res" type="checkbox" name="res" value="${ans.id}">--%>
<%--        <c:out value="${ans.text}"/>--%>
<%--        <br>--%>
<%--    </c:forEach>--%>
<%--        <input class="button" type="submit" value="<fmt:message key="select.option.yes"/>">--%>
<%--</form>--%>


<script>
    window.onload = function () {
        loadQuestionAndAnswer(
            document.getElementById('id_question').value,
            document.getElementById('number_question').value,
            document.getElementsByName('res'));

        timer(${requestScope.duration})
    }
</script>





<div class="progress" role="progressbar" aria-label="Example with label" aria-valuenow="25"
     aria-valuemin="0" aria-valuemax="100">
    <div class="progress-bar" style="width: 25%">25%</div>
</div>

<label for="file">File progress:</label>

<progress id="file" max="100" value="70"> 70% </progress>

</body>
</html>
