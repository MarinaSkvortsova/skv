<%@page import="dao.EmployeeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.CexDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование таблицы Stations</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="span12">
                    <br><br><br><br><br><br>
                    <center><h3>Обновление записи для таблицы Stations</h3></center>
                        <c:set var="elem" value="${stationsObj}"></c:set>
                        <form action="upOnePost" method="GET">
                            <table border="0">
                                <tr><th>Код станции</th><td><label>${elem.codeStation}</label><input type="hidden" name="codeStation" value="${elem.codeStation}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Название станции</th><td><input type="text" class="form-control" name="nameStation" value="${elem.nameStation}"></td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Цех</th>
                                <td>
                                    <select name="codeCex" class="form-control">
                                        <c:set var="codeCex" value="${elem.codeCex}"/>
                                        <%
                                            CexDAO cexDAO = (CexDAO) this.getServletContext().getAttribute("cexDAO");
                                            String strCodeCex = (pageContext.getAttribute("codeCex", PageContext.PAGE_SCOPE)).toString();
                                            int codeCex = Integer.parseInt(strCodeCex);
                                            List<String> massCodeCex = new ArrayList<String>();
                                            massCodeCex = cexDAO.getNameCexUpdate(codeCex);
                                            for (int i = 0; i < massCodeCex.size(); ++i) {
                                                pageContext.getOut().println("<option value=" + massCodeCex.get(i) + ">" + massCodeCex.get(++i) + "</option>");
                                            }
                                        %>
                                    </select>
                                </td></tr>
                            <tr><td><br></td><td><br></td></tr>
                            <tr><th>Старший по станции</th>
                                <td>
                                    <select name="older" class="form-control">
                                        <c:set var="older" value="${elem.older}"/>
                                        <%
                                            EmployeeDAO employeeDAO = (EmployeeDAO) this.getServletContext().getAttribute("employeeDAO");
                                            String strOlder = (pageContext.getAttribute("older", PageContext.PAGE_SCOPE)).toString();
                                            List<String> massFoundEmployeeStr = new ArrayList<String>();
                                            massFoundEmployeeStr = employeeDAO.getFoundEmployeeStr(strOlder);
                                            for (int i = 1; i < massFoundEmployeeStr.size(); i += 2) {
                                                pageContext.getOut().println("<option value=\"" + massFoundEmployeeStr.get(i) + "\">" + massFoundEmployeeStr.get(i) + "</option>");
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
