<%@page import="dao.EmployeeDAO"%>
<%@page import="dao.StationsDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
    <h3>Таблица инцидентов</h3>
</head>
<body>
    <table border="1">
           <tr>
                            <th>Код инцидента</th>
                            <th>Описание инцидента</th>
                            <th>Станция(код)</th>
                            <th>дата обнаружения</th>
                            <th>период закрытия</th>
                            <th>дата закрытия</th>
                            <th>Обнаружил(код сотрудника)</th>
                            <th>Устранил(код сотрудника)</th>
                            <th>Операция</th>
                            <th>Операция</th>
                        </tr>
        <c:forEach var="elem" items="${listIncident}">
            <tr>
                                <td>${elem.codeIncident}</td>
                                <td>${elem.description}</td>
                                <td>
                                    <c:set var="statID" value="${elem.codeStation}"/>
                                    <%
                                        StationsDAO statDAO = (StationsDAO) this.getServletContext().getAttribute("stationsDAO");
                                        String strCityId = (pageContext.getAttribute("statID", PageContext.PAGE_SCOPE)).toString();
                                        int statID = Integer.parseInt(strCityId);
                                        pageContext.getOut().print(statDAO.getNameStationByID(statID));
                                    %>
                                </td>
                                <td>${elem.detectionDate}</td>
                                <td>${elem.closingPeriod}</td>
                                <td>${elem.closingDate}</td>
                                <td>
                                    <c:set var="empID" value="${elem.foundEmployee}"/>
                                    <%
                                        EmployeeDAO empDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
                                        String strEmpId = (pageContext.getAttribute("empID", PageContext.PAGE_SCOPE)).toString();
                                        int empID = Integer.parseInt(strEmpId);
                                        pageContext.getOut().print(empDAO.getFoundEmployeeByID(empID));
                                    %>
                                </td>
                                <td>
                                    <c:set var="empID2" value="${elem.closeEmployee}"/>
                                    <%
                                        String strEmpId2 = (pageContext.getAttribute("empID2", PageContext.PAGE_SCOPE)).toString();
                                        int empID2 = Integer.parseInt(strEmpId2);
                                        pageContext.getOut().print(empDAO.getCloseEmployeeByID(empID2));
                                    %>
                                </td>
                                <td><a href="/DbLab4/updateOnePost?codeIncident=${elem.codeIncident}&nameTable=incident" target="_blank">Редактировать</a></td>
                                <td><a href="/DbLab4/DeleteToId?deletCode=${elem.codeIncident}&nameTable=incident" target="_blank">Удалить</a></td>
                            <tr>   
            </c:forEach>    
    </table>
</body>
</html>
