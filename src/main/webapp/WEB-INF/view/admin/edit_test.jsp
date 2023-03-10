<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.12.2022
  Time: 01:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<html lang="${sessionScope.locale}">
<head>
    <title>Edit</title>
    <style>
        <%@include file="/static/css/style.css"%>
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br>


<div class="offcanvas offcanvas-start" id="forTest">
    <div class="offcanvas-header">
        <h1 class="offcanvas-title"><fmt:message key="button.edit.test"/> </h1>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form method="post" action="edit_test">
            <input type="hidden" name="page" value="${requestScope.page}">
            <input type="hidden" name="test_id" value="${requestScope.test_id}">

            <h6 class="fw-light"><fmt:message key="table.head.test.name"/></h6>
            <input type="text" name="name" value="${requestScope.name}">
            <h6 class="fw-light"><fmt:message key="table.head.subject"/></h6>
            <input type="text" value="${requestScope.subject}" name="subject">
            <h6 class="fw-light"><fmt:message key="table.head.difficult"/></h6>
            <input type="number" name="difficult" value="${requestScope.difficult}">
            <h6 class="fw-light"><fmt:message key="table.head.duration"/></h6>
            <input type="number" name="duration" value="${requestScope.duration}"><br>
            <div class="d-flex justify-content-center">
                <button type="submit"
                        class="btn btn-secondary"><fmt:message key="button.edit"/>
                </button>
            </div>
            <%--            <c:out value="${requestScope.message}"/>--%>
        </form>
    </div>
</div>
<!-- Button to open the offcanvas sidebar -->
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#forTest">
    <fmt:message key="button.edit.test"/>
</button>
<%--======================================================--%>

<div class="offcanvas offcanvas-start" id="forAddQuestion">
    <div class="offcanvas-header">
        <h1 class="offcanvas-title"><fmt:message key="button.add.question"/> </h1>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form method="post" action="add_question">
            <input type="hidden" name="page" value="${requestScope.page}">
            <input type="hidden" name="test_id" value="${requestScope.test_id}">
            <input type="text" required placeholder="<fmt:message key="table.head.text.of.question"/>" name="text"
                   value="${requestScope.too_Long_Text}">
            <br>
            <div class="d-flex justify-content-center">
                <button type="submit"
                        class="btn btn-secondary"><fmt:message key="button.add"/>
                </button>
            </div>
        </form>
    </div>
</div>
<!-- Button to open the offcanvas sidebar -->
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#forAddQuestion">
    <fmt:message key="button.add.question"/>
</button>

<form action="filter_tests">
    <input type="hidden" name="page" value="${requestScope.page}">
    <input class="button" type="submit" value="<fmt:message key="button.back"/>">
</form>

<c:out value="${requestScope.message_question}"/>
<c:out value="${requestScope.message}"/>

<c:if test="${requestScope.message != null}">
    <div class="alert alert-warning">
        <strong>Warning!</strong> <c:out value="${requestScope.message}"/>
    </div>
</c:if>





<table class="table align-middle mb-0 w-auto bg-white">
    <thead class="bg-secondary">
    <tr>
        <th><fmt:message key="table.head.text.of.question"/></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="question" items="${requestScope.questions}">
    <tr>
        <td><c:out value="${question.text}"/></td>
        <td>
            <form action="edit_question">
                <input type="hidden" name="page" value="${requestScope.page}">
                <input type="hidden" name="test_id" value="${requestScope.test_id}">
                <input type="hidden" name="question_id" value="${question.id}">
                <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.edit"/>">
            </form>
        </td>
        <td>
            <form method="post" action="delete_question">
                <input type="hidden" name="page" value="${requestScope.page}">
                <input type="hidden" name="test_id" value="${requestScope.test_id}">
                <input type="hidden" name="question_id" value="${question.id}">
                <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.delete"/>">
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>


<%--<form method="post" action="add_question">--%>
<%--    <input type="hidden" name="page" value="${requestScope.page}">--%>
<%--    <input type="hidden" name="test_id" value="${requestScope.test_id}">--%>
<%--    <input type="text" required placeholder="<fmt:message key="table.head.text.of.question"/>" name="text"--%>
<%--           value="${requestScope.too_Long_Text}"><br>--%>
<%--    <input class="button" type="submit" value="<fmt:message key="button.add.question"/>">--%>
<%--    <c:out value="${requestScope.message_question}"/>--%>
<%--</form>--%>

</body>
</html>
