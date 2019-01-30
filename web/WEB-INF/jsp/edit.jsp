<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>
                Имя:
            </dt>
            <dd>
                <input type="text" name="fullName" size="50" value="${resume.fullName}">
            </dd>
        </dl>
        <h3>Контакты:</h3>

        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>
                        ${type.title}
                </dt>
                <dd>
                    <input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}">
                </dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>

        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
            <c:choose>
                <c:when test="${type == 'OBJECTIVE' || type =='PERSONAL'}">
                    <dl>
                        <dt>
                                ${type.title}
                        </dt>
                        <dd>
                            <input type="text" name="${type.name()}" size="30" value="${resume.getSection(type)}">
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                    <dl>
                        <dt>
                                ${type.title}
                        </dt>
                        <dd>
                        <textarea name='${type}' cols=75 rows=5><%=String.join("\n", ((MarkSection) section).getMarkList())%></textarea>
                        </dd>
                    </dl>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
