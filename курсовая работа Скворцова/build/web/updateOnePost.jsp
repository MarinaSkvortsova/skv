<%@page import="dao.EmployeeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.StationsDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование таблицы Incident</title>
    </head>
    <body>
        
        <div class="container">
            <div class="row">
                <div class="span12">
                    <br><br><br><br><br><br>
                    <center><h3>Обновление записи для таблицы Incident</h3></center>
                        <c:set var="elem" value="${incidentObj}"></c:set>
                        <form action="upOnePost" method="GET">
                            <table border="0">
                                <tr><th>Код инцидента</th><td><label>${elem.codeIncident}</label><input type="hidden" name="codeIncident" value="${elem.codeIncident}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Описание инцидента</th><td><input type="text" class="form-control" name="description" value="${elem.description}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Станция</th>
                                <td>
                                    <select name="codeStation" class="form-control">
                                        <c:set var="codeStation" value="${elem.codeStation}"/>
                                        <%
                                            StationsDAO stationsDAO = (StationsDAO) this.getServletContext().getAttribute("stationsDAO");
                                            String strCodeStation = (pageContext.getAttribute("codeStation", PageContext.PAGE_SCOPE)).toString();
                                            int codeStation = Integer.parseInt(strCodeStation);
                                            List<String> massNameCodeStation = new ArrayList<String>();
                                            massNameCodeStation = stationsDAO.getNameStation(codeStation);
                                            for (int i = 0; i < massNameCodeStation.size(); ++i) {
                                                pageContext.getOut().println("<option value=" + massNameCodeStation.get(i) + ">" + massNameCodeStation.get(++i) + "</option>");
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>дата обнаружения</th><td><input type="date" class="form-control" name="detectionDate" value="${elem.detectionDate}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>период закрытия</th><td><input type="date" class="form-control" name="closingPeriod" value="${elem.closingPeriod}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>дата закрытия</th><td><input type="date" name="closingDate" class="form-control" value="${elem.closingDate}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Обнаружил</th>
                                <td>
                                    <select name="foundEmployee" class="form-control">
                                        <c:set var="foundEmployee" value="${elem.foundEmployee}"/>
                                        <%
                                            EmployeeDAO employeeDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
                                            String strFoundEmployee = (pageContext.getAttribute("foundEmployee", PageContext.PAGE_SCOPE)).toString();
                                            int foundEmployee = Integer.parseInt(strFoundEmployee);
                                            List<String> massFoundEmployee = new ArrayList<String>();
                                            massFoundEmployee = employeeDAO.getFoundEmployee(foundEmployee);
                                            for (int i = 0; i < massFoundEmployee.size(); ++i) {
                                                pageContext.getOut().println("<option value=" + massFoundEmployee.get(i) + ">" + massFoundEmployee.get(++i) + "</option>");
                                            }
                                        %>
                                    </select>
                                </td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Устранил</th>
                                <td>
                                    <select name="closeEmployee" class="form-control">
                                        <c:set var="closeEmployee" value="${elem.closeEmployee}"/>
                                        <%
                                            String strCloseEmployee = (pageContext.getAttribute("closeEmployee", PageContext.PAGE_SCOPE)).toString();
                                            int closeEmployee = Integer.parseInt(strCloseEmployee);
                                            List<String> massloseEmployee = new ArrayList<String>();
                                            massFoundEmployee = employeeDAO.getFoundEmployee(closeEmployee);
                                            for (int i = 0; i < massFoundEmployee.size(); ++i) {
                                                pageContext.getOut().println("<option value=" + massFoundEmployee.get(i) + ">" + massFoundEmployee.get(++i) + "</option>");
                                            }
                                        %>
                                    </select>
                                </td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th><input type="hidden" name="nameTable" value="${nameTable}"></th><td><input type="submit" class="btn btn-primary" name="update" value="Сохранить"></td></tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
