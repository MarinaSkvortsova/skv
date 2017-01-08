<%@page import="dao.CexDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
    
    <h3>Таблица сотрудников</h3>
</head>
<body>
    <table border="1">
        <tr>
            <th>Код сотрудника</th>
            <th>ФИО</th>
            <th>Название цеха</th>
            <th>Должность</th>
            <th>Дата приема на работу</th>
            <th>Операция</th>
            <th>Операция</th>
        </tr>
        <c:forEach var="elem" items="${listEmployee}">
            <tr>
                <td>${elem.codeEmployee}</td>
                <td>${elem.fio}</td>
                <!--<td>${elem.codeCex}</td>-->
                <td>
                    <c:set var="cexID" value="${elem.codeCex}"/>
                    <%
                        CexDAO cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
                        String strCexId = (pageContext.getAttribute("cexID", PageContext.PAGE_SCOPE)).toString();
                        int cexID = Integer.parseInt(strCexId);
                        pageContext.getOut().print(cexDAO.getNameCex(cexID));
                    %>
                </td>
                <td>${elem.position}</td>
                <td>${elem.employmentDate}</td>
                <td><a href="/DbLab4/updateOnePost?codeEmployee=${elem.codeEmployee}&nameTable=employee" target="_blank">Редактировать</a></td>
                <td><a href="/DbLab4/DeleteToId?deletCode=${elem.codeEmployee}&nameTable=employee" target="_blank">Удалить</a></td>
            <tr>   
            </c:forEach>    
    </table>
</body>
</html>