<%@ page import="ru.javawebinar.basejava.model.MarkSection" %>
<%@ page import="ru.javawebinar.basejava.model.OrganizationSection" %>
<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <p>
    <table border="1">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
            <c:choose>
                <c:when test="${type == 'OBJECTIVE' || type =='PERSONAL'}">
                    <tr>
                        <td valign="top" width="200">${type.title}<br>&nbsp;
                        </td>
                        <td valign="top"><%=((TextSection) section).getText()%>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                    <tr valign="top">
                        <td valign="top">${type.title}<br>&nbsp;</td>
                        <td valign="top"><c:forEach var="item" items="<%=((MarkSection) section).getMarkList()%>">
                            <li>${item}
                        </c:forEach></td>
                    </tr>
                </c:when>
                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganization()%>">
                        <tr>
                            <td valign="bottom" colspan="2"><br>
                                <c:choose>
                                    <c:when test="${empty org.homePage.url}">
                                        <b>${org.homePage.name}</b>
                                    </c:when>
                                    <c:otherwise>
                                        <b><a href="${org.homePage.url}">${org.homePage.name}</a></b>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <c:forEach var="position" items="${org.content}">
                            <tr>
                                <td valign="top">
                                    <jsp:useBean id="position"
                                                 type="ru.javawebinar.basejava.model.Organization.Content"/>
                                        ${position.periodStart}&mdash;
                                        ${position.periodEnd}<br>
                                        ${position.position}</td>
                                <td valign="top">
                                        ${position.description}<br>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
