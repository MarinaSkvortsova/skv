<%@page import="dao.CexDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
    <h3>Таблица станций</h3>
</head>
<body>
    <table border="1">
        <tr>
            <th>Код станции</th>
            <th>Название станции</th>
            <th>Код цеха</th>
            <th>Старший по станции</th>
            <th>Операция</th>
            <th>Операция</th>
        </tr>
        <c:forEach var="elem" items="${listStations}">
            <tr>
                <td>${elem.codeStation}</td>
                <td>${elem.nameStation}</td>
                <td>
                    <c:set var="cexID" value="${elem.codeCex}"/>
                    <%
                        CexDAO cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
                        String strCexId = (pageContext.getAttribute("cexID", PageContext.PAGE_SCOPE)).toString();
                        int cexID = Integer.parseInt(strCexId);
                        pageContext.getOut().print(cexDAO.getNameCex(cexID));
                    %>
                </td>
                <td>${elem.older}</td>
                <td><a href="/DbLab4/updateOnePost?codeStation=${elem.codeStation}&nameTable=stations" target="_blank">Редактировать</a></td>
                <td><a href="/DbLab4/DeleteToId?deletCode=${elem.codeStation}&nameTable=stations" target="_blank">Удалить</a></td>
            <tr>   
            </c:forEach>    
    </table>
</body>
</html>